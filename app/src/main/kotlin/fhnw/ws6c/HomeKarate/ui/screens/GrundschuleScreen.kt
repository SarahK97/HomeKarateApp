package fhnw.ws6c.HomeKarate.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.ws6c.HomeKarate.data.KarateTechnik
import fhnw.ws6c.HomeKarate.model.HomeKarateModel
import fhnw.ws6c.HomeKarate.model.Screen
import fhnw.ws6c.HomeKarate.ui.getKyuColor
import fhnw.ws6c.HomeKarate.ui.hkdarkColorScheme
import fhnw.ws6c.HomeKarate.ui.hklightColorScheme
import fhnw.ws6c.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrundschuleScreen(onNavigate: (Screen, KarateTechnik) -> Unit, onNavigateBack: () -> Unit) {
    val techniken = HomeKarateModel.techniken
    val groupedTechniken = techniken.groupBy { it.grundschule }
    val currentColorScheme = if (HomeKarateModel.isDarkTheme) hkdarkColorScheme else hklightColorScheme

    MaterialTheme (
         colorScheme = currentColorScheme
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Grundschule",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(top = 12.dp, start = 8.dp)
                        )
                        Spacer(Modifier.weight(1f))
                        IconButton(onClick = onNavigateBack) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBackIosNew,
                                contentDescription = "Zurück",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                )
            )
            groupedTechniken.forEach { (grundschule, technikenListe) ->
                if (grundschule != null) {
                    Text(
                        text = grundschule.grundschule,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, start = 20.dp, bottom = 8.dp)
                    )
                    LazyRow {
                        items(technikenListe) { technik ->
                            GrundschuleCard(technik = technik) {
                                onNavigate(Screen.GRUNDSCHULEDETAIL, technik)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GrundschuleCard(technik: KarateTechnik, onCardClick: () -> Unit) {
    val context = LocalContext.current
    val imageName = technik.name.lowercase().replace(" ", "_")
    val imageResId = context.resources.getIdentifier(imageName, "drawable", context.packageName)

    val kyuColor = getKyuColor(technik.kyu.kyu)

    Row(
        modifier = Modifier
            .padding(start = 16.dp, bottom = 16.dp)
            .width(140.dp)
            .clickable(onClick = onCardClick)
    ) {
        Box(
            modifier = Modifier
                .width(10.dp)
                .height(110.dp)
                .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                .background(kyuColor)
        )
        Column{
            val painter = if (imageResId != 0) {
                painterResource(id = imageResId)
            } else {
                painterResource(id = R.drawable.placeholder)
            }
            Image(
                painter = painter,
                contentDescription = "${technik.name} Bild",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(110.dp)
                    .width(140.dp)
                    .clip(RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp))
            )
            Text(
                text = technik.name,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
