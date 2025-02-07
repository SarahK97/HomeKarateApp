package fhnw.ws6c.HomeKarate.data

import kotlinx.serialization.Serializable

@Serializable
data class TechnikSteps(
    val nummer: Int,
    val text: String
)
