package com.onurkaragunlu.getirflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.onurkaragunlu.getirflow.databinding.ActivitySecondBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeSharedFlow()
        initializeButton()

    }

    private fun observeSharedFlow() {
        lifecycleScope.launchWhenStarted {
            Event.sharedFlow.collect {
                println("SecondActivity Flow Event  $it")
            }
        }
    }

    private fun initializeButton() {
        binding.sendEventButton.setOnClickListener {
            lifecycleScope.launch {
                Event.sendEvent("Second: $count")
                count++
            }
        }
    }
}