package fhnw.ws6c.HomeKarate

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import fhnw.ws6c.EmobaApp
import fhnw.ws6c.HomeKarate.model.HomeKarateModel
import fhnw.ws6c.HomeKarate.ui.AppUI

object HomeKarateApp : EmobaApp {
    override fun initialize(activity: ComponentActivity) {
        HomeKarateModel.loadData(activity)
    }

    @Composable
    override fun CreateUI() {
        AppUI(HomeKarateModel)
    }
}
