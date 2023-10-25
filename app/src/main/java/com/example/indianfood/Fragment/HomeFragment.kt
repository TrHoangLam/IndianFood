package com.example.indianfood.Fragment

import android.media.RouteListingPreference.Item
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView.ScaleType
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.indianfood.MenuBootomSheetFragment

import com.example.indianfood.R
import com.example.indianfood.adaptar.MenuAdapter
import com.example.indianfood.adaptar.PopularAdapter
import com.example.indianfood.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        binding.viewAllMenu.setOnClickListener {
            val bootomSheetDialog = MenuBootomSheetFragment()
            bootomSheetDialog.show(parentFragmentManager,"Test")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.pic_banner1, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.pic_banner2, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.pic_banner3, ScaleTypes.FIT))

        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)
        imageSlider.setImageList(imageList, ScaleTypes.FIT)
        imageSlider.setItemClickListener(object :ItemClickListener{
            override fun doubleClick(position: Int) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(position: Int) {
                val  itemPosition = imageList[position]
                val itemMessage= "Selected Image $position"
                Toast.makeText(requireContext(),itemMessage,Toast.LENGTH_SHORT).show()
            }
        })
        val foodName = listOf("Pizza","Noodle","Burger","Sandwich",)
        val Price = listOf("$9","$10","$8","$5")
        val populerFoodImages =
            listOf(R.drawable.food_1, R.drawable.food_2, R.drawable.food_3, R.drawable.food_4)
        val adapter = PopularAdapter(foodName,Price,populerFoodImages,requireContext())
        binding.PoppulerRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.PoppulerRecycler.adapter = adapter

    }

    companion object{

    }
}
