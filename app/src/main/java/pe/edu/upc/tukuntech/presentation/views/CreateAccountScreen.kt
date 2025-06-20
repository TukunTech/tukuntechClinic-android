package pe.edu.upc.tukuntech.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.upc.tukuntech.R
import pe.edu.upc.tukuntech.presentation.di.PresentationModule

@Composable
fun CreateAccountScreen(
    navController: NavController,
    modifier: Modifier = Modifier

)
{

    val companyName = remember{
        mutableStateOf("")
    }

    val ruc = remember{
        mutableStateOf("")
    }

    val email = remember{
        mutableStateOf("")
    }

    val companyPhone = remember{
        mutableStateOf("")
    }

    val city = remember{
        mutableStateOf("")
    }

    val password = remember{
        mutableStateOf("")
    }

    Box(
        modifier = modifier
            .fillMaxSize()


    ){

        Image(
            painter = painterResource(id = R.drawable.login_screen),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Image(
            painter = painterResource(id = R.drawable.tukuntech_logo),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(10.dp)
                .size(120.dp)
        )

        Box(
            modifier = modifier
                .align(Alignment.TopCenter)
                .padding(70.dp, 120.dp)

        ){
            Text("Create an account", color = Color.White, fontSize = 25.sp)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp)
                .padding(top = 160.dp) // Puedes ajustar según el diseño
                .clip(RoundedCornerShape(32.dp))
                .background(Color.White.copy(alpha = 0.9f))
                .padding(40.dp)
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        )

        {

            val selectedOption = remember { mutableStateOf("Clinic") }

            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFFE0F7F9))
                    .padding(2.dp)
                    .width(260.dp)  // Ancho fijo que ajusta el toggle a tamaño similar a la imagen
                    .height(40.dp),
                horizontalArrangement = Arrangement.Center, // Centrar los botones
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf("Clinic", "Elder").forEach { option ->
                    val isSelected = selectedOption.value == option

                    Button(
                        onClick = { selectedOption.value = option },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(horizontal = 4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected) Color(0xFF5BB9BA) else Color.Transparent,
                            contentColor = if (isSelected) Color.White else Color(0xFF5BB9BA)
                        ),
                        elevation = null,
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(text = option, fontSize = 14.sp)
                    }
                }
            }



            OutlinedTextField(
                label = {
                    Text("Company name")
                },
                value = companyName.value,
                onValueChange = {
                    companyName.value = it
                }
            )

            Spacer(modifier = Modifier.height(2.dp))

            OutlinedTextField(
                label = {
                    Text("RUC")
                },
                value = ruc.value,
                onValueChange = {
                    ruc.value = it
                }
            )

            Spacer(modifier = Modifier.height(2.dp))

            OutlinedTextField(
                label = {
                    Text("Email")
                },
                value = email.value,
                onValueChange = {
                    email.value = it
                }
            )

            Spacer(modifier = Modifier.height(2.dp))



            OutlinedTextField(
                label = {
                    Text("Company Phone Number")
                },
                value = companyPhone.value,
                onValueChange = {
                    companyPhone.value = it
                }
            )

            Spacer(modifier = Modifier.height(2.dp))

            OutlinedTextField(
                label = {
                    Text("City")
                },
                value = city.value,
                onValueChange = {
                    city.value = it
                }
            )

            Spacer(modifier = Modifier.height(2.dp))

            OutlinedTextField(
                label = {
                    Text("Password")
                },
                value = password.value,
                onValueChange = {
                    password.value = it
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                modifier = Modifier
                    .size(150.dp, 40.dp),
                onClick = {
                    navController.navigate("login")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0c95a2)
                )


            ) {
                Text("Create")
            }

            Spacer(modifier = Modifier.height(2.dp))


            HorizontalDivider(
                color = Color.Black,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text("Already have an account")


            Button(
                onClick = {
                    navController.navigate("login")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0FA596)
                )

            ) {
                Text("Login")
            }

        }

    }
}