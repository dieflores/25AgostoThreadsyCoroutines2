package com.example.a25agostothreadsycoroutines2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mImfOfDay: ImageView
    private lateinit var mProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val url = "https://apod.nasa.gov/apod/image/1908/M61-HST-ESO-S1024.jpg"

        CoroutineScope(Dispatchers.Main).launch {
            val image = doInBackground(url)
            if(image != null){
                updateView(image)
            }
        }

    }
    fun doInBackground(url:String): Bitmap?{
        var bmp: Bitmap? = null
        try {
            progressBar.visibility = View.VISIBLE
            var inputStream = java.net.URL(url).openStream()
            bmp = BitmapFactory.decodeStream(inputStream)
        }catch (e: Exception){
            Log.e("ERROR", e.message.toString())
            e.printStackTrace()
        }
        return bmp
    }
        fun onPostExecute(result: Bitmap?){
            try{
                progressBar.visibility = View.GONE
                imgOfDay.setImageBitmap(result)
            } catch (e: Exception){
                e.printStackTrace()
            }


        }
}