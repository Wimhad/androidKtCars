package com.example.myapphaHA

import kotlin.random.Random

object RaceManager {
    fun race(car1: Car, car2: Car): Car {
        val random = Random.nextInt(2) // لاختيار الفائز بشكل عشوائي بين السيارتين
        return if (random == 0) car1 else car2
    }
}