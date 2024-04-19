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
import com.google.android.material.bottomnavigation.BottomNavigationView
import okhttp3.Headers
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
 //   private lateinit var dogInfoAdapter: DogInfoAdapter
  //  private val dogInfoList = mutableListOf<DogInfo>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.welcome_screen)
        val logInButton: Button = findViewById(R.id.loginButton)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Set an onClickListener on the button
        logInButton.setOnClickListener {
            // Create an intent to navigate to MainActivity2
            //val intent = Intent(this, DogProfile::class.java)
            // Start MainActivity2 using the intent
            startActivity(intent)
        }
    }


    // commented out since recycler view will be in the home page (look under the "home" folder)
    /*
    private fun getDogInfo() {
        val client = AsyncHttpClient()
        val apiKey = "live_YfLcN5wasmrJjW4EFSjbhBFZvqUxTGRMYAYCDl68ZfmJs7Pk06jGE3T7hsmSUJh6" // for reference only
        val url = "https://api.thedogapi.com/v1/images/search?api_key=live_YfLcN5wasmrJjW4EFSjbhBFZvqUxTGRMYAYCDl68ZfmJs7Pk06jGE3T7hsmSUJh6"

        client.get(url, object : JsonHttpResponseHandler() {
             fun onSuccess(statusCode: Int, headers: Headers, response: JSONObject) {
                // Handle successful response
                try {
                    // Parse the response JSON
                    val dogInfo = DogInfo(
                        breeds = response.getJSONArray("breeds").let { breedsArray ->
                            List(breedsArray.length()) { i ->
                                val breedObject = breedsArray.getJSONObject(i)
                                Breed(
                                    weight = Weight(
                                        imperial = breedObject.getJSONObject("weight").getString("imperial"),
                                        metric = breedObject.getJSONObject("weight").getString("metric")
                                    ),
                                    height = Height(
                                        imperial = breedObject.getJSONObject("height").getString("imperial"),
                                        metric = breedObject.getJSONObject("height").getString("metric")
                                    ),
                                    id = breedObject.getInt("id"),
                                    name = breedObject.getString("name"),
                                    bred_for = breedObject.getString("bred_for"),
                                    breed_group = breedObject.getString("breed_group"),
                                    life_span = breedObject.getString("life_span"),
                                    temperament = breedObject.getString("temperament"),
                                    reference_image_id = breedObject.getString("reference_image_id")
                                )
                            }
                        },
                        id = response.getString("id"),
                        url = response.getString("url"),
                        width = response.getInt("width"),
                        height = response.getInt("height")
                    )
                    dogInfoList.add(dogInfo)
                    dogInfoAdapter.notifyDataSetChanged()
                } catch (e: JSONException) {
                    // Handle JSON parsing error
                    Log.e("Dog", "Error parsing JSON", e)
                }
            }

            override fun onFailure(p0: Int, p1: Headers?, p2: String?, p3: Throwable?) {
                Log.e("Dog Error", "Failed to fetch dog info: ")
            }

            override fun onSuccess(p0: Int, p1: Headers?, p2: JSON?) {
                Log.d("DOG API CALLED", p2.toString())
            }
        })
    }
}
*/
}
