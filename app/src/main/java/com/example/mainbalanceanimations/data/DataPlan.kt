package com.example.mainbalanceanimations.data

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
    DataPlan("50 GB", "30 DAYS", 1299),
    DataPlan("55 GB", "15 DAYS", 1199),
    DataPlan("100 GB", "7 DAYS", 1199),
    DataPlan("20 GB", "3 DAYS", 170),
    DataPlan("10 GB", "2 Hours", 9),
    DataPlan("35 GB", "30 DAYS", 1299),
    DataPlan("75 GB", "15 DAYS", 1199),
    DataPlan("85 GB", "7 DAYS", 1199),
    DataPlan("20 GB", "3 DAYS", 70),
    DataPlan("1 GB", "2 Hours", 9)
)
