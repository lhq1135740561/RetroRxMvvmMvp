package com.yunge.myretrofitrxlmvp.ui.ac.login.data

import com.yunge.myretrofitrxlmvp.ui.ac.login.data.model.LoggedInUser
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {

        try {
            // TODO: handle loggedInUser authentication

            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
            if (username == "lhq@qq.com" && password == "123456") {
                return Result.Success(fakeUser)
            }

        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }

        return Result.Error(IOException("Error logging in", Exception()))
    }

    fun logout() {
        // TODO: revoke authentication
    }
}

