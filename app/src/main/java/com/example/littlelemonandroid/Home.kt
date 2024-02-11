package com.example.littlelemonandroid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Home() {
   Column(verticalArrangement = Arrangement.Center, 
      horizontalAlignment = Alignment.CenterHorizontally) {
      Text(text = "Home Screen")
   }
}

@Composable
@Preview(showBackground = true)
fun HomePreview() {
   Home()
}