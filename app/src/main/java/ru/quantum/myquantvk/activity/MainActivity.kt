package ru.quantum.myquantvk.activity

import android.graphics.Color.TRANSPARENT
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import com.mindinventory.midrawer.MIDrawerView
import ru.quantum.myquantvk.R
import ru.quantum.myquantvk.databinding.ActivityMainBinding
import ru.quantum.myquantvk.databinding.ContentMainBinding


class MainActivity : AppCompatActivity() {
    val TAG = "DrawerToolbar"
    private lateinit var navController: NavController
    private lateinit var mBindingActivityMain: ActivityMainBinding
    private lateinit var mBindingContentMain: ContentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBindingActivityMain = ActivityMainBinding.inflate(layoutInflater)
        val viewMain = mBindingActivityMain.root
        setContentView(viewMain)

        setToolbar()
        //navController = findNavController(R.id.nav_host_fragment)
        //val navigationView = mBindingContentMain.navigationView
        //NavigationUI.setupWithNavController(navigationView, navController)

    }

    private fun setToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        mBindingActivityMain.drawerLayout.setScrimColor(TRANSPARENT)
        setSupportActionBar(toolbar)


        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)

        mBindingActivityMain.drawerLayout.setMIDrawerListener(object : MIDrawerView.MIDrawerEvents {
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                mBindingActivityMain.drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}