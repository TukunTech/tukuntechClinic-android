package pe.edu.upc.tukuntech.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.edu.upc.tukuntech.R
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.tooling.preview.Preview
import pe.edu.upc.tukuntech.domain.models.Patient
import pe.edu.upc.tukuntech.presentation.sampledata.samplePatients

@Preview
@Composable
fun PostOperativeView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Header con logo y título
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.tukuntech),
                contentDescription = "Logo",
                modifier = Modifier.size(48.dp)
            )
            Text(
                text = "Postoperative Patients",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de pacientes
        LazyColumn {
            items(samplePatients) { patient ->
                PatientCard(patient)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }


    }
}


@Composable
fun PatientCard(patient: Patient) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEFEFEF))
                .padding(16.dp)
        ) {
            Text(
                text = patient.bed,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            VitalSign(label = "HR", value = "${patient.hr}", unit = "bpm", color = Color.Red)
            VitalSign(label = "NIPB", value = patient.nibp, unit = "mmHg", color = Color(0xFF2E7D32))
            VitalSign(label = "SpO2", value = "${patient.spo2}", unit = "%", color = Color(0xFF6A1B9A))
            VitalSign(label = "Temp", value = "${patient.temp}", unit = "°C", color = Color.Gray)
        }
    }
}

@Composable
fun VitalSign(label: String, value: String, unit: String, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, fontSize = 14.sp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = value,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = color
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = unit, fontSize = 12.sp, color = color)
        }
    }
}