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