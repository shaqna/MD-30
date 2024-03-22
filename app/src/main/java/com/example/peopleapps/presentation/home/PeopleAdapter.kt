package com.example.peopleapps.presentation.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.peopleapps.databinding.ItemPeopleBinding
import com.example.peopleapps.model.People
import com.example.peopleapps.presentation.detail.DetailActivity

class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {
    private val arrayList = arrayListOf<People>()

    fun setList(list: List<People>) {
        arrayList.clear()
        arrayList.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemPeopleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int = arrayList.size

    inner class ViewHolder(private val binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(people: People) {
            with(binding) {
                tvPeopleName.text = "${people.firstName} ${people.lastName}"

                Glide.with(itemView.context).load(people.avatar).into(circleImageView)

               itemView.setOnClickListener {
                    Intent(itemView.context, DetailActivity::class.java).also {
                        it.putExtra(DetailActivity.PEOPLE, people)
                        itemView.context.startActivity(it)
                    }
               }
            }
        }
    }
}