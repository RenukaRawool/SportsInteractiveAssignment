package com.example.sportsinteractiveassignment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportsinteractiveassignment.R
import com.example.sportsinteractiveassignment.data.model.PlayersDetailsModel
import com.example.sportsinteractiveassignment.databinding.FragmentMatchDetailsBinding
import com.example.sportsinteractiveassignment.databinding.ResBottomCardBinding
import com.example.sportsinteractiveassignment.ui.adapter.PlayerDetailsAdapter
import com.example.sportsinteractiveassignment.utils.Resource
import com.example.sportsinteractiveassignment.utils.getCountryFlag
import com.example.sportsinteractiveassignment.utils.setImage
import com.example.sportsinteractiveassignment.viewmodel.MatchViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class MatchDetailsFragment : Fragment() {
    //val viewmodel by inject<MatchViewModel>()
    private val viewmodel by activityViewModel<MatchViewModel>()
    lateinit var binding: FragmentMatchDetailsBinding
    var matchDetailsList = mutableListOf<PlayersDetailsModel>()
    lateinit var matchDetailAdapter: PlayerDetailsAdapter
    val args: MatchDetailsFragmentArgs by navArgs()

    var cardPosition: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_match_details, container, false)
        binding.lifecycleOwner = this  // Set the lifecycle owner to observe LiveData updates

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cardPosition = args.cardPosition

        binding.imgBackarrow.setOnClickListener(View.OnClickListener {
            findNavController().navigateUp()
        })

        binding.recyclerviewPlayer.apply {
            layoutManager = LinearLayoutManager(context)
            matchDetailAdapter = PlayerDetailsAdapter(
                onItemClick = { item ->
                    showBottomDialog(item)
                },
            )
            adapter = matchDetailAdapter
        }



        viewmodel.matchDetails.observe(viewLifecycleOwner) { item ->
            item?.let { res ->
                when (res) {
                    is Resource.Success -> {
                        res?.data?.data?.get(cardPosition)?.let { dataItem ->
                            dataItem.matchDetail?.let { item ->
                                binding.txtMatchVenue.text = item.Venue?.name
                                binding.txtMatchEquation.text = item.Equation
                                item.Match?.let { item2 ->
                                    binding.txtMatchDate.text = item2.date
                                    binding.txtMatchTime.text = item2.time
                                    binding.txtMatchODI.text = item2.number
                                }
                            }
                            dataItem.teams?.let { teamItem ->
                                val teamList = teamItem?.toList()
                                val team1 = teamList?.get(0)?.second
                                val team2 = teamList?.get(1)?.second
                                val country1 = team1?.countryNameFull.orEmpty()
                                val country2 = team2?.countryNameFull.orEmpty()

                                var countryShort1 = team1?.countryNameShort.orEmpty()
                                var countryShort2 = team2?.countryNameShort.orEmpty()

                                binding.tableLayout.getTabAt(1)?.text = countryShort1
                                binding.tableLayout.getTabAt(2)?.text = countryShort2

                                binding.tableLayout.getTabAt(1)?.tag = countryShort1
                                binding.tableLayout.getTabAt(2)?.tag = countryShort2

                                binding.tableLayout.getTabAt(0)?.tag = "All"
                                binding.tableLayout.getTabAt(0)?.text = "All"

                                binding.titleMatchDetail.text = "$country1 VS $country2"
                                binding.txtCountryName.text = country1
                                binding.txtCountryName2.text = country2


                                binding.flagCountry1.setImage(team1?.countryNameShort.getCountryFlag())
                                binding.flagCountry2.setImage(team2?.countryNameShort.getCountryFlag())
                            }
                        }
                    }

                    is Resource.Error -> {
                        Toast.makeText(activity, res.errorMessage, Toast.LENGTH_SHORT).show()
                    }

                    Resource.Unknown -> {
                    }

                    Resource.NoInternet -> {
                        Toast.makeText(activity, "No Internet", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }

        viewmodel.playerDetails.observe(viewLifecycleOwner)
        {
            it?.let { res ->
                matchDetailsList.addAll(res)
                matchDetailAdapter.updateDataAfterClear(matchDetailsList)
                matchDetailAdapter.notifyDataSetChanged()

            }
        }

        binding.tableLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val country = tab?.tag
                matchDetailAdapter.sortPlayers(country as String,matchDetailsList)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }


        })
    }

    fun showBottomDialog(item: PlayersDetailsModel) {
        val bindingCard: ResBottomCardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.res_bottom_card,
            null,
            false
        )
        val dialog = BottomSheetDialog(requireActivity())
        dialog.setContentView(bindingCard.root)
        dialog.setCancelable(false) // Disable outside click

        bindingCard.txtPlayerName.text = item.nameFull
        bindingCard.txtBattingStyle.text = item.batting.BattingStyle
        bindingCard.txtAverage.text = item.batting.average
        bindingCard.txtStrikeRate.text = item.batting.strikeRate
        bindingCard.txtRuns.text = item.batting.runs
        bindingCard.txtBowlingStyle.text = item.bowling.bowlingStyle
        bindingCard.txtBowlingAverage.text = item.bowling.average
        bindingCard.txtEconomyRate.text = item.bowling.economyRate
        bindingCard.txtWickets.text = item.bowling.wickets

        bindingCard.imgClose.setOnClickListener(View.OnClickListener {
            dialog.dismiss()
        })
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewmodel.clearData()
    }
}