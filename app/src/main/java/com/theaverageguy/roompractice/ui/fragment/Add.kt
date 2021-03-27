package com.theaverageguy.roompractice.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.theaverageguy.roompractice.R
import com.theaverageguy.roompractice.databinding.FragmentAddBinding
import com.theaverageguy.roompractice.room.modelClasses.PhoneBook
import com.theaverageguy.roompractice.utils.mToast.isValidText
import com.theaverageguy.roompractice.viewModel.myViewModel


class Add : Fragment(), View.OnClickListener {
    private var _bind: FragmentAddBinding? = null
    private val binding get() = _bind!!
    private lateinit var mViewModel: myViewModel
    private val args by navArgs<AddArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root
        mViewModel = ViewModelProvider(this).get(myViewModel::class.java)
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAllComponents()
    }

    private fun initAllComponents() {
        _bind?.save?.setOnClickListener(this)
        try {
            _bind?.firstName?.setText(args.currentUser.firstName)
            _bind?.lastName?.setText(args.currentUser.lastName)
            _bind?.number?.setText(args.currentUser.phoneNumber.toString())
            _bind?.save?.text = "UPDATE"

        } catch (e: Exception) {
            _bind?.firstName?.setText("")
            _bind?.lastName?.setText("")
            _bind?.number?.setText("")
            _bind?.save?.text = "SAVE"
            setHasOptionsMenu(false)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> {
                val builder = AlertDialog.Builder(requireContext())
                builder.setPositiveButton("Yes") { _, _ ->
                    mViewModel.deleteUser(args.currentUser)
                    Toast.makeText(
                        requireContext(),
                        "Successfully removed: ${args.currentUser.firstName}",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_add_to_home)
                }
                builder.setNegativeButton("No") { _, _ -> }
                builder.setTitle("Delete ${args.currentUser.firstName}?")
                builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
                builder.create().show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.save -> {
                if (_bind?.save?.text == "SAVE") {
                    if (isValidText(_bind?.firstName, _bind?.firstName?.text)
                        && isValidText(_bind?.lastName, _bind?.lastName?.text) &&
                        isValidText(_bind?.number, _bind?.number?.text)
                    ) {
                        val user = PhoneBook(
                            0,
                            _bind?.firstName?.text.toString(),
                            _bind?.lastName?.text.toString(),
                            Integer.parseInt(_bind?.number?.text.toString())
                        )
                        mViewModel.addUser(user)
                        findNavController().navigate(R.id.action_add_to_home)
                    }
                }
                if (_bind?.save?.text == "UPDATE") {
                    if (isValidText(_bind?.firstName, _bind?.firstName?.text)
                        && isValidText(_bind?.lastName, _bind?.lastName?.text)
                        && isValidText(_bind?.number, _bind?.number?.text)
                    ) {
                        val updatedUser = PhoneBook(
                            args.currentUser.id, _bind?.firstName?.text.toString(),
                            _bind?.lastName?.text.toString(),
                            Integer.parseInt(_bind?.number?.text.toString())
                        )
                        mViewModel.updateUser(updatedUser)
                        findNavController().navigate(R.id.action_add_to_home)
                    }
                }
            }
        }
    }


}