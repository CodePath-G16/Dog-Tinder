package com.example.dog_tinder

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import okhttp3.internal.http2.Header
import org.json.JSONObject

class DogProfile : AppCompatActivity() {
    private val apiUrl = "https://api.adoptapet.me/ap/10015514"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dog_profile)
        val client = AsyncHttpClient()
        client.get(apiUrl, object : JsonHttpResponseHandler() {
            fun onSuccess(statusCode: Int, headers: Array<Header>, response: JSONObject) {
                Log.d("API", "Request successful! Response: $response")
                val name = findViewById<TextView>(R.id.name)
                val description = findViewById<TextView>(R.id.description)
                val color = findViewById<TextView>(R.id.color)
                val age = findViewById<TextView>(R.id.age)
                val sex = findViewById<TextView>(R.id.gender)
                val imageView = findViewById<ImageView>(R.id.dogImage)

                val dogName = response.optString("name")
                val dogColor = response.optString("color")
                val dogDesc = response.optString("desc")
                val dogAge = response.optString("age")
                val dogSex = response.optString("sex")
                val dogImageUrl = response.optString("imageUrl")
                name.text = dogName
                description.text = dogDesc
                color.text = dogColor
                age.text = dogAge
                sex.text = dogSex
                //Glide.with(this@DogProfile)
                    //.load(dogImageUrl)
                    //.into(imageView)
            }

            fun onFailure(
                statusCode: Int,
                headers: Array<Header>?,
                throwable: Throwable,
                errorResponse: JSONObject?
            ) {
                Log.e("API", "Request failed with status code $statusCode")
                if (errorResponse != null) {
                    Log.e("API", "Error response: $errorResponse")
                }
            }

            override fun onFailure(p0: Int, p1: Headers?, p2: String?, p3: Throwable?) {
                TODO("Not yet implemented")
            }

            override fun onSuccess(p0: Int, p1: Headers?, p2: JSON?) {
                TODO("Not yet implemented")
            }
        })
    }
}