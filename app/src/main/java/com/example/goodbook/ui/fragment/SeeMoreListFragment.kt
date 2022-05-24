package com.example.goodbook.ui.fragment

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.goodbook.GoodBookApplication
import com.example.goodbook.R
import com.example.goodbook.databinding.FragmentListMoreGridBinding
import com.example.goodbook.adpater.HomeBookCategoriesItemAdapter
import com.example.goodbook.model.Post
import com.example.goodbook.ui.DetailPostActivity
import com.example.goodbook.ui.viewmodel.HomeViewModel
import com.example.goodbook.ui.viewmodel.HomeViewModelFactory

class SeeMoreListFragment: Fragment() {

    private val navigationArgs: SeeMoreListFragmentArgs by navArgs()

    private val viewModel: HomeViewModel by activityViewModels() {
        HomeViewModelFactory(
            (activity?.application as GoodBookApplication).database
                .goodBookDao()
        )
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

        val adapter = HomeBookCategoriesItemAdapter(viewModel, this.viewLifecycleOwner) { post: Post ->
            Log.d(TAG, "Detail Post View!")
            val postDetailIntent = Intent(this.activity, DetailPostActivity::class.java)
            postDetailIntent.putExtra("post", post.id)
            startActivity(postDetailIntent)
        }

        if (navigationArgs.pagetype != null) {
             if (navigationArgs.pagetype == PageType.SEEMORE_RESULT) {
                 binding.titleMoreDetail.text = navigationArgs.keyword
                 binding.imageBanner2.setImageResource(R.drawable.book_xemthem)

                 if (navigationArgs.relatedtocategory != null) {
                     viewModel.searchedposts = when (navigationArgs.relatedtocategory) {
                         ResultsRelatedTopic.MOST_RATE -> viewModel.getAllMostRatePosts()
                         ResultsRelatedTopic.MOST_RECENTLY -> viewModel.getAllMostRecentlyPosts()
                         else -> viewModel.getAllPostRelatedTo(navigationArgs.categoryId)
                     }
                 }
             }
            else if (navigationArgs.pagetype == PageType.SEARCHED_RESULTS) {
                 binding.titleMoreDetail.text = "Kết quả tìm kiếm"
                 viewModel.searchedposts = viewModel.getPostsRelatedToSearchedKeyword(navigationArgs.keyword)
             }
        }

        viewModel.searchedposts.observe(this.viewLifecycleOwner) { posts ->
            posts.let {
                adapter.submitList(it)
            }
        }

        binding.recyclerView.adapter = adapter


    }


}

