package edu.bluejack17_2.water18.activity

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import edu.bluejack17_2.water18.R

class HomeActivity : Activity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var layout:DrawerLayout=findViewById(R.id.drawer_layout)
        val view:NavigationView=findViewById(R.id.navigation_view)
        view.setNavigationItemSelectedListener { item ->
            item.isChecked
        }

    }
}
