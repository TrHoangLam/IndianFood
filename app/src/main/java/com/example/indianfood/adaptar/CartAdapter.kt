package com.example.indianfood.adaptar

import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.indianfood.databinding.CartItemBinding

class CartAdapter(private val cartItems:MutableList<String>,
                  private val CartItemPrice:MutableList<String>,
                  private var CartImage:MutableList<Int>
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

private val itemQuatities = IntArray(cartItems.size){1}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(position)
    }


    override fun getItemCount(): Int = cartItems.size


    inner class CartViewHolder(private val binding: CartItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuatities[position]

                cartFoodName.text = cartItems[position]
                cartitemPrice.text = CartItemPrice[position]
                cartImage.setImageResource(CartImage[position])
                cartItemQuantity.text = quantity.toString()

                minusbutton.setOnClickListener{
                    deceaseQuantity(position)
                }

                plusbutton.setOnClickListener {
                    inceaseQuantity(position)
                }

                deleteButton.setOnClickListener {
                    val itemPosition = adapterPosition
                    if(itemPosition != RecyclerView.NO_POSITION ){
                        deleteItem(itemPosition)
                    }
                }

                }
            }
        private fun inceaseQuantity(position: Int)
        {
            if(itemQuatities[position]<10){
                itemQuatities[position]++
                binding.cartItemQuantity.text = itemQuatities[position].toString()
            }

        }
        private fun deceaseQuantity(position: Int) {
            if (itemQuatities[position] > 1) {
                itemQuatities[position]--
                binding.cartItemQuantity.text = itemQuatities[position].toString()
            }
        }

            private fun deleteItem(position: Int){
                cartItems.removeAt(position)
                CartImage.removeAt(position)
                CartItemPrice.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, cartItems.size)

            }
        }

    }

