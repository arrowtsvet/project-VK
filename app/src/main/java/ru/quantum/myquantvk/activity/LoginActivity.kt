package ru.quantum.myquantvk.activity

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import ru.quantum.myquantvk.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var bindingActivityLogin: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        bindingActivityLogin = ActivityLoginBinding.inflate(layoutInflater)
        val viewLogin = bindingActivityLogin.root
        setContentView(viewLogin)

        animateVK()
        changeThemeClick()

        val nextActivity = bindingActivityLogin.toMainAct
        nextActivity.setOnClickListener {
            val toActivity = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(toActivity)
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
