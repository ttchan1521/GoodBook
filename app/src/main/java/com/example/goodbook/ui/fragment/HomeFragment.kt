package com.example.goodbook.ui.fragment

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.goodbook.GoodBookApplication
import com.example.goodbook.R
import com.example.goodbook.databinding.FragmentHomeBinding
import com.example.goodbook.model.User
import com.example.goodbook.ui.LoginActivity
import com.example.goodbook.ui.SavedPostActivity
import com.example.goodbook.ui.viewmodel.HomeViewModel
import com.example.goodbook.ui.viewmodel.HomeViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels() {
        HomeViewModelFactory(
            (activity?.application as GoodBookApplication).database
                .goodBookDao()
        )
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

        val user_fullname = activity?.intent?.extras?.getString("userFullName")
        val user_avt = activity?.intent?.extras?.getString("userAvt")
        val userId = activity?.intent?.extras?.getInt("userId")


        binding.apply {
            userFullName = user_fullname
            userAvt = user_avt

            menuOn.setOnClickListener {
                menuBox.visibility = android.view.View.VISIBLE

                savedPostTab.setOnClickListener {
                    val savedPostIntent = Intent(activity, SavedPostActivity::class.java)
                    startActivity(savedPostIntent)
                }

                val bottomMenuView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
                bottomMenuView?.visibility = android.view.View.INVISIBLE

                logout.setOnClickListener {
                    val savedPostIntent = Intent(activity, LoginActivity::class.java)
                    startActivity(savedPostIntent)
                }
            }

            menuOff.setOnClickListener {
                menuBox.visibility = android.view.View.INVISIBLE

                val bottomMenuView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
                bottomMenuView?.visibility = android.view.View.VISIBLE
            }

            searchTextInput.setOnKeyListener { _, actionId, keyEvent ->
                if (keyEvent != null
                            && (keyEvent.keyCode == KeyEvent.KEYCODE_ENTER
                            || actionId == EditorInfo.IME_ACTION_SEARCH)) {

                    if (contentFragment.findNavController().currentDestination?.id == R.id.firsthomepage) {
                        val action = FirstHomePageFragmentDirections
                            .actionFirsthomepageToSeeMoreListFragment(
                                searchTextInput.text.toString(),
                                PageType.SEARCHED_RESULTS,
                                ResultsRelatedTopic.NULL,
                                -99
                            );
                        contentFragment.findNavController().navigate(action)
                    }

                    else if (contentFragment.findNavController().currentDestination?.id == R.id.seeMoreListFragment) {
                        val action = SeeMoreListFragmentDirections
                            .actionSeeMoreListFragmentSelf(
                                searchTextInput.text.toString(),
                                PageType.SEARCHED_RESULTS,
                                ResultsRelatedTopic.NULL,
                                -99
                            );
                        contentFragment.findNavController().navigate(action)
                    }

                }
                return@setOnKeyListener false;
            }

            imageNotification.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_notificationFragment)
            }
        }
    }
}

enum class PageType {
    SEARCHED_RESULTS, SEEMORE_RESULT
}
