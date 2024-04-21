package com.example.dog_tinder

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import okhttp3.internal.http2.Header
import org.json.JSONArray
import org.json.JSONObject

class DogProfile : AppCompatActivity() {
    private val id = "k0WvkHMmP"
    private val apiUrl = "https://api.thedogapi.com/v1/images/$id?api_key=live_YfLcN5wasmrJjW4EFSjbhBFZvqUxTGRMYAYCDl68ZfmJs7Pk06jGE3T7hsmSUJh6"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dog_profile)

        val client = AsyncHttpClient()
        client.get(apiUrl, object : JsonHttpResponseHandler() {

            override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    throwable: Throwable?
            ) {
                Log.d("Error", errorResponse)
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                Log.d("doggoAPI", "Request successful! Response: $json")

                val idTextView = findViewById<TextView>(R.id.dogId)
                val breedTextView = findViewById<TextView>(R.id.dogBreed)
                val weightTextView = findViewById<TextView>(R.id.dogWeight)
                val heightTextView = findViewById<TextView>(R.id.dogHeight)
                val imageView = findViewById<ImageView>(R.id.dogImage)
                val bredForTextView = findViewById<TextView>(R.id.dogBredfor)
                val temperamentTextView = findViewById<TextView>(R.id.dogTemperment)
                val lifeSpnTextView = findViewById<TextView>(R.id.dogLifeSpan)

                val dogId = json.jsonObject.getString("id")
                idTextView.text = dogId

                val dogImageUrl = json.jsonObject.getString("url")

                val breedData = json.jsonObject.getString("breeds")
                val breedDataArray = JSONArray(breedData)
                if (breedDataArray.length() > 0) {
                    val firstBreedObject = breedDataArray.getJSONObject(0)
                    val breedName = firstBreedObject.getString("name")
                    val temperament = firstBreedObject.getString("temperament")
                    val bredFor = firstBreedObject.getString("bred_for")
                    val lifeSpn = firstBreedObject.getString("life_span")
                    val heightObject = firstBreedObject.getJSONObject("height")
                    val weightObject = firstBreedObject.getJSONObject("weight")

                    val heightImperial = heightObject.getString("imperial")
                    val heightMetric = heightObject.getString("metric")

                    val weightImperial = weightObject.getString("imperial")
                    val weightMetric = weightObject.getString("metric")

                    breedTextView.text = breedName
                    temperamentTextView.text = temperament
                    bredForTextView.text = bredFor
                    lifeSpnTextView.text = lifeSpn
                    heightTextView.text = "Height: $heightImperial (Imperial), $heightMetric (Metric)"
                    weightTextView.text = "Weight: $weightImperial (Imperial), $weightMetric (Metric)"
                } else {
                    Log.d("karma", "No breed data found")
                }

                // Load the dog image using Glide
                Glide.with(this@DogProfile)
                    .load(dogImageUrl)
                    .into(imageView)

        }
    })
    }
}

