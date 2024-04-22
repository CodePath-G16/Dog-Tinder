package com.example.dog_tinder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.dog_tinder.databinding.ActivityMainBinding
import com.example.dog_tinder.ui.home.DogInfoAdapter
import com.example.dog_tinder.ui.notifications.DogProfile
import com.google.android.material.bottomnavigation.BottomNavigationView
import okhttp3.Headers
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //  private lateinit var dogInfoAdapter: DogInfoAdapter
    //  private val dogInfoList = mutableListOf<DogInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToWelcomeScreen()
    }

    private fun navigateToWelcomeScreen() {

        startActivity(Intent(this, DogProfile::class.java))
        finish()
        /*
        setContentView(R.layout.dog_profile)

        val logInButton: Button = findViewById(R.id.loginButton)

        logInButton.setOnClickListener {
            navigateToHomeScreen()
        }
*/

        /*
       // Call DogProfile activity ONLY FOR TESTING
        val intent = Intent(this, DogProfile::class.java)
        startActivity(intent)
        */
    }

    private fun navigateToHomeScreen() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun navigateToWelcomeScreen() {
        setContentView(R.layout.welcome_screen)
        val logInButton: Button = findViewById(R.id.loginButton)


        logInButton.setOnClickListener {
            navigateToHomeScreen()
        }
    }

}
