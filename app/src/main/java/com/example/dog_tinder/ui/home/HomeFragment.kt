package com.example.dog_tinder.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
import org.json.JSONObject

class HomeFragment : Fragment() {

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

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize RecyclerView
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        dogInfoAdapter = DogInfoAdapter(dogInfoList)
        recyclerView.adapter = dogInfoAdapter

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        getDogInfo()

        return root
    }

    data class DogInfo(
        val breeds: List<Breed>,
        val id: String,
        val url: String,
        val width: Int,
        val height: Int
    )

    data class Breed(
        val weight: Weight,
        val height: Height,
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
     //   val apiKey = "live_YfLcN5wasmrJjW4EFSjbhBFZvqUxTGRMYAYCDl68ZfmJs7Pk06jGE3T7hsmSUJh6"
        val url = "https://api.thedogapi.com/v1/images/search?api_key=live_YfLcN5wasmrJjW4EFSjbhBFZvqUxTGRMYAYCDl68ZfmJs7Pk06jGE3T7hsmSUJh6"

        client.get(url, object : JsonHttpResponseHandler() { // FIX WHY this onSuccess can not override
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}