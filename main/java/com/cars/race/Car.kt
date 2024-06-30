package com.example.myapphaHA

open class Car(val make: String, val model: String, val year: Int) {
    open fun getInfo(): String {
        return "Car: $make $model ($year)"
    }
}