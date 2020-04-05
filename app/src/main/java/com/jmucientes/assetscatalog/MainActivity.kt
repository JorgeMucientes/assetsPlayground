package com.jmucientes.assetscatalog

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.content.res.ResourcesCompat
import com.jmucientes.assetscatalog.Theme.DARK
import com.jmucientes.assetscatalog.Theme.DEFAULT
import com.jmucientes.assetscatalog.utils.asSequence

enum class Theme {
    DEFAULT,
    DARK
}

val assetsCatalog = arrayOf(
    R.drawable.battery_charging_full,
    R.drawable.book,
    R.drawable.cloud_download,
    R.drawable.face,
    R.drawable.insert_chart_outlined,
    R.drawable.perm_media,
    R.drawable.subway,
    R.drawable.event,
    R.drawable.important_devices
)

var selectedTheme = DEFAULT

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setCustomTheme()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Theme ${selectedTheme.toString().toLowerCase()}"

        setChangeButtonTheme()
        addIconsClickListeners()
    }

    private fun setChangeButtonTheme() {
        val button = findViewById<Button>(R.id.change_theme_button)
        button.setOnClickListener { changeTheme() }
    }

    private fun addIconsClickListeners() {
        val iconsContainer = findViewById<ViewGroup>(R.id.icons_container)
        iconsContainer.asSequence()
            .filter { it is ImageView }
            .forEachIndexed { index, imageView ->
                (imageView as ImageView).setImageDrawable(
                    ResourcesCompat.getDrawable(resources, assetsCatalog[index], theme)
                )
                imageView.setOnClickListener {
                    changeSingleIconTheme(imageView, assetsCatalog[index])
                }
            }
    }

    private fun changeSingleIconTheme(imageView: ImageView, @DrawableRes drawableId: Int) {
        val wrapper = ContextThemeWrapper(this, R.style.IconHighlightedTheme)
        val drawable =
            ResourcesCompat.getDrawable(resources, drawableId, wrapper.theme)
        imageView.setImageDrawable(drawable)
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