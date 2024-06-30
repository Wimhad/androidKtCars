package com.cars.race

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cars.race.R
import com.example.myapphaHA.Car
import com.example.myapphaHA.RaceManager
import com.example.myapphaHA.SUV
import com.example.myapphaHA.SportsCar
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var editTextNumberOfCars: EditText
    private lateinit var buttonStartRace: Button
    private lateinit var textViewResults: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNumberOfCars = findViewById(R.id.editTextNumberOfCars)
        buttonStartRace = findViewById(R.id.buttonStartRace)
        textViewResults = findViewById(R.id.textViewResults)

        buttonStartRace.setOnClickListener {
            val numberOfCars = editTextNumberOfCars.text.toString().toIntOrNull()
            if (numberOfCars != null && numberOfCars >= 2) {
                val cars = createCars(numberOfCars)
                raceCars(cars)
            } else {
                textViewResults.text = "Please enter a valid number (at least 2)."
            }
        }
    }

    private fun createCars(numberOfCars: Int): List<Car> {
        val cars = mutableListOf<Car>()
        repeat(numberOfCars) {
            val randomMake = listOf("Toyota", "Ford", "Chevrolet", "BMW", "Tesla").random()
            val randomModel = listOf("Camry", "F-150", "Corvette", "i8", "Model S").random()
            val randomYear = (1990..2024).random()

            // Randomly choose car type
            val carType = Random.nextInt(3)
            val car = when (carType) {
                0 -> SUV(randomMake, randomModel, randomYear, "AWD", 300)
                1 -> SportsCar(randomMake, randomModel, randomYear, 250)
                else -> Car(randomMake, randomModel, randomYear)
            }
            cars.add(car)
        }
        return cars
    }

    private fun raceCars(cars: List<Car>) {
        val results = StringBuilder()
        var remainingCars = cars.toMutableList()

        while (remainingCars.size > 1) {
            val car1Index = Random.nextInt(remainingCars.size)
            var car2Index = Random.nextInt(remainingCars.size)
            while (car2Index == car1Index) {
                car2Index = Random.nextInt(remainingCars.size)
            }

            val car1 = remainingCars[car1Index]
            val car2 = remainingCars[car2Index]
            val winner = RaceManager.race(car1, car2)

            results.append("\n${car1.getInfo()}\n VS \n${car2.getInfo()}, \n\nWinner! ${winner.getInfo()}\n-----------\n")

            remainingCars = remainingCars.filterIndexed { index, _ -> index != car1Index && index != car2Index }.toMutableList()
            remainingCars.add(winner)
        }

        results.append("Winner: ${remainingCars[0].getInfo()}")
        textViewResults.text = results.toString()
    }
}







