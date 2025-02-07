package fhnw.ws6c.HomeKarate.data

import kotlinx.serialization.Serializable

@Serializable
data class KarateTechnik(
    val name: String,
    val kategorie: Kategorie,
    val kyu: Kyu,
    val grundschule: Grundschule? = null,
    val kata: Kata? = null,
    val kumite: Kumite? = null,
    val kihonKumite: KihonKumite? = null,
    val youtubeUrl: String? = null,
    val steps: List<TechnikSteps>? = null
)
