package com.example.fuelmanagmentsoftware.activities.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fuelmanagmentsoftware.activities.room.UserInfo
import com.example.fuelmanagmentsoftware.databinding.ItemViewBinding

class RVAdapter(val userIn: List<UserInfo?>, val listner: OnItemClick) : RecyclerView.Adapter<RVAdapter.viewHolder>() {
    inner class viewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val binding = ItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return viewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userIn.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        val data = userIn[position]
        holder.binding.tvName.text = data?.name
        holder.binding.tvDate.text = data?.date
        holder.binding.tvFuel.text = data?.fuel
        holder.binding.root.setOnClickListener {
            listner.onUpdate(data!!)

        }
//        holder.binding.btnDel.setOnClickListener {
//            listner.onDelete(data!!.id ?: 0)
//        }
    }
}
