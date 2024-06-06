package com.example.tapapp


import android.content.ContentValues.TAG
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.amplitude.experiment.ExperimentClient
import com.example.tapapp.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private final var sdk = android.os.Build.VERSION.SDK_INT
    private var topLeftCounter: Int = 0
    private var topRightCounter: Int = 0
    private var bottomLeftCounter: Int = 0
    private var bottomRightCounter: Int = 0

    companion object {
        const val TOP_LEFT_KEY = "top left key"
        const val TOP_RIGHT_KEY = "top right key"
        const val BOTTOM_LEFT_KEY = "bottom left key"
        const val BOTTOM_RIGHT_KEY = "bottom right key"
    }


    private fun setBackgroundToRed(location: Int) {

        binding.topleft.background =
            ContextCompat.getDrawable(applicationContext, R.drawable.background)
        binding.topright.background =
            ContextCompat.getDrawable(applicationContext, R.drawable.background)
        binding.bottomright.background =
            ContextCompat.getDrawable(applicationContext, R.drawable.background)
        binding.bottomleft.background =
            ContextCompat.getDrawable(applicationContext, R.drawable.background)

        when (location) {
            1 -> {
                binding.topLeftCount.visibility = View.VISIBLE
                binding.topleft.background =
                    ContextCompat.getDrawable(applicationContext, R.drawable.background_red)
            }

            2 -> {
                binding.topRightCount.visibility = View.VISIBLE
                binding.topright.background =
                    ContextCompat.getDrawable(applicationContext, R.drawable.background_red)
            }

            3 -> {
                binding.bottomLeftCount.visibility = View.VISIBLE
                binding.bottomleft.background =
                    ContextCompat.getDrawable(applicationContext, R.drawable.background_red)
            }

            else -> {
                binding.bottomRightCount.visibility = View.VISIBLE
                binding.bottomright.background =
                    ContextCompat.getDrawable(applicationContext, R.drawable.background_red)
            }
        }
    }

    private fun setBackgroundToBlue(location: Int) {

        binding.topleft.background =
        ContextCompat.getDrawable(applicationContext, R.drawable.background)
        binding.topright.background =
            ContextCompat.getDrawable(applicationContext, R.drawable.background)
        binding.bottomright.background =
            ContextCompat.getDrawable(applicationContext, R.drawable.background)
        binding.bottomleft.background =
            ContextCompat.getDrawable(applicationContext, R.drawable.background)

        when (location) {
            1 -> {
                binding.topLeftCount.visibility = View.VISIBLE
                binding.topleft.background =
                    ContextCompat.getDrawable(applicationContext, R.drawable.background_blue)
            }

            2 -> {
                binding.topRightCount.visibility = View.VISIBLE
                binding.topright.background =
                    ContextCompat.getDrawable(applicationContext, R.drawable.background_blue)
            }

            3 -> {
                binding.bottomLeftCount.visibility = View.VISIBLE
                binding.bottomleft.background =
                    ContextCompat.getDrawable(applicationContext, R.drawable.background_blue)
            }

            else -> {
                binding.bottomRightCount.visibility = View.VISIBLE
                binding.bottomright.background =
                    ContextCompat.getDrawable(applicationContext, R.drawable.background_blue)
            }
        }
    }

    private fun clickTrackerBlue(){
        binding.topleft.setOnClickListener {
            topLeftCounter++
            binding.topLeftCount.text = topLeftCounter.toString()
            setBackgroundToBlue(1)
        }
        binding.topright.setOnClickListener {
            topRightCounter++
            binding.topRightCount.text = topRightCounter.toString()
            setBackgroundToBlue(2)
        }
        binding.bottomleft.setOnClickListener {
            bottomLeftCounter++
            binding.bottomLeftCount.text = bottomLeftCounter.toString()
            setBackgroundToBlue(3)
        }
        binding.bottomright.setOnClickListener {
            bottomRightCounter++
            binding.bottomRightCount.text = bottomRightCounter.toString()
            setBackgroundToBlue(4)
        }

    }

    private fun clickTrackerRed() {
        binding.topleft.setOnClickListener {
            topLeftCounter++
            binding.topLeftCount.text = topLeftCounter.toString()
            setBackgroundToRed(1)
        }
        binding.topright.setOnClickListener {
            topRightCounter++
            binding.topRightCount.text = topRightCounter.toString()
            setBackgroundToRed(2)
        }
        binding.bottomleft.setOnClickListener {
            bottomLeftCounter++
            binding.bottomLeftCount.text = bottomLeftCounter.toString()
            setBackgroundToRed(3)
        }
        binding.bottomright.setOnClickListener {
            bottomRightCounter++
            binding.bottomRightCount.text = bottomRightCounter.toString()
            setBackgroundToRed(4)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var changeBackground = false

        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig

        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)


        remoteConfig.fetchAndActivate().addOnCompleteListener(this) { task ->
            if (task.isSuccessful){
                val isBlue = remoteConfig.getBoolean("blue_background")
                val updated = task.result
                changeBackground = updated
                Log.d(TAG, "Config params updated: $updated")

                if(isBlue){
                    clickTrackerBlue()
                } else{
                    clickTrackerRed()
                }
            } else{
                Log.d(TAG, "Remote config parameters failed to update")
            }
        }

        remoteConfig.fetchAndActivate().addOnCompleteListener(this) { task ->
            if (task.isSuccessful){
                val isBlue = remoteConfig.getBoolean("blue_background")
                val updated = task.result
                changeBackground = updated
                Log.d(TAG, "Config params updated: $updated")

                if(isBlue){
                    clickTrackerBlue()
                } else{
                    clickTrackerRed()
                }
            } else{
                Log.d(TAG, "Remote config parameters failed to update")
            }
        }

        remoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener {
            override fun onUpdate(configUpdate: ConfigUpdate) {
                if(configUpdate.updatedKeys.contains("blue_background")){
                    remoteConfig.activate().addOnCompleteListener{
                        clickTrackerBlue()
                    }
                }
                else
                    clickTrackerRed()
            }

            override fun onError(error: FirebaseRemoteConfigException) {
                TODO("Not yet implemented")
            }
        })


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TOP_LEFT_KEY, topLeftCounter)
        outState.putInt(TOP_RIGHT_KEY, topRightCounter)
        outState.putInt(BOTTOM_LEFT_KEY, bottomLeftCounter)
        outState.putInt(BOTTOM_RIGHT_KEY, bottomRightCounter)

    }

}

