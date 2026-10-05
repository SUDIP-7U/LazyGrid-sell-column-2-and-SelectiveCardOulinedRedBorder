package com.example.mainbalanceanimations.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mainbalanceanimations.R

val bold = FontFamily(
    Font(R.font.consebold)
)

// ---------- Balance Card ----------
@Composable
fun BalanceCard(
    balance: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            width = if (isSelected) 3.dp else 1.dp,
            color = if (isSelected) Color.Red else Color.LightGray
        ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color.Red.copy(alpha = 0.05f) else Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            focusedElevation = 0.dp,
            hoveredElevation = 0.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Balance: ৳$balance",
                style = MaterialTheme.typography.titleLarge,
                fontFamily = bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

// ---------- Data Plan Card ----------
@Composable
fun DataPlanCard(
    dataAmount: String,
    validityDays: String,
    price: Any,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val displayPrice = if (price is Int) "৳$price" else price.toString()

    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            width = if (isSelected) 2.5.dp else 1.dp,
            color = if (isSelected) Color.Red else Color.LightGray
        ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color.Red.copy(alpha = 0.05f) else Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            focusedElevation = 0.dp,
            hoveredElevation = 0.dp
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
                style = MaterialTheme.typography.titleMedium,
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
                        .padding(end = 2.dp)
                        .size(16.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = validityDays,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Bottom End corner
            Text(
                text = displayPrice,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BalanceCardPreview() {
    MaterialTheme {
        BalanceCard(balance = 1000, isSelected = true, onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun DataPlanCardPreview() {
    MaterialTheme {
        Row(modifier = Modifier.padding(16.dp)) {
            DataPlanCard(
                dataAmount = "30 GB",
                validityDays = "30 DAYS",
                price = 299,
                isSelected = true,
                onClick = {}
            )
        }
    }
}
