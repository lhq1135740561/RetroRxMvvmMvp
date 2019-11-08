package com.yunge.myretrofitrxlmvp.ui.ac.login.ui.login

import android.app.Activity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.yunge.myretrofitrxlmvp.R
import com.yunge.myretrofitrxlmvp.loginmvp.User
import com.yunge.myretrofitrxlmvp.loginmvp.model.UserBiz
import com.yunge.myretrofitrxlmvp.loginmvp.presenter.UserLoginPresenter
import com.yunge.myretrofitrxlmvp.loginmvp.utils.LoginDao
import com.yunge.myretrofitrxlmvp.loginmvp.view.IUserLoginView
import com.yunge.myretrofitrxlmvp.ui.ac.BaseActivity
import com.yunge.myretrofitrxlmvp.ui.ac.NewsActivity
import com.yunge.myretrofitrxlmvp.ui.ac.WeatherActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity() , IUserLoginView{

    //跳转到天气页面
    override fun loginWeather() {
        startActivity(WeatherActivity::class.java)
    }

    override fun getUserName(): String {
        return username.text.toString()
    }

    override fun getUserPassword(): String {
        return password.text.toString()
    }

    override fun clearUserName() {
        username.text = null
    }

    override fun clearUserPassword() {
        password.text = null
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.GONE
    }

    override fun toMainActivity(user: User) {
        //登录成功，跳转到新闻页面
        startActivity(NewsActivity::class.java)

        //存储本地用户名和密码
        LoginDao.cacheLoginUserNamePassword(username = user.userName,password = user.userPassword,context = this)

        Toast.makeText(this,user.userName + "，登录成功",Toast.LENGTH_SHORT).show()
    }

    override fun showFailedError(error: String) {

        Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
    }

    private lateinit var loginViewModel: LoginViewModel

    private val userLoginPresenter = UserLoginPresenter(UserBiz(),this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val loginClear = findViewById<Button>(R.id.login_clear)
        val loginWeather = findViewById<Button>(R.id.login_weather)
        val loading = findViewById<ProgressBar>(R.id.loading)



        loginViewModel = ViewModelProviders.of(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            //登录
            login.setOnClickListener {
                userLoginPresenter.login()
            }

            //清除
            loginClear.setOnClickListener {
                userLoginPresenter.clear()
            }

            //跳转到天气页面
            loginWeather.setOnClickListener {
                userLoginPresenter.loginWeather()
            }
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }


    override fun onResume() {
        super.onResume()
        val userName = LoginDao.getCacheLoginUserName(this)
        val userPassword = LoginDao.getCacheLoginUserPassword(this)
        if (userName != null && userPassword != null){
            username.text = Editable.Factory.getInstance().newEditable(userName)
            password.text = Editable.Factory.getInstance().newEditable(userPassword)
        }
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })

}
