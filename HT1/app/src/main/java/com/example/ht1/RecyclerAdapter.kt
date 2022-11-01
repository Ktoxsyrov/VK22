package com.example.ht1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ht1.databinding.RecyclerItemBinding

class RecyclerAdapter(): RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    private var itemsCount = 0

    fun setCount(data: Int){
        itemsCount = data
    }

    fun getCount(): Int{
        return itemsCount
    }
    class MyViewHolder(binding: RecyclerItemBinding): RecyclerView.ViewHolder(binding.root){
        val text = binding.itemText
        val background = binding.frameId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context)
        ))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.text.text = position.toString()
//        holder.background.layoutParams = ViewGroup.LayoutParams(50, 50)
        if(position % 2 == 0)
            holder.background.setBackgroundResource(R.drawable.item_shape_red)
        else
            holder.background.setBackgroundResource(R.drawable.item_shape_blue)

    }

    override fun getItemCount(): Int {
        return itemsCount
    }
}