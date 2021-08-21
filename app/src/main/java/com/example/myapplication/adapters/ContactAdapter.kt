package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ContentProviderRecyclerItemBinding


class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    var contacts: List<String> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ContentProviderRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    inner class ContactViewHolder(private val binding: ContentProviderRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(name: String) {
            binding.contactTextView.text = name
        }
    }
}