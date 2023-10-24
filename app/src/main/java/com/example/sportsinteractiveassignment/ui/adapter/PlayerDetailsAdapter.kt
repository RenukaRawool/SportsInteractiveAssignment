package com.example.sportsinteractiveassignment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsinteractiveassignment.R
import com.example.sportsinteractiveassignment.data.model.PlayersDetailsModel
import com.example.sportsinteractiveassignment.databinding.ResPlayerListItemBinding


class PlayerDetailsAdapter(private val onItemClick: (PlayersDetailsModel) -> Unit) : RecyclerView.Adapter<PlayerDetailsAdapter.MyViewHolder>() {

    val itemList: MutableList<PlayersDetailsModel> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val binding = ResPlayerListItemBinding.inflate(inflator,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemList[position]
        holder.itemView.setOnClickListener { onItemClick(item) }
        holder.bindView(item,position)
    }

    class MyViewHolder(val binding: ResPlayerListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindView(item: PlayersDetailsModel,position: Int)
        {
            val color = if (position % 2 == 0) R.color.white else R.color.whitegrey
            binding.topLayout.setBackgroundResource(color)
            binding.txtPlayerName.text = item.nameFull
            binding.imgCaptain.visibility = if (item.isCaptain) View.VISIBLE else View.GONE
            binding.imgGloves.visibility = if (item.isKeeper) View.VISIBLE else View.GONE
        }
    }

    fun sortPlayers(country: String?,m: List<PlayersDetailsModel>?) {
        val filterList =
        if (country == "All"){
            m
        }else{
             m?.filter {
                it.country == country
            }
        }
        updateDataAfterClear(filterList)
        notifyDataSetChanged()
    }

    fun updateDataAfterClear(m: List<PlayersDetailsModel>?) {
        m?.let {
            itemList?.clear()
            itemList?.addAll(it)
            notifyDataSetChanged()
        }
    }

}