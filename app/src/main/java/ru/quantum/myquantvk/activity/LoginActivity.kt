package ru.quantum.myquantvk.activity

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.quantum.myquantvk.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var bindingActivityLogin: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        bindingActivityLogin = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bindingActivityLogin.root)

        animateVK()
        changeThemeClick()

        val vkScopes: ArrayList<VKScope> = arrayListOf(
            VKScope.WALL,
            VKScope.FRIENDS,
            VKScope.PHOTOS,
            VKScope.GROUPS,
            VKScope.STATUS,
            VKScope.STATS,
            VKScope.OFFLINE
        )


        bindingActivityLogin.toMainAct.setOnClickListener {
            bindingActivityLogin.toMainAct.visibility = View.GONE
            bindingActivityLogin.progressBar.visibility = View.VISIBLE
            CoroutineScope(Dispatchers.Main).launch {
                delay(2000)
                VK.login(this@LoginActivity, vkScopes)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val callback = object : VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                Toast.makeText(
                    applicationContext,
                    "Пользователь авторизован",
                    Toast.LENGTH_LONG
                ).show()
                bindingActivityLogin.progressBar.visibility = View.GONE
                bindingActivityLogin.toMainAct.visibility = View.VISIBLE
                val nextToAct = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(nextToAct)
            }

            override fun onLoginFailed(errorCode: Int) {
                Toast.makeText(
                    applicationContext,
                    "Пользователь не авторизован",
                    Toast.LENGTH_LONG
                ).show()
                bindingActivityLogin.progressBar.visibility = View.GONE
                bindingActivityLogin.toMainAct.visibility = View.VISIBLE
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            bindingActivityLogin.progressBar.visibility = View.GONE
            bindingActivityLogin.toMainAct.visibility = View.VISIBLE
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun animateVK() {
        val loginAnimatedImage = bindingActivityLogin.loginAnimatedImage
        val drawable: Drawable = loginAnimatedImage.drawable
        if (drawable is Animatable) {
            (drawable as Animatable).start()
        }
    }

    private fun changeThemeClick() {

        val appSettingPrefs: SharedPreferences = getSharedPreferences("AppSettingPrefs", 0)
        val sharedPrefsEdit: SharedPreferences.Editor = appSettingPrefs.edit()
        val isNightModeOn: Boolean = appSettingPrefs.getBoolean("NightMode", false)
        val btnChangeTheme = bindingActivityLogin.switchTheme

        if (isNightModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        btnChangeTheme.setOnClickListener(View.OnClickListener {
            if (isNightModeOn) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPrefsEdit.putBoolean("NightMode", false)
                sharedPrefsEdit.apply()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPrefsEdit.putBoolean("NightMode", true)
                sharedPrefsEdit.apply()
            }
        })
    }

}
