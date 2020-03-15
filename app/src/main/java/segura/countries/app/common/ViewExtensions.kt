package segura.countries.app.common

import android.app.Activity
import android.content.res.Configuration
import segura.countries.app.R

 fun Activity.setDarkOrDayMode() {
    val configuration = resources.configuration
    when (configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_NO -> {
            setTheme(R.style.LightTheme)

        }
        Configuration.UI_MODE_NIGHT_YES -> {
            setTheme(R.style.NightTheme)

        }
    }
}