package ru.quantum.myquantvk.activity

import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import ru.quantum.myquantvk.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        animateVKicon()
    }

    private fun animateVKicon() {
        val rocketImage = findViewById<ImageView>(R.id.login_animatedImage)
        val drawable: Drawable = rocketImage.drawable
        if (drawable is Animatable) {
            (drawable as Animatable).start()
        }
    }
}
