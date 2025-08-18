    package com.magicbag.laboratorio_05_ppm_sarah_estrada

    import android.content.Intent
    import android.net.Uri
    import android.os.Bundle
    import android.widget.Toast
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.activity.enableEdgeToEdge
    import androidx.compose.foundation.background
    import androidx.compose.foundation.border
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.foundation.shape.CircleShape
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.ArrowForward
    import androidx.compose.material.icons.filled.Refresh
    import androidx.compose.material3.Button
    import androidx.compose.material3.ButtonDefaults
    import androidx.compose.material3.ElevatedCard
    import androidx.compose.material3.Icon
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.Scaffold
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.rotate
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.graphics.RectangleShape
    import androidx.compose.ui.platform.LocalContext
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.text.style.TextAlign
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import com.magicbag.laboratorio_05_ppm_sarah_estrada.ui.theme.AppTheme

    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                AppTheme(dynamicColor = true) {
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
        val context = LocalContext.current
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .height(60.dp)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            shape = CircleShape
                        )
                        .padding(10.dp)
                        .size(25.dp),
                    tint = MaterialTheme.colorScheme.onSecondary
                )
                Text(
                    text = "Actualización disponible",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start
                )

                Button(
                    onClick = {
                        val appPackageName = "com.magicbag.laboratorio_05_ppm_sarah_estrada"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName"))
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    Text(
                        text ="Descargar",
                        textAlign = TextAlign.End
                    )
                }
            }

            Column(modifier = Modifier
                .padding(16.dp)
            ){
                Text(
                    text = "Martes",
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Row(modifier = Modifier
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "1 de enero",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Text(
                        text = "Terminar jornada",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .border(
                                width = 3.dp,
                                shape = RoundedCornerShape(7.dp),
                                color = MaterialTheme.colorScheme.secondaryContainer
                            )
                            .padding(10.dp)
                    )
                }

            }

            ElevatedCard (modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .padding(horizontal = 14.dp),
                shape = RectangleShape
            ){
               Row(modifier = Modifier
                   .fillMaxWidth()
                   .padding(horizontal = 16.dp),
                   horizontalArrangement = Arrangement.SpaceBetween,
                   verticalAlignment = Alignment.CenterVertically
               ){
                   Text(
                       text = "Portal del Ángel",
                       style = MaterialTheme.typography.displaySmall,
                       fontWeight = FontWeight.Bold,
                       color = MaterialTheme.colorScheme.primary
                   )

                   Button(
                       onClick = {
                           val gmmIntentUri = Uri.parse("geo:14.5826014,-90.475069,17")
                           val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                           mapIntent.setPackage("com.google.android.apps.maps")
                           context.startActivity(mapIntent)
                       },
                       modifier = Modifier
                           .rotate(45f),
                       colors = ButtonDefaults.buttonColors(
                           containerColor = Color.Transparent,
                           contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                       ),
                       shape = RoundedCornerShape(5.dp),
                   ){
                       Icon(
                           Icons.Default.ArrowForward,
                           contentDescription = "Maps",
                           modifier = Modifier
                               .background(
                                   color = MaterialTheme.colorScheme.onPrimaryContainer,
                                   shape = RoundedCornerShape(5.dp)
                               )
                               .rotate(-45f),
                           tint = MaterialTheme.colorScheme.onSecondary
                       )
                   }
               }

                Column(
                    modifier = Modifier
                        .padding(horizontal = 14.dp)
                        .padding(vertical = 5.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    Text(
                        text = "Km 11.2 Carr. a El Salvador",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.labelLarge
                    )

                    Text(
                        text = "6:00AM 11:00PM",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.labelMedium
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            Toast.makeText(
                                context,
                                "Sarah Rachel Estrada Bonilla",
                                Toast.LENGTH_LONG
                            ).show()
                        },
                        modifier = Modifier
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.secondaryContainer
                        )
                    ) {
                        Text(
                            text = "Iniciar"
                        )
                    }

                    Button(
                        onClick = {
                            Toast.makeText(
                                context,
                                "Steak House",
                                Toast.LENGTH_LONG
                            ).show()

                            Toast.makeText(
                                context,
                                "QQQ",
                                Toast.LENGTH_LONG
                            ).show()
                        },
                        modifier = Modifier
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = "Detalles"
                        )
                    }

                }
            }

        }

    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        AppTheme{
            Greeting()
        }
    }