package fhnw.ws6c.HomeKarate.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import fhnw.ws6c.HomeKarate.data.*
import fhnw.ws6c.HomeKarate.model.HomeKarateModel
import fhnw.ws6c.HomeKarate.ui.getKyuColor
import fhnw.ws6c.HomeKarate.ui.hkdarkColorScheme
import fhnw.ws6c.HomeKarate.ui.hklightColorScheme
import fhnw.ws6c.R

fun filterTechniken(
    selectedCategory: Kategorie?,
    selectedGrundschulen: List<Grundschule>,
    selectedKyu: List<Kyu>
): List<KarateTechnik> {
    return HomeKarateModel.techniken.filter { technik ->
        (selectedCategory == null || technik.kategorie == selectedCategory) &&
                (selectedGrundschulen.isEmpty() || technik.grundschule in selectedGrundschulen) &&
                (selectedKyu.isEmpty() || technik.kyu in selectedKyu)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErgebnisScreen(onNavigateBack: () -> Unit, onNavigateToDetailScreen: (KarateTechnik) -> Unit) {
    val gefilterteTechniken = filterTechniken(
        HomeKarateModel.selectedCategory,
        HomeKarateModel.selectedGrundschulen,
        HomeKarateModel.selectedKyu
    )
    val currentColorScheme =
        if (HomeKarateModel.isDarkTheme) hkdarkColorScheme else hklightColorScheme


    MaterialTheme(
        colorScheme = currentColorScheme
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
        ){
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Suchergebnisse",
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
            FilterChipGroup(
                selectedCategory = HomeKarateModel.selectedCategory,
                selectedGrundschulen = HomeKarateModel.selectedGrundschulen,
                selectedKyu = HomeKarateModel.selectedKyu
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(gefilterteTechniken) { technik ->
                    TechnikCard(technik) { selectedTechnik ->
                        onNavigateToDetailScreen(selectedTechnik)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterChipGroup(selectedCategory: Kategorie?, selectedGrundschulen: List<Grundschule>, selectedKyu: List<Kyu>) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )  {
        selectedCategory?.let { category ->
            FilterChip(text = category.kategorie, color = MaterialTheme.colorScheme.background)
        }
        selectedGrundschulen.forEach { grundschule ->
            FilterChip(text = grundschule.grundschule, color = MaterialTheme.colorScheme.background)
        }
        selectedKyu.forEach { kyu ->
            FilterChip(text = kyu.kyu, color = getKyuColor(kyu.kyu))
        }
    }

}

@Composable
fun FilterChip(text: String, color: Color) {
    Surface(
        color = color,
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 4.dp
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun TechnikCard(technik: KarateTechnik, onTechnikClicked: (KarateTechnik) -> Unit) {
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            Text(
                text = technik.name,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Start,
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
                    .clickable { onTechnikClicked(technik) }
            )
            {
            val context = LocalContext.current
            val imageResId = context.resources.getIdentifier(technik.name.lowercase().replace(" ", "_"), "drawable", context.packageName)
            val painter = if (imageResId != 0) painterResource(id = imageResId) else painterResource(id = R.drawable.placeholder)

            Image(
                painter = painter,
                contentDescription = "Bild von ${technik.name}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(230.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
            )
        }
    }
}