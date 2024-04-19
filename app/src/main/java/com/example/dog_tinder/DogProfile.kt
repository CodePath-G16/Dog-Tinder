package com.example.dog_tinder

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray


class DogProfile : AppCompatActivity() {

    private val apiUrl = "https://api.thedogapi.com/v1/images/search?api_key=live_YfLcN5wasmrJjW4EFSjbhBFZvqUxTGRMYAYCDl68ZfmJs7Pk06jGE3T7hsmSUJh6"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dog_profile)


        val client = AsyncHttpClient()
        client.get(apiUrl, object : JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<Header>,
                response: JSONArray
            ) {

                Log.d("API", "Request successful! Response: $response")


                val nameTextView = findViewById<TextView>(R.id.dogName)
                val idTextView = findViewById<TextView>(R.id.dogId)
                val ageTextView = findViewById<TextView>(R.id.dogAge)
                val breedNameTextView = findViewById<TextView>(R.id.breedName)
                val weightTextView = findViewById<TextView>(R.id.weight)
                val heightTextView = findViewById<TextView>(R.id.height)
                val imageView = findViewById<ImageView>(R.id.dogImage)


                val dogData = response.getJSONObject(0) // Assuming you want the first dog's data

                // Retrieve dog data from the JSON object
                val dogId = dogData.optString("id")
                val dogAge = dogData.optString("age")
                val dogImageUrl = dogData.optString("url")


                val breedData = dogData.optJSONArray("breeds")?.getJSONObject(0)

                if (breedData != null) {
                    // Retrieve breed attributes from breedData JSON object
                    val breedName = breedData.optString("name")
                    val weight = breedData.optJSONObject("weight")?.optString("imperial")
                    val height = breedData.optJSONObject("height")?.optString("imperial")

                    // Set breed attributes to TextViews
                    breedNameTextView.text = "Breed: $breedName"
                    weightTextView.text = "Weight: $weight"
                    heightTextView.text = "Height: $height"

                }
                val petsData = dogData.optJSONArray("pets")?.getJSONObject(0)

                if (petsData != null) {
                    val dogName= breedData?.optString("name")
                    nameTextView.text = "Name: $dogName"}// Set dog data to TextViews

                idTextView.text = "ID: $dogId"
                ageTextView.text = "Age: $dogAge"



                // Load the dog image using Glide
                Glide.with(this@DogProfile)
                    .load(dogImageUrl)
                    .into(imageView)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>?,
                throwable: Throwable,
                errorResponse: JSONArray?
            ) {
                // Log the error
                Log.e("API", "Request failed with status code $statusCode")
                if (errorResponse != null) {
                    Log.e("API", "Error response: $errorResponse")
                }
            }
        })
    }
}
