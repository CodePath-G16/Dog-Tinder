package com.example.dog_tinder.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.dog_tinder.databinding.FragmentNotificationsBinding
import okhttp3.Headers
import org.json.JSONArray
import com.example.dog_tinder.R

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve breed ID from arguments
        arguments?.let {
            val breedId = it.getString("BREED_ID")
            // Log the breed ID
            Log.d("BreedID in PROFILE PAGE", "Breed ID: $breedId")

            // Create apiUrl inside the let block
            val apiUrl = "https://api.thedogapi.com/v1/images/$breedId?api_key=live_YfLcN5wasmrJjW4EFSjbhBFZvqUxTGRMYAYCDl68ZfmJs7Pk06jGE3T7hsmSUJh6"

            // Call fetchData with the apiUrl
            fetchData(apiUrl)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    private fun fetchData(apiUrl: String) {
        val client = AsyncHttpClient()
        client.get(apiUrl, object : JsonHttpResponseHandler() {

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("DOG PROFILE NO PULL", errorResponse)
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                Log.d("doggoAPI", "Request successful! Response: $json")

                val idTextView = binding.dogId
                val breedTextView = binding.dogBreed
                val weightTextView = binding.dogWeight
                val heightTextView = binding.dogHeight
                val imageView = binding.dogImage
                val bredForTextView = binding.dogBredfor
                val temperamentTextView = binding.dogTemperment
                val lifeSpnTextView = binding.dogLifeSpan

                // Your JSON parsing and UI update code here
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
                Glide.with(this@NotificationsFragment)
                    .load(dogImageUrl)
                    .into(imageView)
            }
        })
    }

    override fun onDestroyView() {
        Log.d("PROFILE DESTROYED", "calling_onDestroy")
        super.onDestroyView()
        _binding = null
    }
}
