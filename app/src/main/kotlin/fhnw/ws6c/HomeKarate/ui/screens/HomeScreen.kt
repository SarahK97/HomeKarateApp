import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.ws6c.HomeKarate.data.Kategorie
import fhnw.ws6c.HomeKarate.model.HomeKarateModel
import fhnw.ws6c.HomeKarate.model.HomeKarateModel.isDarkTheme
import fhnw.ws6c.HomeKarate.model.Screen
import fhnw.ws6c.HomeKarate.ui.DarkGradient
import fhnw.ws6c.HomeKarate.ui.LightGradient
import fhnw.ws6c.HomeKarate.ui.hkdarkColorScheme
import fhnw.ws6c.HomeKarate.ui.hklightColorScheme
import fhnw.ws6c.R



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigate: (Screen) -> Unit) {
    val techniken = HomeKarateModel.techniken
    val groupedTechniken = techniken.groupBy { it.kategorie }
    val currentColorScheme = if (isDarkTheme) hkdarkColorScheme else hklightColorScheme

    MaterialTheme(
        colorScheme = currentColorScheme
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
            ){
            TopAppBar(
                title = {
                    Text(
                        text = "Home Karate",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(top = 12.dp, start = 8.dp)
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent),
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                groupedTechniken.forEach { (kategorie, _) ->
                    item {
                        Column(
                            modifier = Modifier
                                .padding(bottom = 16.dp)
                        ) {
                            Text(
                                text = kategorie.kategorie,
                                style = MaterialTheme.typography.titleMedium,
                                textAlign = TextAlign.Start,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 8.dp)
                            )
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                                    .clickable {
                                        onNavigate(
                                            when (kategorie) {
                                                Kategorie.KATA -> Screen.KATA
                                                Kategorie.GRUNDSCHULE -> Screen.GRUNDSCHULE
                                                Kategorie.KIHONKUMITE -> Screen.KIHONKUMITE
                                                Kategorie.KUMITE -> Screen.KUMITE
                                            }
                                        )
                                    },
                            )
                            {
                                val imageResId = when (kategorie.kategorie) {
                                    "Grundschule" -> R.drawable.grundschule
                                    "Kata" -> R.drawable.kata
                                    "Kumite" -> R.drawable.kumite
                                    "Kihon Kumite" -> R.drawable.kihonkumite
                                    else -> R.drawable.placeholder
                                }
                                Image(
                                    painter = painterResource(id = imageResId),
                                    contentDescription = "${kategorie.kategorie} Bild",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(230.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}