package com.example.goodbook.ui.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.goodbook.R
import com.example.goodbook.databinding.FragmentHomeBinding
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

        binding.apply {

            user = viewModel.user

            menuOn.setOnClickListener {
                binding.menuBox.visibility = android.view.View.VISIBLE
            }

            menuOff.setOnClickListener {
                binding.menuBox.visibility = android.view.View.INVISIBLE
            }

            searchTextInput.setOnKeyListener { _, actionId, keyEvent ->
                if (keyEvent != null
                            && (keyEvent.keyCode == KeyEvent.KEYCODE_ENTER
                            || actionId == EditorInfo.IME_ACTION_SEARCH)) {

                    if (contentFragment.findNavController().currentDestination?.id == R.id.firsthomepage) {
                        val action = FirstHomePageFragmentDirections
                            .actionFirsthomepageToSeeMoreListFragment(
                                searchTextField.editText.toString(),
                                PageType.SEARCHED_RESULTS
                            );
                        contentFragment.findNavController().navigate(action)
                    }

                    else if (contentFragment.findNavController().currentDestination?.id == R.id.seeMoreListFragment) {
                        val action = SeeMoreListFragmentDirections
                            .actionSeeMoreListFragmentSelf(
                                searchTextField.editText.toString(),
                                PageType.SEARCHED_RESULTS
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

public enum class PageType {
    SEARCHED_RESULTS, SEEMORE_RESULT
}