package com.example.indianfood.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.indianfood.CongratsBottomSheet
import com.example.indianfood.PayOutActivity
import com.example.indianfood.R
import com.example.indianfood.adaptar.CartAdapter
import com.example.indianfood.databinding.FragmentCartBinding


class CartFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCartBinding.inflate(inflater,container,false)

        val cartFoodName = listOf("Pizza","Noodle","Burger","Sandwich","Pizza","Noodle")
        val cartitemPrice = listOf("$9","$10","$8","$5","$9","$10")
        val cartImage = listOf(

            R.drawable.food_1, R.drawable.food_2, R.drawable.food_3, R.drawable.food_4,R.drawable.food_1, R.drawable.food_2
        )
        val adapter = CartAdapter(ArrayList(cartFoodName),ArrayList(cartitemPrice),ArrayList(cartImage))
        binding.cartRecycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecycleView.adapter = adapter

        binding.proceedButton.setOnClickListener {
            val intent = Intent(requireContext(),PayOutActivity::class.java)
            startActivity(intent)
        }

        val bottomSheetDeialog = CongratsBottomSheet
        return binding.root
    }

    companion object {


    }
}