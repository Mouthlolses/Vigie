package com.seuvigie.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.seuvigie.data.dao.UserDao
import com.seuvigie.data.database.AppDatabase
import com.seuvigie.data.model.User
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class EntityReadWriteTest {
    private lateinit var userDao: UserDao
    private lateinit var db: AppDatabase

    object TestUtil {

        fun createUser(id: Long): User {
            return User(
                id = id,
                name = "user$id",
                email = "mockk@gmail.com",
                phone = "88993289575",
                password = "password1321"
            )
        }
    }


    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).allowMainThreadQueries().build()

        userDao = db.userDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeUserAndReadInList() = runTest {
        val user = TestUtil.createUser(3).apply {
            name = "George"
        }
        userDao.insertUser(user)
        val byName = userDao.findUsersByName("George")
        assertEquals(user, byName.first())
        println("Usuário retornado: $byName")
    }


}