package com.uz.shimmerlayot.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uz.shimmerlayot.data.entity.Person
import com.uz.shimmerlayot.databinding.ItemPersonBinding

class PersonAdapter(private val onDeleteClick: (Person) -> Unit) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    private val persons = mutableListOf<Person>()

    fun submitList(list: List<Person>) {
        persons.clear()
        persons.addAll(list)
        notifyDataSetChanged()
    }

    inner class PersonViewHolder(private val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person) {
            binding.tvName.text = person.name
            binding.tvGender.text = person.gender

            binding.root.setOnLongClickListener {
                onDeleteClick(person)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(binding)
    }

    override fun getItemCount() = persons.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(persons[position])
    }
}
