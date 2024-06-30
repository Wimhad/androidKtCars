package com.example.myapphaHA

class SportsCar(make: String, model: String, year: Int, val topSpeed: Int) :
    Car(make, model, year) {

    override fun getInfo(): String {
        return super.getInfo() + ", Top Speed: $topSpeed km/h"
    }
}