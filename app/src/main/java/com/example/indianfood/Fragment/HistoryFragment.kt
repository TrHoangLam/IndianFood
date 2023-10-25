package com.example.indianfood.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.indianfood.R
import com.example.indianfood.adaptar.BuyAgainAdapter
import com.example.indianfood.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment() {
   private  lateinit var binding: FragmentHistoryBinding
    private lateinit var buyagainAdapter: BuyAgainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        setupRecyclerView()
        return binding.root
    }
    private fun setupRecyclerView(){
        val buyAgainFoodName = arrayListOf("Food 2","Food 2","Food 3")
        val buyAgainFoodPrice = arrayListOf("$10","$8","$9")
        val buyAgainFoodImage = arrayListOf(R.drawable.food_1,R.drawable.food_2,R.drawable.food_3)
        buyagainAdapter = BuyAgainAdapter(buyAgainFoodName,buyAgainFoodPrice,buyAgainFoodImage)
        binding.BuyAgainRecyclerView.adapter = buyagainAdapter
        binding.BuyAgainRecyclerView.layoutManager = LinearLayoutManager(requireContext())

    }
    companion object {

    }
}