package fhnw.ws6c.HomeKarate.ui

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import fhnw.ws6c.R

val CustomFontFamily = FontFamily(
    Font(R.font.truelies),
)

val LightGothamFontFamily = FontFamily(
    Font(R.font.gothamlight)
)
val MediumGothamFontFamily = FontFamily(
    Font(R.font.gothammedium)
)

val CustomTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = CustomFontFamily,
        fontSize = 26.sp
    ),
    titleMedium = TextStyle(
        fontFamily = CustomFontFamily,
        fontSize = 20.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = MediumGothamFontFamily,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = LightGothamFontFamily,
        fontSize = 16.sp
    )
)