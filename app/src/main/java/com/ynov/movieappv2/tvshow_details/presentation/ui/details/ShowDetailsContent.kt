package com.ynov.movieappv2.tvshow_details.presentation.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import com.ynov.movieappv2.R
import com.ynov.movieappv2.tvshow_details.domain.model.ShowDetails

@Composable
fun ShowDetailsContent(showDetails: ShowDetails) {
    val scrollState = rememberScrollState()
    // Nouvelle palette aqua/turquoise
    val darkTurquoise = Color(0xFF026E70)     // Couleur de fond principale
    val mediumTurquoise = Color(0xFF25ADB0)   // Accent secondaire
    val lightTurquoise = Color(0xFF5DE0E3)    // Accent primaire
    val brightCyan = Color(0xFF00E6F0)        // Couleur pour boutons d'action
    val paleTurquoise = Color(0xFFB3F2F5)     // Couleur texte claire

    // Dégradé adapté pour l'overlay
    val colors = listOf(
        Color.Transparent,
        darkTurquoise.copy(alpha = 0.5f),
        darkTurquoise.copy(alpha = 0.9f),
        darkTurquoise
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(darkTurquoise)
            .verticalScroll(scrollState)
    ) {
        // Header with backdrop image and gradient overlay
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp)
        ) {
            // Backdrop image with error handling
            if (showDetails.imageUrl.isNotEmpty()) {
                AsyncImage(
                    model = showDetails.imageUrl,
                    contentDescription = "Show Poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    error = painterResource(id = R.drawable.ic_launcher_background),
                    placeholder = ColorPainter(mediumTurquoise)
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(mediumTurquoise)
                )
            }

            // Gradient overlay for better text visibility
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = colors,
                            startY = 150f,
                            endY = 380f
                        )
                    )
            )

            // Back button
            IconButton(
                onClick = { /* Navigate back */ },
                modifier = Modifier
                    .padding(16.dp)
                    .size(36.dp)
                    .background(mediumTurquoise.copy(alpha = 0.6f), shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Title and year
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
            ) {
                Text(
                    text = showDetails.name,
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        shadow = Shadow(
                            color = darkTurquoise,
                            offset = Offset(1f, 1f),
                            blurRadius = 3f
                        )
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                // Year and country as pill
                if (showDetails.startDate.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val year = if (showDetails.startDate.length >= 4)
                            showDetails.startDate.take(4) else showDetails.startDate
                        StatusPill(text = year, backgroundColor = mediumTurquoise.copy(alpha = 0.7f))

                        if (showDetails.country.isNotEmpty()) {
                            StatusPill(text = showDetails.country,
                                backgroundColor = mediumTurquoise.copy(alpha = 0.7f))
                        }
                    }
                }
            }
        }

        // Action buttons row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ActionButton(
                text = "Play",
                icon = Icons.Default.PlayArrow,
                modifier = Modifier.weight(1f),
                backgroundColor = brightCyan
            )

            ActionButton(
                text = "My List",
                icon = Icons.Default.Add,
                modifier = Modifier.weight(1f),
                backgroundColor = mediumTurquoise
            )
        }

        // Status
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatusPill(
                text = showDetails.status,
                backgroundColor = when (showDetails.status.lowercase()) {
                    "running" -> brightCyan
                    "ended" -> mediumTurquoise
                    "tba" -> lightTurquoise
                    else -> mediumTurquoise
                }
            )

            if (showDetails.network.isNotEmpty()) {
                Text(
                    text = " on ${showDetails.network}",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = paleTurquoise
                    )
                )
            }
        }

        // Description
        if (showDetails.description.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = showDetails.description,
                style = TextStyle(
                    fontSize = 15.sp,
                    color = Color.White,
                    lineHeight = 22.sp
                ),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // Info card
        Spacer(modifier = Modifier.height(24.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = mediumTurquoise.copy(alpha = 0.3f)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Show Info",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // ID and details
                InfoRow(label = "ID", value = showDetails.id.toString(), labelColor = paleTurquoise)
                if (showDetails.startDate.isNotEmpty()) {
                    InfoRow(label = "Start Date", value = showDetails.startDate,
                        labelColor = paleTurquoise)
                }
                if (showDetails.country.isNotEmpty()) {
                    InfoRow(label = "Country", value = showDetails.country,
                        labelColor = paleTurquoise)
                }
                if (showDetails.network.isNotEmpty()) {
                    InfoRow(label = "Network", value = showDetails.network,
                        labelColor = paleTurquoise)
                }
            }
        }

        // Extra space at bottom
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun StatusPill(text: String, backgroundColor: Color) {
    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        )
    }
}

@Composable
fun ActionButton(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    backgroundColor: Color
) {
    Button(
        onClick = { /* Action */ },
        modifier = modifier.height(40.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}

@Composable
fun InfoRow(label: String, value: String, labelColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "$label:",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = labelColor
            ),
            modifier = Modifier.width(100.dp)
        )
        Text(
            text = value,
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.White
            )
        )
    }
}