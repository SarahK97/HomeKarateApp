package fhnw.ws6c.HomeKarate.data

import android.content.Context
import kotlinx.serialization.json.Json

class DataService(private val context: Context) {

    private val json = Json { ignoreUnknownKeys = true }
    fun getTechniken(): List<KarateTechnik> {
        val jsonFileString = loadJsonFromAssets("techniken.json")
        return parseJsonToKarateTechnik(jsonFileString)
    }

    private fun loadJsonFromAssets(fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    private fun parseJsonToKarateTechnik(jsonString: String): List<KarateTechnik> {
        return json.decodeFromString(jsonString)
    }
}
