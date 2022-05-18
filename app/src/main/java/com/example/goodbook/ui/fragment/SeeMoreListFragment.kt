package com.example.goodbook.ui.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.goodbook.R
import com.example.goodbook.databinding.FragmentListMoreGridBinding
import com.example.goodbook.model.Book
import com.example.goodbook.ui.adpater.HomeBookCategoriesItemAdapter
import com.example.goodbook.ui.viewmodel.HomeViewModel
import com.example.goodbook.ui.viewmodel.HomeViewModelFactory

class SeeMoreListFragment: Fragment() {

    private val navigationArgs: SeeMoreListFragmentArgs by navArgs()

    private val viewModel: HomeViewModel by activityViewModels() {
        HomeViewModelFactory()
    }

    private var _binding: FragmentListMoreGridBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListMoreGridBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HomeBookCategoriesItemAdapter{ book: Book ->
            Log.d(TAG, "More List View!")
            TODO()
        }

        viewModel.searchedbooks = when (navigationArgs.keyword) {
            null -> listOf()
            else -> viewModel.getBooks(navigationArgs.keyword)
        }

        if (navigationArgs.pagetype != null) {
             if (navigationArgs.pagetype == PageType.SEEMORE_RESULT) {
                 binding.titleMoreDetail.text = "Xem thêm"
                 binding.imageBanner2.setImageResource(R.drawable.book_xemthem)
             }
            else if (navigationArgs.pagetype == PageType.SEARCHED_RESULTS) {
                 binding.titleMoreDetail.text = "Kết quả tìm kiếm"
             }
        }

        viewModel.searchedbooks.let {
            adapter.submitList(it)
        }

        binding.recyclerView.adapter = adapter
    }

}

