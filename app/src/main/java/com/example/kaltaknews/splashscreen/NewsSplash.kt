package com.example.kaltaknews.splashscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kaltaknews.databinding.ActivitySplashNewsBinding

class NewsSplash : AppCompatActivity() {
    lateinit var mBinding: ActivitySplashNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//       mBinding= DataBindingUtil.setContentView(this,R.layout.activity_splash_news)
//        Handler(Looper.getMainLooper()).postDelayed({
//           val intent=Intent(this,MainActivity::class.java)
//
//           startActivity(intent)
//            finish()
//        },3000)
    }
}