package com.example.goodbook.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.goodbook.R
import com.example.goodbook.databinding.FragmentFirstpageHomeBinding
import com.example.goodbook.model.CategoryType
import com.example.goodbook.adpater.HomeListAdapter
import com.example.goodbook.ui.viewmodel.HomeViewModel
import com.example.goodbook.ui.viewmodel.HomeViewModelFactory

class FirstHomePageFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels() {
        HomeViewModelFactory()
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

        val adapter = HomeListAdapter { categoryType ->
            val keyword = when (categoryType) {
                CategoryType.MOST_READ -> "Most read"
                else -> "recently"
            }

            val action = FirstHomePageFragmentDirections
                .actionFirsthomepageToSeeMoreListFragment(
                    keyword,
                    PageType.SEEMORE_RESULT
                );
            findNavController().navigate(action)
        }

        viewModel.allCategories.let {
            adapter.submitList(it)
        }

        binding.apply {
            recyclerView.adapter = adapter
        }
    }
}