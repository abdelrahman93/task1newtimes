package com.comapny.task1.ui

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.comapny.task1.R
import com.comapny.task1.customViews.CustomBottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val navView: CustomBottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_data_usage,
                R.id.navigation_home, R.id.navigation_dashboard))
        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)

        button.setOnClickListener(View.OnClickListener {
            val view = layoutInflater.inflate(R.layout.bottom_sheet_add_money, null)
            val dialog = BottomSheetDialog(this)
            dialog.setCanceledOnTouchOutside(false)
            dialog.setContentView(view)
            dialog.show()
        })

    }
}
