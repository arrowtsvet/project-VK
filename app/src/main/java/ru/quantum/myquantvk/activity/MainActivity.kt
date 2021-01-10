package ru.quantum.myquantvk.activity

import android.graphics.Color.TRANSPARENT
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.mindinventory.midrawer.MIDrawerView
import ru.quantum.myquantvk.R
import ru.quantum.myquantvk.databinding.ActivityMainBinding
import ru.quantum.myquantvk.databinding.AppBarMainBinding


class MainActivity : AppCompatActivity() {
    val TAG = "MIDrawerActivity"
    private lateinit var bindingActivityMain: ActivityMainBinding
    private lateinit var mBindingAppBar: AppBarMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingActivityMain = ActivityMainBinding.inflate(layoutInflater)
        val viewMain = bindingActivityMain.root
        setContentView(viewMain)

        setToolbar()

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setHomeAsUpIndicator(R.drawable.ic_action_name)
        bindingActivityMain.drawerLayout.setMIDrawerListener(object : MIDrawerView.MIDrawerEvents {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                Log.d(TAG, "Drawer Opened")
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                Log.d(TAG, "Drawer closed")
            }
        })
    }

    private fun setToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        bindingActivityMain.drawerLayout.setScrimColor(TRANSPARENT)
        setSupportActionBar(toolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                bindingActivityMain.drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}