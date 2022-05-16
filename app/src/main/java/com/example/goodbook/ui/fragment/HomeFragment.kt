package com.example.goodbook.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.goodbook.R
import com.example.goodbook.databinding.FragmentHomeBinding
import com.example.goodbook.ui.adpater.HomeListAdapter
import com.example.goodbook.ui.viewmodel.HomeViewModel
import com.example.goodbook.ui.viewmodel.HomeViewModelFactory


class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels() {
        HomeViewModelFactory()
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HomeListAdapter()
//        { book ->
//            val action = FragmentHomeDirections
//                .actionForageableListFragmentToForageableDetailFragment(forageable.id)
//            findNavController().navigate(action)
//        }

        viewModel.allCategories.let {
            adapter.submitList(it)
        }

        binding.apply {
            recyclerView.adapter = adapter

            menuOn.setOnClickListener {
                binding.menuBox.visibility = android.view.View.VISIBLE
            }

            menuOff.setOnClickListener {
                binding.menuBox.visibility = android.view.View.INVISIBLE
            }

            searchTextField.setOnClickListener{
                TODO()
            }

            imageNotification.setOnClickListener {
                TODO()
            }


        }
    }
}
