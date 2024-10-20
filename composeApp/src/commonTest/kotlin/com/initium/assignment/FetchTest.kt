package com.initium.assignment

import com.initium.assignment.base.Base
import com.initium.assignment.domain.services.InitialService
import com.initium.assignment.domain.services.UserService
import kotlinx.coroutines.runBlocking
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FetchTest: Base() {

    private val mUserService =  UserService.instance
    @BeforeTest
    fun setup() {
        val context = getAppContext()
        InitialService.instance.initialize(context)
    }

    @AfterTest
    fun tearDown() {
        mUserService.removeALlUsers()
    }

    @Test
    fun fetchUser() = runBlocking {
        val users = mUserService.fetchUser(20, 0)
        val localUsers = mUserService.fetchAllUsers()
        assertEquals(users.dataList.size, localUsers.size)
        mUserService.removeALlUsers()
    }

    @Test
    fun getDetailUser() = runBlocking {
        val name = "pjhyett"
        val userDetail = mUserService.fetchUserDetail(userName = name)
        assertEquals(userDetail.username, name)

        val userDetailLocal = mUserService.getUser(userDetail.username)

        assertEquals(userDetailLocal?.username, name)
        assertTrue(userDetailLocal?.isInDetail ?: false)
    }
}