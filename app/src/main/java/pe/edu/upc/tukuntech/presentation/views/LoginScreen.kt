package pe.edu.upc.tukuntech.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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


@Composable
fun LoginScreen(navController: NavController, modifier: Modifier = Modifier) {

    val email = remember{
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
               .padding(120.dp)

       ){
           Text("Log in", color = Color.White, fontSize = 25.sp)
       }


       Column(
           modifier = Modifier
               .fillMaxWidth()
               .padding(22.dp)
               .clip(RoundedCornerShape(32.dp))
               .background(Color.White.copy(alpha = 0.9f))
               .padding(40.dp)
               .align(Alignment.Center),
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           Row (
               horizontalArrangement = Arrangement.spacedBy(8.dp,  Alignment.CenterHorizontally),
               modifier = Modifier.fillMaxWidth(),
               verticalAlignment = Alignment.CenterVertically
           ) {
                   IconButton(
                       onClick = {}
                   ) {
                       Icon(
                           painter = painterResource(id = R.drawable.google),
                           contentDescription = "Google",
                           tint = Color.Unspecified,
                           modifier = Modifier.size(36.dp)
                       )

                   }

               IconButton(
                   onClick = {}
               ) {
                   Icon(
                       painter = painterResource(id = R.drawable.ios),
                       contentDescription = "Google",
                       tint = Color.Unspecified,
                       modifier = Modifier.size(36.dp)
                   )

               }

               IconButton(
                   onClick = {}
               ) {
                   Icon(
                       painter = painterResource(id = R.drawable.facebook),
                       contentDescription = "Google",
                       tint = Color.Unspecified,
                       modifier = Modifier.size(36.dp)
                   )

               }

           }

           val selectedOption = remember { mutableStateOf("Clinic") }

           Row(
               modifier = Modifier
                   .clip(RoundedCornerShape(50))
                   .background(Color(0xFFE0F7F9))
                   .padding(2.dp)
                   .fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceBetween
           ) {
               listOf("Clinic", "Elder").forEach { option ->
                   val isSelected = selectedOption.value == option

                   Button(
                       onClick = { selectedOption.value = option },
                       shape = RoundedCornerShape(50),
                       modifier = Modifier
                           .weight(1f)
                           .height(40.dp),
                       colors = ButtonDefaults.buttonColors(
                           containerColor = if (isSelected) Color(0xFF5BB9BA) else Color.Transparent,
                           contentColor = if (isSelected) Color.White else Color(0xFF5BB9BA)
                       ),
                       elevation = null
                   ) {
                       Text(text = option)
                   }
               }
           }

           OutlinedTextField(
               label = {
                   Text("Email")
               },
               value = email.value,
               onValueChange = {
                    email.value = it
               }
           )

           Spacer(modifier = Modifier.height(12.dp))

           OutlinedTextField(
               label = {
                   Text("Password")
               },
               value = password.value,
               onValueChange = {
                   password.value = it
               }
           )

           Spacer(modifier = Modifier.height(24.dp))

           Button(
               modifier = Modifier
                   .size(150.dp, 40.dp),
               onClick = {
                   navController.navigate("home")
               },
               colors = ButtonDefaults.buttonColors(
                 containerColor = Color(0xFF0c95a2)
               )


           ) {
               Text("Log In")
           }

           Spacer(modifier = Modifier.height(16.dp))

           Text("I forgot my password")

          Divider(
              color = Color.Black,
              thickness = 1.dp,
              modifier = Modifier.padding(vertical = 8.dp)
          )

           Text("Don't have an account?")

           Spacer(modifier = Modifier.height(16.dp))
           Button(
               onClick = { /* otra acción */ },
               modifier = Modifier.fillMaxWidth(),
               colors = ButtonDefaults.buttonColors(
                   containerColor = Color(0xFF0FA596)
               )

           ) {
               Text("Create an account")
           }

       }

   }

}

