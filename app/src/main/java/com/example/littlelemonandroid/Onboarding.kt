package com.example.littlelemonandroid

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavHostController

@Composable
fun Onboarding(navController: NavHostController) {
    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Column() {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            Modifier
                .align(Alignment.CenterHorizontally)
                .aspectRatio(5f)
                .padding(top = 16.dp, bottom = 16.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(0XFF353d3a)),
        ) {
            Text(
                text = "Let's get to know you",
                modifier =
                Modifier.align(Alignment.Center),
                style = TextStyle(color = Color(0XFFFFFFFF), fontSize = 24.sp)
            )
        }
        Text(
            text = "Personal Information",
            modifier = Modifier.padding(vertical = 30.dp, horizontal = 12.dp),
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
        )
        OutlinedTextField(
            value = firstName, onValueChange = {
                firstName = it
            },
            label = { Text(text = "First Name") }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp)
        )
        OutlinedTextField(
            value = lastName, onValueChange = {
                lastName = it
            },
            label = { Text(text = "Last Name") }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp)
        )
        OutlinedTextField(
            value = email, onValueChange = {
                email = it
            },
            label = { Text(text = "Email") }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp)
        )
        Button(
            onClick = {
                if (firstName.isBlank() or lastName.isBlank() or email.isBlank()) {
                    Toast.makeText(
                        context, "Registration unsuccessful." +
                                " Please enter all data.", Toast.LENGTH_LONG
                    ).show()
                } else {
                    val sharedPrefs = context.getSharedPreferences("data", Context.MODE_PRIVATE)
                    sharedPrefs.edit().putString("firstname", firstName)
                        .putString("lastname", lastName).putString("email", email).commit()
                    Toast.makeText(context, "Registration Successful", Toast.LENGTH_LONG).show()
                    navController.navigate(Home.route)
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0XFFe3a41b)),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Register",
                style = TextStyle(color = Color.Black),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    Onboarding(navController = NavHostController(LocalContext.current))
}