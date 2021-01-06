package ru.quantum.myquantvk.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.quantum.myquantvk.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bindingActivityMain: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        bindingActivityMain = ActivityMainBinding.inflate(layoutInflater)
        val viewMain = bindingActivityMain.root
        setContentView(viewMain)

    }
}