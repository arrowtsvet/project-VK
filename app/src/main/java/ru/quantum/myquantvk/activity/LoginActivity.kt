package ru.quantum.myquantvk.activity

import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.quantum.myquantvk.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var bindingActivityLogin: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        bindingActivityLogin = ActivityLoginBinding.inflate(layoutInflater)
        val view = bindingActivityLogin.root

        setContentView(view)

        animateVK()
    }

    private fun animateVK() {
        val loginAnimatedImage = bindingActivityLogin.loginAnimatedImage
        val drawable: Drawable = loginAnimatedImage.drawable
        if (drawable is Animatable) {
            (drawable as Animatable).start()
        }
    }
}
