package com.example.apecapplication

data class OrdersBookDb(val id: Int, val customerName: String, val bookName: String, val amount: Int = 10)