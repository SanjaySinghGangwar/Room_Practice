package com.theaverageguy.roompractice.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.theaverageguy.roompractice.R
import com.theaverageguy.roompractice.room.modelClasses.PhoneBook
import com.theaverageguy.roompractice.ui.fragment.HomeDirections

class showUserAdapter : RecyclerView.Adapter<showUserAdapter.MyViewHolder>() {

    private var userList = emptyList<PhoneBook>()


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mainLayout: LinearLayoutCompat = itemView.findViewById(R.id.mainLayout)
        var firstName: TextView = itemView.findViewById(R.id.firstName)
        var heading: TextView = itemView.findViewById(R.id.heading)
        var lastName: TextView = itemView.findViewById(R.id.lastName)
        var number: TextView = itemView.findViewById(R.id.number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.show_user_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.firstName.text = currentItem.firstName
        holder.heading.text = "# " + (position + 1).toString()
        holder.lastName.text = currentItem.lastName
        holder.number.text = currentItem.phoneNumber.toString()
        holder.mainLayout.setOnClickListener {
            val action = HomeDirections.actionHomeToAdd(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    fun setData(user: List<PhoneBook>) {
        this.userList = user
        notifyDataSetChanged()
    }


}