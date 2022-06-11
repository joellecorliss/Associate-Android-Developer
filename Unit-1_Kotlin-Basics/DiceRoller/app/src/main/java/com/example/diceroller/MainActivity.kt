package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)
        val flipCoin: Button = findViewById(R.id.flipCoin)

        val toast = Toast.makeText(this, "Dice rolled!", Toast.LENGTH_SHORT)

        rollButton.setOnClickListener {
            rollDice()
            toast.show()
        }

        flipCoin.setOnClickListener {
            flipCoin()
        }

        //Do a dice roll when the app starts
        rollDice()
    }

    /**
     * Roll the dice and update the screen with the result.
     */
    private fun rollDice() {
        // Create 2 Dice objects with 6 sides and roll them
        val diceOne = Dice(6)
        val diceTwo = Dice(6)
        // Update the screen with the dice roll
        val diceRollOne = diceOne.roll()
        val diceRollTwo = diceTwo.roll()

        val diceImageOne: ImageView = findViewById(R.id.diceOne)
        val diceImageTwo: ImageView = findViewById(R.id.diceTwo)
//        when (diceOne.roll()) {
//            1 -> diceImage.setImageResource(R.drawable.dice_1)
//            2 -> diceImage.setImageResource(R.drawable.dice_2)
//            3 -> diceImage.setImageResource(R.drawable.dice_3)
//            4 -> diceImage.setImageResource(R.drawable.dice_4)
//            5 -> diceImage.setImageResource(R.drawable.dice_5)
//            6 -> diceImage.setImageResource(R.drawable.dice_6)
//        }

        //Update the ImageView with the correct drawable resource ID
        val drawableResourceOne = when (diceRollOne) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        val drawableResourceTwo = when (diceRollTwo) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        diceImageOne.setImageResource(drawableResourceOne)
        diceImageTwo.setImageResource(drawableResourceTwo)

        //Update content description - for accessibility
        diceImageOne.contentDescription = diceRollOne.toString()
        diceImageTwo.contentDescription = diceRollTwo.toString()

    }

    private fun flipCoin() {
        val coin = Coin()
        val coinFlipResult = coin.flip()

        val flipText: TextView = findViewById(R.id.sideOfCoin)
        flipText.text = coinFlipResult
    }

}

class Dice(private val numSides: Int) {

    fun roll(): Int {
        return (1..numSides).random()
    }

}

class Coin() {
    fun flip() : String {
        val sides = arrayOf("head", "tail")
        return sides.random()
    }
}