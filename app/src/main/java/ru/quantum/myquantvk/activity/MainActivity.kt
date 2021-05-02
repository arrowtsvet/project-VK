package ru.quantum.myquantvk.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.Drawer
import ru.quantum.myquantvk.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var bindingActivityMain: ActivityMainBinding
    private lateinit var mDrawer: Drawer
    private lateinit var mHeader: AccountHeader
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        bindingActivityMain = ActivityMainBinding.inflate(layoutInflater)
        val viewMain = bindingActivityMain.root
        setContentView(viewMain)

        /*val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        mDrawer = DrawerBuilder()
             .withActivity(this)
             .withToolbar(toolbar)
             .withActionBarDrawerToggle(true)
             .withHeader(R.layout.drawer_header)
             .addDrawerItems(
                 PrimaryDrawerItem().withIdentifier(100)
                     .withIconTintingEnabled(true)
                     .withName("Настройки")
                     .withSelectable(false)
             )
             .build()*/
    }
    override fun onBackPressed() {
        AlertDialog.Builder(this).apply {
            setTitle("Подтверждение")
            setMessage("Нажмите 'Да', если хотите разлогиниться")

            setPositiveButton("Да") { _, _ ->
                super.onBackPressed()
            }

            setNegativeButton("Нет"){_, _ ->
                // if user press no, then return the activity
                Toast.makeText(this@MainActivity, "Thank you",
                    Toast.LENGTH_LONG).show()
            }
            setCancelable(true)
        }.create().show()
    }

    /*override fun onStart() {
         super.onStart()
         initFields()
         initFunc()
     }

     private fun initFunc() {
         setSupportActionBar(mToolbar)
         createHeader()
         createDrawer()
     }

     private fun createDrawer() {
         mDrawer = DrawerBuilder()
             .withActivity(this)
             .withToolbar(mToolbar)
             .withActionBarDrawerToggle(true)
             .withSelectedItem(-1)
             .withAccountHeader(mHeader)
             .addDrawerItems(
                 PrimaryDrawerItem().withIdentifier(100)
                     .withIconTintingEnabled(true)
                     .withName("Настройки")
                     .withSelectable(false)
             ).build()
     }

     private fun createHeader() {
         mHeader = AccountHeaderBuilder()
             .withActivity(this)

             .withHeaderBackground(R.drawable.header_image)
             .addProfiles(
                 ProfileDrawerItem().withName("Quant")
             ).build()
     }

     private fun initFields() {
         mToolbar = bindingActivityMain.toolbar
     }*/
}