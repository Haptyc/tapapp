@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.tapapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tapapp.databinding.ActivityComposeBinding
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.clip


class ComposeActivity : AppCompatActivity() {

    lateinit var activityComposeBinding: ActivityComposeBinding


    @Preview(
        showSystemUi = true
    )
    @Composable
    fun Grid() {


        var counterArray = IntArray(4) { 0 }


        var visible by remember {
            mutableStateOf(false)
        }
        var currentRed by remember {
            mutableStateOf(-1)
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            content = {
                items(4) { i ->
                    Box(
                        modifier = Modifier
                            .aspectRatio(0.5f)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(
                                color = if (i == currentRed) {
                                    Color.Red
                                } else {
                                    Color.Green
                                }
                            )
                            .clickable {
                                currentRed = i
                                counterArray[i]++

                            },
                        contentAlignment = Alignment.Center
                    ) {
                        AnimatedVisibility(visible = i == currentRed) {
                            Text(counterArray[i].toString())
                        }
                    }
                }
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Grid()
        }
    }

    private fun handleClicks(index: Int) {

    }
}