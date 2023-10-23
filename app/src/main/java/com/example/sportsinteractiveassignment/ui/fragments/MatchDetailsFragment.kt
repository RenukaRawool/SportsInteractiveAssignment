package com.example.sportsinteractiveassignment.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.sportsinteractiveassignment.R
import com.example.sportsinteractiveassignment.data.model.DataModel
import com.example.sportsinteractiveassignment.databinding.FragmentMatchBoardBinding
import com.example.sportsinteractiveassignment.databinding.FragmentMatchDetailsBinding
import com.example.sportsinteractiveassignment.viewmodel.MatchViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class MatchDetailsFragment : Fragment() {
    //val viewmodel by inject<MatchViewModel>()
    private val viewmodel by activityViewModel<MatchViewModel>()
    lateinit var binding : FragmentMatchDetailsBinding
    var matchDataList = mutableListOf<DataModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_match_details, container, false)
        binding.lifecycleOwner = this  // Set the lifecycle owner to observe LiveData updates
        return binding.root
    }
}