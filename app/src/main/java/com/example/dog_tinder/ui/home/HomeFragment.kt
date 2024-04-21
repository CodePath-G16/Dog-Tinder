package com.example.dog_tinder.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dog_tinder.databinding.FragmentHomeBinding
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONException


class HomeFragment : Fragment() {

    //USe FragmentHomeBinding for view binding
    private var _binding: FragmentHomeBinding? = null
    private lateinit var dogInfoAdapter: DogInfoAdapter
    private val dogInfoList = mutableListOf<DogInfo>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        //Inflate the layout and initialize the binding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize RecyclerView
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        dogInfoAdapter = DogInfoAdapter(dogInfoList)
        recyclerView.adapter = dogInfoAdapter



        // Observe the dogInfoList LiveData from HomeViewModel
        homeViewModel.dogInfoList.observe(viewLifecycleOwner) { dogInfoList ->
            // Update the adapter with the new dogInfoList
            this.dogInfoList.clear()
            this.dogInfoList.addAll(dogInfoList)
            dogInfoAdapter.notifyDataSetChanged()
        }

        getDogInfo()

        return root
    }

    data class DogInfo(
        val breeds: List<Breed>,
        val id: String,
        val url: String,
        val width: Int,
        val height: Int,
        val breedName: String,
        val bredFor: String,
        val breedGroup: String

    ) {

    }

    data class Breed(
        val weight: String,
        val height: String,
        val id: Int,
        val name: String,
        val bred_for: String,
        val breed_group: String,
        val life_span: String,
        val temperament: String,
        val reference_image_id: String
    )

    data class Weight(
        val imperial: String,
        val metric: String
    )

    data class Height(
        val imperial: String,
        val metric: String
    )




    private fun getDogInfo() {
        val client = AsyncHttpClient()
        val apiKey = "live_YfLcN5wasmrJjW4EFSjbhBFZvqUxTGRMYAYCDl68ZfmJs7Pk06jGE3T7hsmSUJh6"
        val url = "https://api.thedogapi.com/v1/images/search?api_key=$apiKey"

        client.get(url, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e("Dog Error", "Failed to fetch dog info: ${throwable?.message}")
            }
            /* override fun onSuccess(statusCode: Int, header: Headers?, response: JSON?) {

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
            */

            //was not able to figure out how to make the current code work so I tried to follow the format from my previous project
            //if able to make it work, then will take this out

            override fun onSuccess(statusCode: Int, headers: Headers?, response: JSON?) {
                try {
                    val jsonArray = response?.jsonArray
                    jsonArray?.let {
                        for (i in 0 until it.length()) {
                            val dog = it.getJSONObject(i)
                            val breedsArray = dog.getJSONArray("breeds")
                            if (breedsArray.length() > 0) {
                                val breedObject = breedsArray.getJSONObject(0)
                                val breedName = breedObject.getString("name")
                                val bredFor = breedObject.optString("bred_for", "Unknown")
                                val breedGroup = breedObject.optString("breed_group", "Unknown")
                                val height = breedObject.getJSONObject("height").getString("metric")
                                val width = breedObject.getJSONObject("weight").getString("metric")
                                val url = dog.getString("url")

                                // Create a DogInfo object and add it to the list
                                val dogInfo = DogInfo(
                                    breeds = emptyList(),
                                    id = dog.getString("id"),
                                    url = url,
                                    width = width.toIntOrNull() ?: 0,
                                    height = height.toIntOrNull() ?: 0,
                                    breedName = breedName,
                                    bredFor = bredFor,
                                    breedGroup = breedGroup
                                )
                                dogInfoList.add(dogInfo)
                            }
                        }
                        // Notify the adapter of the data change
                        dogInfoAdapter.notifyDataSetChanged()
                    }
                } catch (e: JSONException) {
                    Log.e("DogTinder", "Error parsing JSON: ${e.message}")
                }
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}