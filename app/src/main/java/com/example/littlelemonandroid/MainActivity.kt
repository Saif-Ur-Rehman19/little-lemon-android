package com.example.littlelemonandroid

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.littlelemonandroid.database.MenuDatabase
import com.example.littlelemonandroid.database.MenuItem
import com.example.littlelemonandroid.ui.theme.LittleLemonAndroidTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    object DatabaseManager {
        private var database: MenuDatabase? = null

        fun getDatabase(context: Context): MenuDatabase {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    MenuDatabase::class.java,
                    "menu.db"
                ).build()
            }
            return database!!
        }
    }


//      private val database by lazy {
//              Room.databaseBuilder(
//                  applicationContext,
//                  MenuDatabase::class.java,
//                  "menu.db"
//              ).build()
//    }
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }



    @OptIn(InternalAPI::class)
    private suspend fun fetchMenuData(): List<MenuItemNetwork> {
        try {
            val response = client.
            get(
                "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
                .body<MenuNetwork>()
            return response.menu
        } catch (exception: Exception){
            Log.d("TAG", "fetchMenuData: ${exception.message}")
            return emptyList()
        }
    }


    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    MyNavigation()

                }
            }
            lifecycleScope.launch {
                val menuData = fetchMenuData()

                val menuItems = menuData.map { networkItem ->
                  MenuItem(
                      id = networkItem.id,
                      title = networkItem.title,
                        description = networkItem.description,
                        price = networkItem.price,
                        image = networkItem.image,
                        category = networkItem.category
                    )
                }
                var sharedPreferences : SharedPreferences? = getSharedPreferences("sharedpref",Context.MODE_PRIVATE)
               withContext(IO){
                   if (sharedPreferences != null) {
                       if (!sharedPreferences.getBoolean("menuItemsSaved", false)) {
                           saveMenuItemsToDatabase(menuItems)
                           sharedPreferences.edit().putBoolean("menuItemsSaved", true).apply()
                       }
                   }
                }

           }
        }
    }

    @Composable
    fun MyNavigation() {
        val navController = rememberNavController()
        Navigation(navController = navController)
    }

    private fun saveMenuItemsToDatabase(menuItems: List<MenuItem>) {
        DatabaseManager.getDatabase(applicationContext).menuDao().saveMenuItems(menuItems)
    }
}