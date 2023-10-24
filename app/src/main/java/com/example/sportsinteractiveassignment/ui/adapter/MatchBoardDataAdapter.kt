package com.example.sportsinteractiveassignment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsinteractiveassignment.R
import com.example.sportsinteractiveassignment.data.model.DataModel
import com.example.sportsinteractiveassignment.databinding.ResMatchBoardCardBinding
import com.example.sportsinteractiveassignment.utils.getCountryFlag
import com.example.sportsinteractiveassignment.utils.setImage


class MatchBoardDataAdapter(val itemList:List<DataModel>,private val onItemClick: (Int,DataModel) -> Unit) : RecyclerView.Adapter<MatchBoardDataAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val binding = ResMatchBoardCardBinding.inflate(inflator,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemList[position]
        holder.itemView.setOnClickListener { onItemClick(position,item) }
        holder.bindView(item)
    }

    class MyViewHolder(val binding:ResMatchBoardCardBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindView(item: DataModel)
        {
            binding.matchCardView.setBackgroundResource(R.drawable.match_board_bg)
            binding.txtMatchVenue.text = item.matchDetail?.Venue?.name
            binding.txtMatchDate.text = item.matchDetail?.Match?.date
            binding.txtMatchTime.text = item.matchDetail?.Match?.time
            binding.txtMatchODI.text = item.matchDetail?.Match?.number
            binding.txtMatchEquation.text = item.matchDetail?.Equation

            val teamList = item.teams?.toList()
            val team1 = teamList?.get(0)?.second
            val team2 = teamList?.get(1)?.second
            binding.txtCountryName.text = team1?.countryNameFull.orEmpty()
            binding.txtCountryName2.text = team2?.countryNameFull.orEmpty()

            binding.flagCountry1.setImage(team1?.countryNameShort.getCountryFlag())
            binding.flagCountry2.setImage(team2?.countryNameShort.getCountryFlag())
        }

    }

}