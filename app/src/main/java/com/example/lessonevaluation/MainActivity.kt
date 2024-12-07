package com.example.lessonevaluation

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textET: EditText
    private lateinit var randomNumberBTN: Button
    private var isRandomButtonClick: Boolean = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textET = findViewById(R.id.textET)
        registerForContextMenu(textET)

        randomNumberBTN = findViewById(R.id.randomNumberBTN)
        randomNumberBTN.setOnClickListener {
            val getRandom = (1..50).random()
            textET.setText(getRandom.toString())
            isRandomButtonClick = true
        }

        textET.setOnClickListener {
            textET.text.clear()
            textET.setBackgroundColor(getColor(R.color.white))
            isRandomButtonClick = false
        }

        textET.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isRandomButtonClick = false
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_change_color -> {
                val inputText = textET.text.toString()
                if (!isRandomButtonClick) {
                    when (inputText) {
                        "1" -> textET.setBackgroundColor(getColor(R.color.orange))
                        "2" -> textET.setBackgroundColor(getColor(R.color.yellow))
                        "3" -> textET.setBackgroundColor(getColor(R.color.green))
                        "4" -> textET.setBackgroundColor(getColor(R.color.blue))
                        "5" -> textET.setBackgroundColor(getColor(R.color.red))
                        else -> {
                            textET.setBackgroundColor(getColor(R.color.white))
                            Toast.makeText(this, getString(R.string.no_evaluation_text), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    val randomNumber = inputText.toIntOrNull()
                    if (randomNumber != null) {
                        when (randomNumber) {
                            in 1..10 -> textET.setBackgroundColor(getColor(R.color.red))
                            in 11..20 -> textET.setBackgroundColor(getColor(R.color.orange))
                            in 21..30 -> textET.setBackgroundColor(getColor(R.color.yellow))
                            in 31..40 -> textET.setBackgroundColor(getColor(R.color.green))
                            in 41..50 -> textET.setBackgroundColor(getColor(R.color.blue))
                            else -> {
                                textET.setBackgroundColor(getColor(R.color.white))
                                Toast.makeText(this, getString(R.string.no_evaluation_text), Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
            R.id.menu_exit -> {
                finish()
                Toast.makeText(this, getString(R.string.application_shutdown_text), Toast.LENGTH_SHORT).show()
            }
            else -> return super.onContextItemSelected(item)
        }
        return true
    }
}