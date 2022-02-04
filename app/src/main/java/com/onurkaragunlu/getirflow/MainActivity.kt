package com.onurkaragunlu.getirflow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.onurkaragunlu.getirflow.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeSharedFlow()
        initializeButton()
    }


    private fun observeSharedFlow() {
        // Collects from the flow when the View is at least STARTED and
        // SUSPENDS the collection when the lifecycle is STOPPED.
        // Collecting the flow cancels when the View is DESTROYED.
        lifecycleScope.launchWhenStarted {
            Event.sharedFlow.collect {
                println("MainActivity Flow Event  $it")
            }
        }

        /* lifecycleScope.launch {
             // The block passed to repeatOnLifecycle is executed when the lifecycle
             // is at least STARTED and is cancelled when the lifecycle is STOPPED.
             // It automatically restarts the block when the lifecycle is STARTED again.
             repeatOnLifecycle(Lifecycle.State.STARTED) {
                 Event.sharedFlow.collect {
                     println("MainActivity Flow Event  $it")
                 }
             }
         }*/

    }

    private fun initializeButton() {
        binding.openActivityButton.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, SecondActivity::class.java)
            startActivity(intent)
        }

        binding.sendEventButton.setOnClickListener {
            lifecycleScope.launch {
                Event.sendEvent("Main")
            }
        }
    }
}