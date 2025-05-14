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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.upc.tukuntech.R

@Composable
fun ClinicHomeView(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1C)) // fondo exterior negro
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Fila superior: botón logout (izquierda) y logo (derecha)
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Button(
                    onClick = {
                        navController.navigate("login")

                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                ) {
                    Text("Log out", color = Color.Black)
                }

                Image(
                    painter = painterResource(id = R.drawable.tukuntech),
                    contentDescription = "TukunTech logo",
                    modifier = Modifier.size(64.dp) // Logo más grande
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Centro del contenido: título y botones
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Where do you want to start?",
                    fontSize = 25.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(24.dp))

                ClinicPostOperativeButton("Postoperative Patients", navController)
                ClinicICUButton("ICU Patients", navController)
                //ClinicHomeButton("Patients")
            }
        }
    }
}


@Composable
fun ClinicPostOperativeButton(text: String, navController: NavController) {
    Button(
        onClick = {
            navController.navigate("post_operative")
        },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00ACC1)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .height(48.dp)
    ) {
        Text(
            text = text,
            fontSize = 18.sp
        )
    }
}

@Composable
fun ClinicICUButton(text: String, navController: NavController) {
    Button(
        onClick = {
            navController.navigate("uci")
        },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00ACC1)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .height(48.dp)
    ) {
        Text(
            text = text,
            fontSize = 18.sp
        )
    }
}