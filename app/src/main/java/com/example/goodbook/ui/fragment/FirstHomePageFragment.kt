package com.example.goodbook.ui.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.goodbook.GoodBookApplication
import com.example.goodbook.databinding.FragmentFirstpageHomeBinding
import com.example.goodbook.adpater.HomeListAdapter
import com.example.goodbook.ui.viewmodel.HomeViewModel
import com.example.goodbook.ui.viewmodel.HomeViewModelFactory

class FirstHomePageFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels() {
        HomeViewModelFactory(
                (activity?.application as GoodBookApplication).database
                    .goodBookDao()
        )
    }

    private var _binding: FragmentFirstpageHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstpageHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HomeListAdapter(this.viewLifecycleOwner, viewModel, requireActivity()) { categoryTypeId, categoryTitle ->
            val relatedTopic = when (categoryTypeId) {
                -2 -> ResultsRelatedTopic.MOST_RECENTLY
                -1 -> ResultsRelatedTopic.MOST_RATE
                else -> ResultsRelatedTopic.AVAILABLE_CATEGORY
            }

            val action = FirstHomePageFragmentDirections
                .actionFirsthomepageToSeeMoreListFragment(
                    categoryTitle,
                    PageType.SEEMORE_RESULT,
                    relatedTopic,
                    categoryTypeId
                );
            findNavController().navigate(action)
        }

        viewModel.get7PostsForCategories(this.viewLifecycleOwner).let {
            adapter.submitList(it)
        }

        binding.apply {
            recyclerView.adapter = adapter
        }

    }
}

enum class ResultsRelatedTopic {
    MOST_RECENTLY, MOST_RATE, AVAILABLE_CATEGORY, NULL
}