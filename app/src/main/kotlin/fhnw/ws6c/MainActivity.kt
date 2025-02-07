package fhnw.ws6c

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import fhnw.ws6c.HomeKarate.HomeKarateApp


class MainActivity : ComponentActivity() {
    private lateinit var app : EmobaApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app = HomeKarateApp

        app.initialize(activity = this)

        setContent {
            app.CreateUI()
        }
    }

    /**
     * Eine der Activity-LiveCycle-Methoden als Beispiel, falls da weiteres benötigt wird.
     */
    override fun onStop() {
        super.onStop()
        app.onStop(activity = this)
    }
}