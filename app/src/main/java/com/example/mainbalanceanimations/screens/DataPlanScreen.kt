package com.example.mainbalanceanimations.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mainbalanceanimations.data.DataPlan

val samplePlans = listOf(
    DataPlan("30 GB", "30 DAYS", 299),
    DataPlan("15 GB", "15 DAYS", 199),
    DataPlan("5 GB", "7 DAYS", 99),
    DataPlan("12 GB", "12 DAYS", 129),
    DataPlan("15 GB", "18 DAYS", 159),
    DataPlan("18 GB", "10 DAYS", 179),
    DataPlan("20 GB", "10 DAYS", 199),
    DataPlan("25 GB", "10 DAYS", 249),
    DataPlan("30 GB", "15 DAYS", 279),
    DataPlan("40 GB", "15 DAYS", 349),
    DataPlan("50 GB", "10 DAYS", 399),
)

@Composable
fun DataPlanContent() {
    var selectedIndex by remember { mutableIntStateOf(-1) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        itemsIndexed(samplePlans) { index, plan ->
            DataPlanCard(
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
fun DataPlanScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Data Plans") }) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            DataPlanContent()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DataPlanScreenPreview() {
    MaterialTheme {
        DataPlanScreen()
    }
}
