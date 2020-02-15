package com.example.janken

import android.annotation.SuppressLint
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.core.content.edit
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.schedule
import android.media.MediaPlayer
import android.net.Uri
import android.widget.TextView
import com.example.janken.GlobalVariable.gMyCnt
import com.example.janken.GlobalVariable.gchCnt
import java.io.IOException

object GlobalVariable {
    var gMyCnt = 0
    var gchCnt = 0
}


class MainActivity : AppCompatActivity() {

    var mp: MediaPlayer? = null // 追加
    //private val handler = Handler()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // タイトル画面を表示
        //setContentView(R.layout.activity_title)


        mp = MediaPlayer.create(applicationContext, R.raw.jankenlong) // 追加

        mp?.isLooping = true

        mp?.seekTo(0)
        //mp?.setVolume(0.5f,0.5f)
        mp?.start()

         gu.setOnClickListener { onJankenButtonTapped(it)
        //showResult()
        }
        choki.setOnClickListener { onJankenButtonTapped(it)
        //showResult()
        }
        pa.setOnClickListener { onJankenButtonTapped(it)
        //showResult()
        }

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit {
            clear()
        }
    }

    fun showResult() {
        textView6.text = gMyCnt.toString()
        textView5.text = gchCnt.toString()
    }

    fun onJankenButtonTapped(view: View?) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("MY_HAND", view?.id)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        mp?.start()
        showResult()
    }

    override fun onPause() {
        super.onPause()
        mp?.pause()
    }
 }
