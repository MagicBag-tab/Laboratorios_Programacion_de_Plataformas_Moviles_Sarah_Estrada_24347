package com.magicbag.laboratorio_07_ppm_sarah_estrada

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.magicbag.laboratorio_07_ppm_sarah_estrada.ui.theme.Laboratorio_07_PPM_Sarah_EstradaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio_07_PPM_Sarah_EstradaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantallaNotificaciones(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun IconChange(
    fondo: Boolean
){
    if(fondo){
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = null,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    shape = CircleShape
                )
                .padding(4.dp)
        )
    }else{
        Icon(
            imageVector = Icons.Default.DateRange,
            contentDescription = null,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = CircleShape
                )
                .padding(4.dp)
        )
    }
}

@Composable
fun NotificacionesPlantilla(
    tipo: Boolean,
    titulo: String,
    fecha: String,
    text: String
){
    Row(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){

        IconChange(tipo)

        Column(modifier = Modifier
            .weight(1f)
        ){
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ){
                Text(
                    text = titulo,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, fill = true)

                )

                Text(
                    text = fecha,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    modifier = Modifier.padding(start = 8.dp)

                )
            }

            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
fun Filtro(
    type: String,
    isSelected: Boolean,
    onSelectionChange: (Boolean) -> Unit
){
    FilterChip(
        onClick = { onSelectionChange(!isSelected) },
        label = {
            Text( text = type,
                style = MaterialTheme.typography.labelMedium)
        },
        selected = isSelected,
        leadingIcon = if(isSelected){
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = null,
                    modifier = Modifier
                        .size(FilterChipDefaults.IconSize)
                )
            }
        }else{
            null
        },
        modifier = Modifier
            .height(20.dp)
    )
}

@Composable
fun PantallaNotificaciones(
    modifier: Modifier = Modifier
) {

    val notifications = remember { generateFakeNotifications() }

    var showGeneral by rememberSaveable { mutableStateOf(true) }
    var showMeetings by rememberSaveable { mutableStateOf(true) }

    val filteredNotifications = remember(showGeneral, showMeetings, notifications) {
        notifications.filter { notification ->
            val isGeneral = notification.type == NotificationType.GENERAL
            val isMeeting = notification.type == NotificationType.NEW_MEETING

            (isGeneral && showGeneral) || (isMeeting && showMeetings)
        }
    }

    Column(modifier = modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer
            )
            .height(48.dp)
            .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Top
        ){
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null
            )

            Text(
                text = "Notificaciones",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = "Tipos de Notificaciones",
            modifier = Modifier
                .padding(14.dp),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold
        )

        Row(modifier = Modifier
            .padding(horizontal = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Filtro(
                type = "Generales",
                isSelected = showGeneral,
                onSelectionChange = { showGeneral = it }
            )
            Filtro(
                type = "Reuniones",
                isSelected = showMeetings,
                onSelectionChange = { showMeetings = it }
            )
        }

        OutlinedCard(modifier = Modifier
            .padding(14.dp)
            .fillMaxWidth()
        ) {
            LazyColumn {
                items(filteredNotifications) { notification ->
                    NotificacionesPlantilla(
                        tipo = (notification.type == NotificationType.GENERAL),
                        titulo = notification.title,
                        fecha = notification.sendAt,
                        text = notification.body
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Laboratorio_07_PPM_Sarah_EstradaTheme {
        PantallaNotificaciones()
    }
}