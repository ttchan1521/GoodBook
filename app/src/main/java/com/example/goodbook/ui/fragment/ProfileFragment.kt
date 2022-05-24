package com.example.goodbook.ui.fragment

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.goodbook.R
import com.example.goodbook.ui.profile.AccountActivity
import com.example.goodbook.ui.profile.SavePostActivity


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(
            R.layout.fragment_profile,
            container, false
        )
        val accountInfoBtn = view.findViewById<View>(R.id.accountInfoBtn) as Button
        val reviewedBtn = view.findViewById<View>(R.id.reviewedBtn) as Button
        val savedBtn = view.findViewById<View>(R.id.savedBtn) as Button
        accountInfoBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(context, AccountActivity::class.java)

                val userFullname = activity?.intent?.extras?.getString("userFullName")
                val userAvt = activity?.intent?.extras?.getString("userAvt")
                val password = activity?.intent?.extras?.getString("password")
                val email = activity?.intent?.extras?.getString("password")
                val phone = activity?.intent?.extras?.getString("password")

                intent.putExtra("userFullName", userFullname)
                intent.putExtra("userAvt", userAvt)
                intent.putExtra("password", password)
                intent.putExtra("email", email)
                intent.putExtra("phone", phone)
                startActivity(intent)
            }
        })

        savedBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(context, SavePostActivity::class.java)
                startActivity(intent)
            }
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user_fullname = activity?.intent?.extras?.getString("userFullName")
        val user_avt = activity?.intent?.extras?.getString("userAvt")

        val name = view.findViewById<View>(R.id.name) as TextView
        name.setText(user_fullname)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}