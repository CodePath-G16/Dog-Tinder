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
    private val id = "dhXSUo4aC"
    private val apiUrl = "https://api.thedogapi.com/v1/images/$id?api_key=live_YfLcN5wasmrJjW4EFSjbhBFZvqUxTGRMYAYCDl68ZfmJs7Pk06jGE3T7hsmSUJh6"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dog_profile)

        val client = AsyncHttpClient()
        client.get(apiUrl, object : JsonHttpResponseHandler() {
            override fun onFailure(p0: Int, p1: Headers?, p2: String?, p3: Throwable?) {
                Log.d("doggoAPI", "Failed $p2")
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


                /*
                val dogId = json.getString("id")
                val dogImageUrl = json.getString("url")
                val weight = json.getString("weight")
                val height = json.getString("height")
                val breedData = json.getJSONArray("breeds")

                //val breedName = breedData.getString("name")
                //val bredFor = breedData.optString("bred_for")
                //val temperament = breedData.optString("temperament")

                for (i in 0 until breedData.length()) {
                    val breedObject = breedData.getJSONObject(i)
                    val breedName = breedObject.getString("name")

                idTextView.text = "ID: $dogId"
                breedTextView.text = "Breed: $breedName"
                weightTextView.text = "Weight: $weight"
                heightTextView.text = "Height: $height"

                //bredForTextView.text = "Bred for $bredFor"
                //temperamentTextView.text= "Temperament: $temperament"

                // Load the dog image using Glide
                Glide.with(this@DogProfile)
                    .load(dogImageUrl)
                    .into(imageView)
            }
*/
            fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Error", errorResponse)
            }

            }
        })
    }
}
