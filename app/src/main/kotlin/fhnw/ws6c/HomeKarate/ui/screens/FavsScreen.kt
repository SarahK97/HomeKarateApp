package fhnw.ws6c.HomeKarate.ui.screens
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.ws6c.HomeKarate.data.KarateTechnik
import fhnw.ws6c.HomeKarate.model.HomeKarateModel
import fhnw.ws6c.HomeKarate.model.Screen
import fhnw.ws6c.HomeKarate.ui.hkdarkColorScheme
import fhnw.ws6c.HomeKarate.ui.hklightColorScheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavsScreen(onNavigate: (Screen, KarateTechnik) -> Unit) {

    val favoriteTechniks = HomeKarateModel.techniken.filter {
        HomeKarateModel.isFavorite(it)
    }

    val groupedFavorites = favoriteTechniks.groupBy { it.kategorie }
    val currentColorScheme = if (HomeKarateModel.isDarkTheme) hkdarkColorScheme else hklightColorScheme

    MaterialTheme(
        colorScheme = currentColorScheme
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = "Favoriten",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(top = 12.dp, start = 8.dp)
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent),
            )
            LazyColumn {
                groupedFavorites.forEach { (kategorie, technikenListe) ->
                    item {
                        Text(
                            text = kategorie.kategorie,
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp, start = 20.dp, bottom = 8.dp)
                        )
                    }
                    item {
                        LazyRow {
                            items(technikenListe) { technik ->
                                KataCard(technik = technik) {
                                    onNavigate(Screen.KATADETAIL, technik)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
