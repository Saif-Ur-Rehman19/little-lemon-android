package com.example.littlelemonandroid

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemonandroid.database.MenuItem


@Composable
fun Home(navHostController: NavHostController) {
    val menuItems = MainActivity.DatabaseManager.getDatabase(LocalContext.current).menuDao()
        .getAllMenuItems().observeAsState(emptyList())
    var searchPhrase by remember {
        mutableStateOf("")
    }
    val groupedMenuItems = remember(menuItems.value) {
        menuItems.value.groupBy { it.category }
    }
    Column() {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "little lemon logo",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.Center)
            )
            Image(painter = painterResource(id = R.drawable.profile),
                contentDescription = "",
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp)
                    .align(Alignment.TopEnd)
                    .clickable {
                        navHostController.navigate(Profile.route)
                    })
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0XFF353d3a))
                .padding(8.dp)
        ) {
            Column {
                Text(
                    text = "Little Lemon", style = TextStyle(color = Color.Yellow, fontSize = 38.sp)
                )
                Text(text = "Chicago", style = TextStyle(color = Color.White, fontSize = 28.sp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "We are family owned \n Mediterranean restaurant,\n " + "focused on traditional \nrecipes served with a modern twist.",
                        style = TextStyle(color = Color.White),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.hero),
                        contentDescription = "Hero image",
                        modifier = Modifier
                            .width(200.dp)
                            .clip(shape = RoundedCornerShape(20.dp))
                    )
                }

                TextField(value = searchPhrase, onValueChange = {
                    searchPhrase = it
                },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon"
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    placeholder = {
                        Text(
                            text = "Enter search phrase"
                        )
                    })
            }
        }
        val filteredMenuItems = remember {
            derivedStateOf {
                if (searchPhrase.isNotBlank()) {
                    menuItems.value.filter { menuItem ->
                        menuItem.title.contains(searchPhrase, ignoreCase = true) ||
                                menuItem.description.contains(searchPhrase, ignoreCase = true)
                    }
                } else {
                    menuItems.value
                }
            }
        }

        // Passing filtered menu items to MenuItems composable
        MenuItems(filteredMenuItems)
        //MenuItems(menuItems)

    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItems(menuItems: State<List<MenuItem>>) {
    //Log.d("TAG", "MenuItems: ${menuItems.value[0].price}")
    //Toast.makeText(LocalContext.current, "MenuItems: ${menuItems.value[0].price}", Toast.LENGTH_LONG).show()

    Column {
        Text(
            text = "ORDER FOOD FOR DELIVERY",
            style = TextStyle(fontWeight = FontWeight.Bold), modifier = Modifier.padding(8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
        ) {
            Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(end = 4.dp)) {
                Text(text = "Starters")
            }
            Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(end = 4.dp)) {
                Text(text = "Mains")
            }
            Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(end = 4.dp)) {
                Text(text = "Desserts")
            }
            Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(end = 4.dp)) {
                Text(text = "Drinks")
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp, start = 8.dp)
                .height(1.dp)
                .background(Color.Gray)
        )
        // menuItems.value.forEach { menuItem -> MenuItemCard(menuItem = menuItem)  }
        MenuItemCard(menuItems)


    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItemCard(menuItems: State<List<MenuItem>>) {
    Log.d("TAG", "MenuItemCard: " + menuItems.value.size.toString())
    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()

    ) {

        items(1) {
            menuItems.value.forEach { menu ->
                Text(text = menu.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Row(modifier = Modifier.fillMaxWidth()) {

                    GlideImage(
                        model = menu.image, contentDescription = "dish image",
                        modifier = Modifier.size(100.dp)
                    )
                    Text(text = menu.description, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun HomePreview() {

}