package pe.edu.upc.tukuntech.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.upc.tukuntech.presentation.views.ClinicHomeView
import pe.edu.upc.tukuntech.presentation.views.ICUView
import pe.edu.upc.tukuntech.presentation.views.PatientRegistrationView
import pe.edu.upc.tukuntech.presentation.views.PostOperativeView
@Preview

@Composable
fun Home() {

    val navController = rememberNavController()

    val navigationItems = listOf(
        NavigationItem(
            icon = Icons.Default.Home,
            title = "Home",
            route = "home"
        ),
        NavigationItem(
            icon = Icons.Default.Favorite,
            title = "PostOperative",
            route = "post_operative"
        ),
        NavigationItem(
            icon = Icons.Default.FavoriteBorder,
            title = "UCI",
            route = "uci"
        ),
        NavigationItem(
            icon = Icons.Default.Person,
            title = "Patients",
            route = "patients"
        )
    )

    val selectedIndex = remember {
        mutableStateOf(0)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar {
                navigationItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedIndex.value == index,
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        },
                        onClick = {
                            selectedIndex.value = index
                            navController.navigate(item.route)
                        },
                        label = {
                            Text(item.title)
                        }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home") {
                ClinicHomeView(navController)

            }
            composable("post_operative") {
                PostOperativeView()

            }
            composable("uci") {
                ICUView()

            }
            composable("patients") {
                PatientRegistrationView() //TODO: Implementar vista de pacientes, por el momento redirecciona a reguistro de pacientes

            }
        }
    }
}

data class NavigationItem(
    val icon: ImageVector,
    val title: String,
    val route: String
)

