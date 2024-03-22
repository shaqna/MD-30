package com.example.peopleapps.presentation.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.peopleapps.R
import com.example.peopleapps.databinding.ActivityDetailBinding
import com.example.peopleapps.model.People

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        processIntent()
    }

    private fun processIntent() {
        val people = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(PEOPLE, People::class.java)
        } else {
            intent.getParcelableExtra(PEOPLE)
        }

        with(binding) {
            tvPeopleName.text = "${people?.firstName} ${people?.lastName}"
            Glide.with(this@DetailActivity).load(people?.avatar).into(circleImageView)
        }

    }

    companion object {
        const val PEOPLE = "people"
    }
}