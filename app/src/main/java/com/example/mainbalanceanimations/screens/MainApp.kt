package com.example.mainbalanceanimations.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mainbalanceanimations.data.plans

@Composable
fun BalanceApp() {
    var balance by remember { mutableIntStateOf(1000) }
    var selectedIndex by remember { mutableIntStateOf(-1) }

    Column {
        BalanceCard(
            balance = balance,
            isSelected = selectedIndex == -1,
            onClick = { selectedIndex = -1 }
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
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