package fhnw.ws6c.HomeKarate.ui

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val hklightColorScheme = lightColorScheme(
    primary = Color.Black,
    background = Color(0xFFE3E3E3),
    surface = Color(0xFFA9A9A9),
)

val hkdarkColorScheme = darkColorScheme(
    primary = Color.White,
    background = Color(0xFF707070),
    surface = Color(0xFF000000),
)

val LightGradient = Brush.verticalGradient(
    colors = listOf(Color(0xFFE3E3E3), Color(0xFFA9A9A9))
)

val DarkGradient = Brush.verticalGradient(
    colors = listOf(Color(0xFF707070), Color(0xFF000000))
)

val LightDiagonalGradient = Brush.linearGradient(
    colors = listOf(Color(0xFF4E7A00), Color(0xFF0036BE)),
    start = Offset(0f, 0f),
    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
)

val DarkDiagonalGradient = Brush.linearGradient(
    colors = listOf(Color(0xFFF1B719), Color(0xFF610099)),
    start = Offset(0f, 0f),
    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
)

object KyuColors {
    val Weiß = Color.White
    val Gelb = Color(0xFFF1B719)
    val Orange = Color(0xFFE06501)
    val Grün = Color(0xFF4E7A00)
    val Blau = Color(0xFF0036BE)
    val Violett = Color(0xFF610099)
    val Braun = Color(0xFF632E00)
    val Schwarz = Color.Black
}


fun getKyuColor(kyu: String): Color {
    return when(kyu) {
        "9. Kyu" -> KyuColors.Weiß
        "8. Kyu" -> KyuColors.Gelb
        "7. Kyu" -> KyuColors.Orange
        "6. Kyu" -> KyuColors.Grün
        "5. Kyu" -> KyuColors.Blau
        "4. Kyu" -> KyuColors.Violett
        "1.-3. Kyu" -> KyuColors.Braun
        "Dan" -> KyuColors.Schwarz
        else -> Color.Gray
    }
}