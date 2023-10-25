package com.example.indianfood.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.indianfood.R
import com.example.indianfood.adaptar.MenuAdapter
import com.example.indianfood.databinding.FragmentSearchBinding
import com.example.indianfood.model.MenuItem

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: MenuAdapter
    private val originalMenuItems: List<MenuItem> = listOf(
        MenuItem("Pizza", "$9","", R.drawable.food_1),
        MenuItem("Noodle", "$10","", R.drawable.food_2),
        MenuItem("Burger", "$8", "",R.drawable.food_3),
        MenuItem("Sandwich", "$5","", R.drawable.food_4),
        // Add more menu items here
    )

    private val filteredMenuItems: MutableList<MenuItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        adapter = MenuAdapter(filteredMenuItems, requireContext())
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adapter

        // Set up the SearchView
        setUpSearchView()

        // Initially, display all menu items
        showAllMenuItems()

        return binding.root
    }

    private fun setUpSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterMenuItems(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterMenuItems(newText)
                return true
            }
        })
    }

    private fun showAllMenuItems() {
        filteredMenuItems.clear()
        filteredMenuItems.addAll(originalMenuItems)
        adapter.notifyDataSetChanged()
    }

    private fun filterMenuItems(query: String?) {
        filteredMenuItems.clear()

        if (query.isNullOrBlank()) {
            // If the query is empty, display all menu items
            filteredMenuItems.addAll(originalMenuItems)
        } else {
            // Filter menu items based on the query
            val lowerCaseQuery = query.toLowerCase()
            for (item in originalMenuItems) {
                if (item.foodName!!.toLowerCase().contains(lowerCaseQuery)) {
                    filteredMenuItems.add(item)
                }
            }
        }

        adapter.notifyDataSetChanged()
    }
}
