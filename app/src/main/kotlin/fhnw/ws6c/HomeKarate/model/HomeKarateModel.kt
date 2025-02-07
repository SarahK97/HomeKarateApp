package fhnw.ws6c.HomeKarate.model

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import fhnw.ws6c.HomeKarate.data.DataService
import fhnw.ws6c.HomeKarate.data.Grundschule
import fhnw.ws6c.HomeKarate.data.KarateTechnik
import fhnw.ws6c.HomeKarate.data.Kategorie
import fhnw.ws6c.HomeKarate.data.Kyu

object HomeKarateModel {
    var techniken by mutableStateOf(emptyList<KarateTechnik>())
    val favoriteTechniks = mutableStateListOf<String>()
    var currentScreen by mutableStateOf<Screen>(Screen.HOME)

    var selectedCategory: Kategorie? by mutableStateOf(null)
    var selectedGrundschulen: List<Grundschule> by mutableStateOf(listOf())
    var selectedKyu: List<Kyu> by mutableStateOf(listOf())
    var isDarkTheme by mutableStateOf(false)

    fun toggleTheme() {
        isDarkTheme = !isDarkTheme
    }
    fun setTheme(darkTheme: Boolean) {
        isDarkTheme = darkTheme
    }
    fun navigateToErgebnisScreen() {
        currentScreen = Screen.ERGEBNIS
    }

    fun setSelectedFilters(category: Kategorie?, grundschulen: List<Grundschule>, kyu: List<Kyu>) {
        selectedCategory = category
        selectedGrundschulen = grundschulen
        selectedKyu = kyu
    }
    fun loadData(context: Context) {
        val dataService = DataService(context)
        techniken = dataService.getTechniken()
    }

    fun toggleFavorite(technik: KarateTechnik) {
        if (technik.name in favoriteTechniks) {
            favoriteTechniks.remove(technik.name)
        } else {
            favoriteTechniks.add(technik.name)
        }
    }

    fun isFavorite(technik: KarateTechnik): Boolean {
        return technik.name in favoriteTechniks
    }
}
