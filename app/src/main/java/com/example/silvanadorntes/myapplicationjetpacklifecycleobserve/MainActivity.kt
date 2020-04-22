package com.example.silvanadorntes.myapplicationjetpacklifecycleobserve

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var imagePhoto: ImageView = findViewById(R.id.imagePhoto)
        var imagePhoto2: ImageView = findViewById(R.id.imagePhoto2)
        var buttonGetUrl: Button = findViewById(R.id.button_get_url)

        var model = ViewModelProviders.of(this).get(ActivityViewModel::class.java)
        var urlImage: MutableLiveData<String?>? = model.callUrlImage()

        urlImage?.observe(this, Observer {
            println("Se ejecuta si la url sufre un cambio")

            //Si quiero usar Picasso lo hago asi las dos lineas de codigo estan bien y funcionales
            //Picasso.get().load(it).into(imagePhoto)
            //Picasso.get().load(it).into(imagePhoto2)

            //Si quiero usar Glide lo hago asi las dos lineas de codigo estan bien y funcionales
            Glide.with(this).load(it).into(imagePhoto)
            Glide.with(this).load(it).into(imagePhoto2)
        })

        buttonGetUrl.setOnClickListener {
            model.randomNumbersUrl()
        }

    }
}
