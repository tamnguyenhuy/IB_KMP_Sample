package com.initium.assignment.model

const val DEFAULT_LIMIT = 20

/**
 * Data structure to hold list data.
 *
 * @param R The type of the data.
 * @property dataCapacity The total number of data available.
 * @property dataList The list of data.
 * @property itemPerPage The number of items per page.
 * @property defaultItemSize The default number of items.
 */
data class ListDataStruct<R>(
    var dataCapacity: Long = 0,
    var dataList: List<R> = emptyList(),
    var itemPerPage: Int = DEFAULT_LIMIT,
    private val defaultItemSize: Int = 0
) {

    fun append(list: ListDataStruct<R>): ListDataStruct<R> {
        return this.copy(
            dataCapacity = list.dataCapacity + defaultItemSize,
            dataList = dataList + list.dataList,
            defaultItemSize = defaultItemSize
        )
    }

    fun add(index: Int, item: R): ListDataStruct<R> {
        return ListDataStruct(
            dataCapacity = dataCapacity + 1,
            dataList = dataList.toMutableList().apply {
                add(index, item)
            },
            defaultItemSize = defaultItemSize
        )
    }

    fun addDefaultItem(item: R): ListDataStruct<R> {
        return ListDataStruct(
            dataCapacity = dataCapacity + 1,
            dataList = dataList.toMutableList().apply {
                add(0, item)
            },
            defaultItemSize = defaultItemSize + 1
        )
    }

    fun remove(predict: (R) -> Boolean): ListDataStruct<R> {
        return ListDataStruct(
            dataCapacity = dataCapacity + 1,
            dataList = dataList.toMutableList().apply {
                val index = indexOfFirst {
                    predict.invoke(it)
                }
                if (index != -1) removeAt(index)
            },
            defaultItemSize = defaultItemSize
        )
    }

    operator fun get(index: Int) = dataList[index]

    val currentSize: Int get() = dataList.size

    fun hasMore(): Boolean {
        if (dataList.size <= defaultItemSize && dataCapacity <= defaultItemSize) return true
        return (dataList.size - defaultItemSize) < (dataCapacity - defaultItemSize)
    }

    fun getCurrentPage(size: Int, itemsPerPage: Int = DEFAULT_LIMIT): Int {
        if (size <= defaultItemSize) return -1
        return (size - defaultItemSize) / itemsPerPage
    }

    override fun toString(): String {
        return "ListData(size: ${dataList.size}, total: $dataCapacity, type: ${
            dataList.firstOrNull()?.let { it::class.simpleName }
        }), defaultItemSize: $defaultItemSize"
    }

    fun filter(predict: (R) -> Boolean): ListDataStruct<R> {
        return ListDataStruct(
            dataCapacity = dataCapacity + defaultItemSize,
            dataList = dataList.filter(predict),
            defaultItemSize = defaultItemSize
        )
    }
}

inline fun <R> defaultDataList(list: List<R>): ListDataStruct<R> =
    ListDataStruct(dataCapacity = list.size.toLong(), dataList = list)