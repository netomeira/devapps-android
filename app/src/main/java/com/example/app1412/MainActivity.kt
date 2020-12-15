package com.example.app1412

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonExplicit.setOnClickListener(){
            val intent  = Intent(applicationContext, ActivitySecundaria::class.java)
            startActivity(intent)
        }

        buttonImplicit.setOnClickListener(){
            val intent = Intent().apply {
                setAction("SECUNDARIA")
            }

            if (intent.resolveActivity(packageManager) != null) {
                Log.d("HSS", "Start activity action SECUNDARIA")
                startActivity(intent)
            }
        }

        buttonImplicitCategory.setOnClickListener(){
            val intent = Intent().apply {
                setAction("TERCIARIA")
            }

            if (intent.resolveActivity(packageManager) != null) {
                Log.d("HSS", "Start activity action TERCIARIA")
                startActivity(intent)
            }
        }

        buttonOpenPage.setOnClickListener(){
            val intent = Intent().apply {
                setAction(Intent.ACTION_VIEW)
                setData(Uri.parse("https://www.newgrounds.com"))
            }

            if (intent.resolveActivity(packageManager) != null){
                Log.d("HSS", "Start activity action HALDNY")
                startActivity(intent)
            } else {
                Log.d("HSS", "No activity found for HALDNY")
            }
        }

        buttonMakeCall.setOnClickListener(){
            val intent = Intent().apply {
                setAction(Intent.ACTION_DIAL)
                setData(Uri.parse("tel:"+11118802177690))
            }

            if (intent.resolveActivity(packageManager) != null){
                Log.d("HSS", "Start activity for dial")
                startActivity(intent)
            } else {
                Log.d("HSS", "No activity found for dialing")
            }
        }

        buttonCreateAlarm.setOnClickListener(){
            createAlarm("Test", 21, 39)
        }

        buttonViewMap.setOnClickListener(){
            val mapIntent: Intent = Uri.parse(
                "geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California"
            ).let { location ->
                // Or map point based on latitude/longitude
                // Uri location = Uri.parse("geo:37.422219,-122.08364?z=14"); // z param is zoom level
                Intent(Intent.ACTION_VIEW, location)
            }
            if (mapIntent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
    }

    fun createAlarm(message: String, hour: Int, minutes: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}