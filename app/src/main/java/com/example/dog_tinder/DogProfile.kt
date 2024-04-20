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



                val idTextView = findViewById<TextView>(R.id.dogId)
                val breedNameTextView = findViewById<TextView>(R.id.breedName)
                val weightTextView = findViewById<TextView>(R.id.weight)
                val heightTextView = findViewById<TextView>(R.id.height)
                val imageView = findViewById<ImageView>(R.id.dogImage)
                val lifeSpanTextView = findViewById<TextView>(R.id.lifeSpan)
                val bredForTextView = findViewById<TextView>(R.id.bredFor)
                val breedGroupTextView = findViewById<TextView>(R.id.breedGroup)
                val temperamentTextView = findViewById<TextView>(R.id.temperament)

                val dogData = response.getJSONObject(0) // Assuming you want the first dog's data


                val dogId = dogData.optString("id")
                val dogImageUrl = dogData.optString("url")


                val breedData = dogData.optJSONArray("breeds")?.getJSONObject(0)

                if (breedData != null) {
                  
                    val breedName = breedData.optString("name")

                    val weight = breedData.optJSONObject("weight")?.optString("imperial")
                    val height = breedData.optJSONObject("height")?.optString("imperial")
                    val lifeSpn= breedData.optString("life_span")
                    val bredFor = breedData.optString("bred_for")
                    val breedGroup = breedData.optString("breed_group")
                    val temperament = breedData.optString("temperament")
                    
                    breedNameTextView.text = "Breed: $breedName"
                    weightTextView.text = "Weight: $weight"
                    heightTextView.text = "Height: $height"
                    lifeSpanTextView.text= "Life Span : $lifeSpn"
                    bredForTextView.text = "Bred for $bredFor"
                    breedGroupTextView.text = "Breed group: $breedGroup"
                    temperamentTextView.text= "Temperament: $temperament"

                }


                idTextView.text = "ID: $dogId"




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
