package io.jeffchang.splitdiff

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation.findNavController
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp() =
            findNavController(this, R.id.activity_main_nav_host_fragment).navigateUp()
}
