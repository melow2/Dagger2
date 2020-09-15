package com.khs.dagger2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var smartPhone: SmartPhone
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())
        (application as SmartPhoneApplication).smartPhoneComponent.inject(this)
        smartPhone.makeACallWithRecording()
    }
}
