package com.example.littlelemonandroid

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Profile() {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE)
    val firstName = sharedPreferences.getString("firstname", "")
    val lastName = sharedPreferences.getString("lastname", "")
    val email = sharedPreferences.getString("email", "")
    Column() {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            Modifier
                .align(Alignment.CenterHorizontally)
                .aspectRatio(5f)
                .padding(top = 16.dp, bottom = 16.dp)
        )
        Text(
            text = "Personal Information",
            modifier = Modifier.padding(vertical = 40.dp, horizontal = 12.dp),
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
        )
        Text(text = "First Name", modifier = Modifier.padding(horizontal = 12.dp))
        TextField(
            value = firstName!!, onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp)
        )
        Text(text = "Last Name", modifier = Modifier.padding(horizontal = 12.dp))
        TextField(
            value = lastName!!, onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp)
        )
        Text(text = "Email", modifier = Modifier.padding(horizontal = 12.dp))
        TextField(
            value = email!!, onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {  },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0XFFe3a41b)),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp)
            
        ) {
            Text(
                text = "Log Out", style = TextStyle(color = Color.Black),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
fun ProfilePreview() {
    Profile()
}