package com.example.mainbalanceanimations.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mainbalanceanimations.data.plans
import com.example.mainbalanceanimations.ui.theme.AppTypography

@Composable
fun GradientDataPlanCard(
    dataAmount: String,
    validityDays: String,
    price: Any,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val displayPrice = if (price is Int) "৳$price" else price.toString()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            width = if (isSelected) 3.5.dp else 2.dp,
            brush = if (isSelected) {
                Brush.linearGradient(
                    colors = listOf(
                        Color.Red,
                        Color(0xFFFFC0CB), // Pink
                        Color(0xFF800080)  // Purple
                    )
                )
            } else {
                Brush.linearGradient(
                    colors = listOf(
                        Color.LightGray,
                        Color.LightGray
                    )
                )
            }
        ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFF800080).copy(alpha = 0.05f) else Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(10.dp)
        ) {
            // Top Start corner
            Text(
                text = dataAmount,
                style = AppTypography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.TopStart)
            )

            // Bottom Start corner
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.BottomStart)
            ) {
                Icon(
                    imageVector = Icons.Filled.AccessTime,
                    contentDescription = "Duration",
                    modifier = Modifier
                        .padding(start = 2.dp)
                        .size(14.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = validityDays,
                    fontSize = 11.sp,
                    style = AppTypography.displaySmall
                )
            }

            // Bottom End corner
            Text(
                text = displayPrice,
                fontSize = 14.sp,
                style = AppTypography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}

@Composable
fun GradientPlanContent() {
    var selectedIndex by remember { mutableIntStateOf(-1) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        itemsIndexed(plans) { index, plan ->
            GradientDataPlanCard(
                dataAmount = plan.dataAmount,
                validityDays = plan.validityDays,
                price = plan.price,
                isSelected = index == selectedIndex,
                onClick = { selectedIndex = index }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradientPlanScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Gradient Plans") }) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            GradientPlanContent()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GradientPlanScreenPreview() {
    MaterialTheme {
        GradientPlanScreen()
    }
}
