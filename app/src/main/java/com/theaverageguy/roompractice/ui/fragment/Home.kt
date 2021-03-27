package com.theaverageguy.roompractice.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.theaverageguy.roompractice.R
import com.theaverageguy.roompractice.adapter.showUserAdapter
import com.theaverageguy.roompractice.databinding.FragmentHomeBinding
import com.theaverageguy.roompractice.viewModel.myViewModel

class Home : Fragment(), View.OnClickListener {

    private var _bind: FragmentHomeBinding? = null
    private val binding get() = _bind!!

    private lateinit var mUserViewModel: myViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        setHasOptionsMenu(true)

        // Recyclerview
        val adapter = showUserAdapter()
        val recyclerView = _bind?.recyclerview
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())


        mUserViewModel = ViewModelProvider(this).get(myViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAllComponents()
    }

    private fun initAllComponents() {
        _bind?.floatingActionButton?.setOnClickListener(this)
        showDialog()
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setNegativeButton("OK") { _, _ -> }
        builder.setTitle("Notice 🗒")
        builder.setMessage("This is just a practice app to learn the following topics \n👉Room database \n👉MVVM architecture pattern \n👉Co-routines\n👉Navigation Components \n👉Single activity architecture \n👉Safe Args \n👉View Binding")
        builder.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> {
                val builder = AlertDialog.Builder(requireContext())
                builder.setPositiveButton("Yes") { _, _ ->
                    mUserViewModel.deleteAllUsers()
                    Toast.makeText(
                        requireContext(),
                        "Successfully removed everything",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                builder.setNegativeButton("No") { _, _ -> }
                builder.setTitle("Delete everything?")
                builder.setMessage("Are you sure you want to delete everything?")
                builder.create().show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.floatingActionButton -> {
                findNavController().navigate(R.id.action_home_to_add)
                /*val user = PhoneBook(
                    0,
                    "",
                    "",
                    Integer.parseInt("0")
                )
                val action = HomeDirections.actionHomeToAdd(user)
                findNavController().navigate(action)*/
            }
        }
    }

}