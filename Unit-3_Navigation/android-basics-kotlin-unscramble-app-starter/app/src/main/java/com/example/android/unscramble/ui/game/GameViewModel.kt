package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
    //Mutable data inside the ViewModel should always be private.
//    private var _score = 0
//    private var _currentWordCount = 0
//    private lateinit var _currentScrambledWord: String

    private val _score = MutableLiveData(0)
    private val _currentWordCount = MutableLiveData(0)
    private val _currentScrambledWord = MutableLiveData<String>()

    //to hold a list of words you use in the game, to avoid repetitions.
    private var wordsList: MutableList<String> = mutableListOf()
    //to hold the word the player is trying to unscramble.
    private lateinit var currentWord: String

    //to the UI controller (i.e. GameFragment), this variable will only be read-only
//    val score: Int get() = _score
//    val currentWordCount: Int get() = _currentWordCount
//    val currentScrambledWord: String get() = _currentScrambledWord
    val score: LiveData<Int> get() = _score
    val currentWordCount: LiveData<Int> get() = _currentWordCount
    val currentScrambledWord: LiveData<String> get() = _currentScrambledWord


    //Init is the place for initial setup code needed during the initialization of an object instance.
    //This block of code is run when the object instance is first created and initialized.
    //MUST COME AFTER ALL THE DECLARED VARIABLES - otherwise the app will crash
    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }

    fun nextWord(): Boolean {
        return if (_currentWordCount.value!! < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    /*
    * Updates currentWord and currentScrambledWord with the next word.
    */
    private fun getNextWord() {
        currentWord = allWordsList.random() //grab the word to be guessed from allWordsList
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle() // shuffle the word to be guessed
        while(String(tempWord).equals(currentWord, false)) { //keep shuffling to be scrambled
            tempWord.shuffle()
        }
        if (wordsList.contains(currentWord)) {
            getNextWord()
        } else {
            //_currentScrambledWord = String(tempWord)
            _currentScrambledWord.value = String(tempWord)
            //++_currentWordCount
            _currentWordCount.value = (_currentWordCount.value)?.inc()
            wordsList.add(currentWord)
        }
    }

    private fun increaseScore() {
        //_score += SCORE_INCREASE
        _score.value = (_score.value)?.plus(SCORE_INCREASE)
    }

    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        }
        return false
    }

    fun reinitialiseData() {
        _score.value = 0
        _currentWordCount.value = 0
        wordsList.clear()
        getNextWord()
    }
}