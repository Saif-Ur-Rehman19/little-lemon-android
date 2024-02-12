package com.example.littlelemonandroid

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Home(navHostController: NavHostController) {
   Column() {
      Image(painter = painterResource(id = R.drawable.profile), contentDescription = "",
         modifier = Modifier.size(70.dp).padding(8.dp).align(Alignment.End).clickable {
            navHostController.navigate(Profile.route)
         })
   }
}

@Composable
@Preview(showBackground = true)
fun HomePreview() {

}