package com.initium.assignment.ui.uikit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import assignment.composeapp.generated.resources.Res
import assignment.composeapp.generated.resources.close
import assignment.composeapp.generated.resources.retry_txt
import com.initium.assignment.domain.model.DEFAULT_LIMIT
import com.initium.assignment.domain.model.ListDataStruct
import com.initium.assignment.ui.theme.Md3
import kotlinx.coroutines.flow.distinctUntilChanged
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import kotlin.math.max

private fun generateItems(page: Int, itemsPerPage: Int = DEFAULT_LIMIT): List<String> {
    return List(itemsPerPage) { index ->
        "Page $page - Item $index"
    }
}

@Composable
private fun <T> LazyListState.LoadMoreHandler(
    state: GenericLazyListState<T>,
    buffer: Int = 3,
    onLoadMore: (Int) -> Unit,
) {
    val loadMore by remember {
        derivedStateOf {
            val layoutInfo = this.layoutInfo
            val totalItemsNumber = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1
            val reachBottom = (lastVisibleItemIndex > totalItemsNumber - buffer)

            val virtualItemSize = (state.currentPage + 1) * state.itemPerPage
            val actualSize = if (state.initialized) state.total else (state.itemPerPage + 1).toLong()

            reachBottom && actualSize >= 0 && virtualItemSize < actualSize
        }
    }

    LaunchedEffect(loadMore) {
        snapshotFlow { loadMore }
            .distinctUntilChanged()
            .collect {
                if (!it) return@collect
                val next: Float = ((state.currentPage + 1) * state.itemPerPage).toFloat() / state.itemPerPage
                val hasMore = next - next.toInt() == 0f
                if (hasMore) {
                    onLoadMore(next.toInt())
                }
            }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T> GenericLazyList(
    state: GenericLazyListState<T>,
    itemKey: (T) -> Any?,
    itemView: @Composable LazyItemScope.(index: Int, item: T) -> Unit,
    loadingIndicator: @Composable LazyItemScope.() -> Unit = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(36.dp),
            )
        }
    },
    background: Color = Md3.colorScheme.background,
) {
    val listState = rememberLazyListState()

    val refreshState = rememberPullRefreshState(
        refreshing = state.isRefreshing,
        onRefresh = {
            state.refresh()
        },
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .run { if (state.hasRefresh) this.pullRefresh(refreshState) else this }
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.size, key = {
                state.getOrNull(it)?.let(itemKey) ?: Unit
            }) { index ->
                state.getOrNull(index)?.let {
                    itemView(this, index, it)
                }
            }

            if (state.isLoading && (!state.isHasFailure || state.size == 0)) {
                item {
                    loadingIndicator.invoke(this)
                }
            }
        }

        if (state.size == 0 && state.isHasFailure) {
            ErrorStateScreen()
        }

        PullRefreshIndicator(state.isRefreshing, refreshState, Modifier.align(Alignment.TopCenter))
    }

    if (state.hasLoadMore) {
        listState.LoadMoreHandler(
            state = state,
        ) {
            state.load(it)
        }
    }
}

@Composable
private fun ErrorStateScreen() {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.7f)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, CenterVertically),
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.error), // Updated for consistency
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(80.dp),
                painter = painterResource(Res.drawable.close),
                contentDescription = null,
            )
        }

        Text(
            text = stringResource(Res.string.retry_txt),
            style = MaterialTheme.typography.bodyLarge, // Updated to match Material 3
            color = MaterialTheme.colorScheme.scrim,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun <T> rememberGenericLazyListState(
    source: ListDataStruct<T>,
    isHasFailure: Boolean = false,
    onFetch: ((page: Int) -> Unit)? = null,
    onRefresh: (() -> Unit)? = null,
): GenericLazyListState<T> {
    val state = remember<GenericLazyListState<T>> {
        GenericLazyListState(
            onFetch = onFetch,
            onRefresh = onRefresh
        )
    }
    LaunchedEffect(source) {
        state.setItems(source)
    }

    LaunchedEffect(isHasFailure) {
        if (isHasFailure) {
            state.setFailure()
        }
    }
    return state
}

@Stable
class GenericLazyListState<T> internal constructor(
    private val onFetch: ((page: Int) -> Unit)? = null,
    private val onRefresh: (() -> Unit)? = null,
) {
    var initialized by mutableStateOf(false)
    val hasLoadMore: Boolean get() = onFetch != null
    val hasRefresh: Boolean get() = onRefresh != null

    private var _items by mutableStateOf(ListDataStruct<T>())
    val items get() = _items
    val size get() = _items.currentSize
    val total get() = _items.dataCapacity
    val itemPerPage = DEFAULT_LIMIT

    fun getOrNull(index: Int): T? = runCatching { _items[index] }.getOrNull()

    // loading
    private var _isLoading by mutableStateOf(true)
    val isLoading get() = _isLoading

    private var _isHasFailure by mutableStateOf(false)
    val isHasFailure get() = _isHasFailure

    var currentPage: Int = -1

    private var isFetching = false

    fun load(page: Int) {
        if (!items.hasMore() || isFetching) return
        isFetching = true
        currentPage = page
        _isLoading = true
        this.onFetch?.invoke(page)
    }

    // refreshing
    private var _isRefreshing by mutableStateOf(false)
    val isRefreshing get() = _isRefreshing

    fun refresh() {
        _isHasFailure = false
        _isRefreshing = true
        _isLoading = true
        currentPage = 0
        _items = ListDataStruct()
        this.onRefresh?.invoke()
    }

    // update items
    fun setItems(list: ListDataStruct<T>) {
        _items = list
        _isLoading = false
        _isRefreshing = false
        currentPage = list.getCurrentPage(max(size - 1, 0))
        if (currentPage > 0) {
            initialized = true
        }
        isFetching = false
    }

    fun setFailure() {
        _isHasFailure = true
        _isLoading = false
        _isRefreshing = false
    }

    // reset
    fun idle() {
        _isLoading = false
        _isRefreshing = false
    }
}