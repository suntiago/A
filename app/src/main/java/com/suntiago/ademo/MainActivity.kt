package com.suntiago.ademo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.suntiago.a.A

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        A.between(1,2,3)
    }
}
