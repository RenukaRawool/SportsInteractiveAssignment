package com.example.sportsinteractiveassignment.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.sportsinteractiveassignment.R
import com.example.sportsinteractiveassignment.data.model.DataModel
import com.example.sportsinteractiveassignment.databinding.FragmentMatchBoardBinding
import com.example.sportsinteractiveassignment.ui.adapter.MatchBoardDataAdapter
import com.example.sportsinteractiveassignment.utils.Resource
import com.example.sportsinteractiveassignment.viewmodel.MatchViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MatchBoardFragment : Fragment() {
    //val viewmodel by inject<MatchViewModel>()

    private val viewmodel by activityViewModel<MatchViewModel>()

    lateinit var binding : FragmentMatchBoardBinding
    var matchDataList = mutableListOf<DataModel>()
    lateinit var adapter : MatchBoardDataAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_match_board, container, false)
        binding.lifecycleOwner = this // Set the lifecycle owner to observe LiveData updates

        binding.recyclerviewMatchboardCard.apply {
            layoutManager = LinearLayoutManager(context)
        }
        viewmodel.getMatchDetails()

        viewmodel.matchDetails.observe(viewLifecycleOwner)
        {
            it?.let {res->
                when (res) {
                    is Resource.Success -> {
                        res.data.data?.let { it1 -> matchDataList.addAll(it1) }
                        adapter = MatchBoardDataAdapter(matchDataList)
                        binding.recyclerviewMatchboardCard.adapter = adapter
                    }

                    is Resource.Error -> {
                        Toast.makeText(activity,res.errorMessage,Toast.LENGTH_SHORT).show()
                    }

                    Resource.Unknown -> {
                    }

                    Resource.NoInternet -> {
                        Toast.makeText(activity,"No Internet",Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }

        return binding.root
    }

}