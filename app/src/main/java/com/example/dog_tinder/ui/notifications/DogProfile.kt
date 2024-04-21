package com.example.dog_tinder.ui.notifications
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.dog_tinder.R
import okhttp3.Headers
import okhttp3.internal.http2.Header
import org.json.JSONArray


class DogProfile : AppCompatActivity() {

    private val apiUrl = "https://api.thedogapi.com/v1/images/search?api_key=live_YfLcN5wasmrJjW4EFSjbhBFZvqUxTGRMYAYCDl68ZfmJs7Pk06jGE3T7hsmSUJh6"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dog_profile)


        val client = AsyncHttpClient()
        client.get(apiUrl, object : JsonHttpResponseHandler() {
            override fun onFailure(p0: Int, p1: Headers?, p2: String?, p3: Throwable?) {
                TODO("Not yet implemented")
            }

            override fun onSuccess(statusCode: Int, p1: Headers?, p2: JSON?) {
                Log.d("LOOK HERE FOR API", "Request successful! Response: ${p2.toString()}")

                val dogData = p2?.jsonArray?.optJSONObject(0) // Access the first JSON object

                if (dogData != null) {
                    val idTextView = findViewById<TextView>(R.id.doggoid)
                    val breedNameTextView = findViewById<TextView>(R.id.dogBreed)
                    val weightTextView = findViewById<TextView>(R.id.dogWeight)
                    val heightTextView = findViewById<TextView>(R.id.dogHeight)
                    val imageView = findViewById<ImageView>(R.id.dogImage)
                    val lifeSpanTextView = findViewById<TextView>(R.id.lifeSpan)
                    val bredForTextView = findViewById<TextView>(R.id.dogBredfor)
                    val breedGroupTextView = findViewById<TextView>(R.id.breedGroup)
                    val temperamentTextView = findViewById<TextView>(R.id.dogTemperment)

                    val dogId = dogData.optString("id")
                    val dogImageUrl = dogData.optString("url")

                    val breedData = dogData.optJSONArray("breeds")?.optJSONObject(0)

                    if (breedData != null) {
                        val breedName = breedData.optString("name")
                        val weight = breedData.optJSONObject("weight")?.optString("imperial")
                        val height = breedData.optJSONObject("height")?.optString("imperial")
                        val lifeSpan = breedData.optString("life_span")
                        val bredFor = breedData.optString("bred_for")
                        val breedGroup = breedData.optString("breed_group")
                        val temperament = breedData.optString("temperament")

                        breedNameTextView.text = "Breed: $breedName"
                        weightTextView.text = "Weight: $weight"
                        heightTextView.text = "Height: $height"
                        lifeSpanTextView.text = "Life Span: $lifeSpan"
                        bredForTextView.text = "Bred for: $bredFor"
                        breedGroupTextView.text = "Breed group: $breedGroup"
                        temperamentTextView.text = "Temperament: $temperament"
                    }

                    idTextView.text = "ID: $dogId"

                    // Load the dog image using Glide
                    Glide.with(this@DogProfile)
                        .load(dogImageUrl)
                        .into(imageView)
                }
            }



            fun onFailure(
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