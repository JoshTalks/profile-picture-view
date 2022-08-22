package com.joshtalks.ppviewsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joshtalks.ppviewsample.databinding.ItemPeopleBinding

class PeopleAdapter(val peopleList: List<Person>): RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val binding = ItemPeopleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PeopleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val person = peopleList[position]
        holder.setData(person)
    }

    override fun getItemCount(): Int = peopleList.size

    inner class PeopleViewHolder(val binding: ItemPeopleBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(person: Person){
            binding.person = person
        }
    }

}