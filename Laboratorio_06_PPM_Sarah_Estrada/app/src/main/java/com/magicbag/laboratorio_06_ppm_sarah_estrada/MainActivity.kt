package com.magicbag.laboratorio_06_ppm_sarah_estrada

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.magicbag.laboratorio_06_ppm_sarah_estrada.ui.theme.Laboratorio_06_PPM_Sarah_EstradaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio_06_PPM_Sarah_EstradaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {

    var contador by rememberSaveable { mutableIntStateOf(0) }
    var incrementos by rememberSaveable { mutableIntStateOf(0) }
    var decrementos by rememberSaveable { mutableIntStateOf(0) }
    var max by rememberSaveable { mutableIntStateOf(0) }
    var min by rememberSaveable { mutableIntStateOf(0) }
    var cambios by rememberSaveable { mutableIntStateOf(0) }
    var listaDeNumeros by rememberSaveable { mutableStateOf(listOf<Int>()) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(
            color = MaterialTheme.colorScheme.background
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){

        Text(
            text = "Sarah Estrada",
            style = MaterialTheme.typography.displayMedium,
        )

        Row(modifier = Modifier
            .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Button(
                onClick = {
                    contador--
                    decrementos++
                    cambios++
                    listaDeNumeros += contador
                    if (contador < min) min = contador
                },
                shape = CircleShape
            ) {
                Text(
                    text = "-"
                )
            }
            Text(
                text = contador.toString(),
                style = MaterialTheme.typography.headlineMedium
            )

            Button(
                onClick = {
                    contador++
                    incrementos++
                    cambios++
                    listaDeNumeros += contador
                    if (contador > max) max = contador
                },
                shape = CircleShape
            ) {
                Text(
                    text = "+"
                )
            }

        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total Incrementos: ",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = incrementos.toString(),
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total Decrementos: ",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = decrementos.toString(),
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Valor máximo: ",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = max.toString(),
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Valor Mínimo: ",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = min.toString(),
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total Cambios: ",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = cambios.toString(),
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Text(
                text = "Historial: ",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            LazyVerticalGrid(
                columns = GridCells.Fixed(5),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(listaDeNumeros){ index, numero ->

                    var colorFondo = Color.Green

                    if (index == 0) {
                        colorFondo = Color.Green
                    } else {
                        val numeroAnterior = listaDeNumeros[index - 1]
                        if (numero > numeroAnterior) {
                            colorFondo = Color.Green
                        } else if (numero < numeroAnterior) {
                            colorFondo = Color.Red
                        }
                    }

                    Text(
                        text = numero.toString(),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(4.dp)
                            .background(
                                color = colorFondo,
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(8.dp)
                    )
                }
            }
            Button(
                onClick = {
                    contador = 0
                    incrementos = 0
                    decrementos = 0
                    max = 0
                    min = 0
                    cambios = 0
                    listaDeNumeros = listOf()

                },
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(
                    text = "Reiniciar"
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Laboratorio_06_PPM_Sarah_EstradaTheme {
        Greeting()
    }
}