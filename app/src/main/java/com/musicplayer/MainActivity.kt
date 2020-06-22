package com.musicplayer

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AbstractPermissionActivity() {

    override val desiredPermissions =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) arrayOf()
        else arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

    override fun onPermissionDenied() {
        Toast.makeText(this, "Lack of permissions", Toast.LENGTH_LONG)
            .show()
        finish()
    }

    override fun onReady(state: Bundle?) {
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_musicBrowsing, R.id.navigation_musicManagement, R.id.navigation_musicPlaying
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}
