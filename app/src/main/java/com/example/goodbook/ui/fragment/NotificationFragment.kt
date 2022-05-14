package com.example.goodbook.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goodbook.R
import com.example.goodbook.adpater.NotificationAdapter
import com.example.goodbook.data.DataNotification
import com.example.goodbook.databinding.FragmentNotificationBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NotificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotificationFragment : Fragment() {
    private var _binding: FragmentNotificationBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNotificationBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.notiRecyclerview
        recyclerView.layoutManager = LinearLayoutManager(context)
        val dataNotification = DataNotification().load()
        recyclerView.adapter = NotificationAdapter(requireContext(), dataNotification)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}