package com.example.mainbalanceanimations


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                BalanceApp()
            }
        }
    }
}

// ---------- Balance Card ----------
@Composable
fun BalanceCard(balance: Int, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() }, // ✅ clickable
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            width = 3.dp,
            color = if (isSelected) Color.Red else Color.Red // ✅ selected হলে black border
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Balance: ৳$balance",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// ---------- Data Plan Card ----------
@Composable
fun DataPlanCard(
    dataAmount: String,
    validityDays: String,
    price: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(220.dp)
            .padding(6.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            width = 3.dp,
            color = if (isSelected) Color.Red else Color.Gray // ✅ selected হলে black border
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(16.dp)
        ) {
            // Top Start corner
            Text(
                text = dataAmount,
                style = MaterialTheme.typography.headlineMedium,
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
                        .padding(end = 4.dp)
                        .size(20.dp)
                )
                Text(
                    text = validityDays,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Bottom End corner
            Text(
                text = "৳$price",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}

// ---------- Data Plan Model ----------
data class DataPlan(
    val dataAmount: String,
    val validityDays: String,
    val price: Int
)

val plans = listOf(
    DataPlan("30 GB", "30 DAYS", 299),
    DataPlan("15 GB", "15 DAYS", 199),
    DataPlan("5 GB", "7 DAYS", 99),
    DataPlan("2 GB", "3 DAYS", 70),
    DataPlan("1 GB", "2 Hours", 9),
)

// ---------- Main App ----------
@Composable
fun BalanceApp() {
    var balance by remember { mutableIntStateOf(1000) } // initial balance
    var selectedIndex by remember { mutableIntStateOf(-1) } // কোনটা select হলো track করবে

    Column {
        // Balance Card (index = -1)
        BalanceCard(
            balance = balance,
            isSelected = selectedIndex == -1,
            onClick = { selectedIndex = -1 }
        )

        // Grid layout
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // ✅ 2 column grid
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(plans.size) { index ->
                val plan = plans[index]
                DataPlanCard(
                    dataAmount = plan.dataAmount,
                    validityDays = plan.validityDays,
                    price = plan.price,
                    isSelected = index == selectedIndex,
                    onClick = {
                        selectedIndex = index
                        if (balance >= plan.price) {
                            balance -= plan.price
                        }
                    }
                )
            }
        }
    }
}

