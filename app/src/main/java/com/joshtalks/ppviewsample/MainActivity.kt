package com.joshtalks.ppviewsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.joshtalks.ppviewsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val imageUrl = "https://media.istockphoto.com/photos/portrait-of-a-smiling-young-boy-picture-id1168352687?b=1&k=20&m=1168352687&s=170667a&w=0&h=g4-PgBrUFIa7ceUeVKErhruDppu-ktfHQZB-ELF4fUg="

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.handler = this

        binding.peopleList.adapter = PeopleAdapter(getList())

    }

    private fun getList() = listOf(
        Person(
            "Deepak Sharma",
            "Hi Bro, How are your??",
            "https://images.hindustantimes.com/rf/image_size_960x540/HT/p2/2018/12/16/Pictures/_ad5b9986-012c-11e9-8449-e5c2aa861415.jpg"
        ),
        Person(
            "Ankit Jha",
            "Let's do party today",
        ),
        Person(
            "Mohit",
            "Ok",
            "https://live.staticflickr.com/7480/15920980069_087d525355_b.jpg"
        ),
        Person(
            "Deepak Sharma",
            "Hi Bro, How are your??",
        ),
    )
}