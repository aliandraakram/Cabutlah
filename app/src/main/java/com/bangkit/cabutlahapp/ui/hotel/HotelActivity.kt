package com.bangkit.cabutlahapp.ui.hotel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.cabutlahapp.R
import com.bangkit.cabutlahapp.data.model.Hotel
import com.bangkit.cabutlahapp.data.model.Restaurant
import com.bangkit.cabutlahapp.databinding.ActivityRestaurantBinding
import com.bangkit.cabutlahapp.ui.restaurant.RestaurantAdapter
import com.bangkit.cabutlahapp.viewModel.ViewmodelFactory
import com.google.firebase.database.*

class HotelActivity : AppCompatActivity() {

    private lateinit var Rv : RecyclerView
    private lateinit var viewmodel: HotelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewmodelFactory.getInstance()
        viewmodel = ViewModelProvider(this, factory)[HotelViewModel::class.java]

        Rv = findViewById(R.id.hotel_list)
        Rv.layoutManager =  LinearLayoutManager(this)
        Rv.setHasFixedSize(
            true )
        getDataFromFirebase()
    }

    private fun getDataFromFirebase() {
        viewmodel.getHotel(this).observe(this, {
            if (it != null){
                Rv.adapter = HotelAdapter(it)
            }
        })

    }
}