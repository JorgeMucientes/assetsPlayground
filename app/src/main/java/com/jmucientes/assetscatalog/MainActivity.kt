package com.jmucientes.assetscatalog

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.jmucientes.assetscatalog.Theme.DARK
import com.jmucientes.assetscatalog.Theme.DEFAULT

enum class Theme {
    DEFAULT,
    DARK
}

var selectedTheme = DEFAULT

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setCustomTheme()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Theme ${selectedTheme.toString().toLowerCase()}"

        val button = findViewById<Button>(R.id.change_theme_button)
        button.setOnClickListener { changeTheme() }
    }

    private fun setCustomTheme() {
        when (selectedTheme) {
            DEFAULT -> setTheme(R.style.AppTheme)
            DARK -> setTheme(R.style.AppThemeDark)
        }
    }

    private fun changeTheme() {
        invertSelectedTheme()
        recreate()
    }

    private fun invertSelectedTheme() {
        selectedTheme = when (selectedTheme) {
            DEFAULT -> DARK
            DARK -> DEFAULT
        }
    }
}