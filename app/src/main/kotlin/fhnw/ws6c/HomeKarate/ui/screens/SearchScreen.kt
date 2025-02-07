package fhnw.ws6c.HomeKarate.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fhnw.ws6c.HomeKarate.data.Kategorie
import fhnw.ws6c.HomeKarate.data.Kyu
import fhnw.ws6c.HomeKarate.data.Grundschule
import fhnw.ws6c.HomeKarate.data.Kata
import fhnw.ws6c.HomeKarate.data.KihonKumite
import fhnw.ws6c.HomeKarate.data.Kumite
import fhnw.ws6c.HomeKarate.model.HomeKarateModel
import fhnw.ws6c.HomeKarate.model.HomeKarateModel.currentScreen
import fhnw.ws6c.HomeKarate.model.Screen
import fhnw.ws6c.HomeKarate.ui.getKyuColor
import fhnw.ws6c.HomeKarate.ui.hkdarkColorScheme
import fhnw.ws6c.HomeKarate.ui.hklightColorScheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(onNavigate: (Screen) -> Unit) {
    var selectedCategory by remember { mutableStateOf<Kategorie?>(null) }
    val selectedKyu = remember { mutableStateListOf<Kyu>() }
    val selectedGrundschule = remember { mutableStateListOf<Grundschule>() }
    val selectedKata = remember { mutableStateListOf<Kata>() }
    val selectedKumite = remember { mutableStateListOf<Kumite>() }
    val selectedKihonKumite = remember { mutableStateListOf<KihonKumite>() }
    var selectedTechnik by remember { mutableStateOf<String?>(null) }
    val selectedKyuColor = remember { mutableStateOf<Color?>(null) }
    val currentColorScheme =
        if (HomeKarateModel.isDarkTheme) hkdarkColorScheme else hklightColorScheme

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
                        text = "Suchen",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(top = 12.dp, start = 8.dp)
                    )

                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                ),
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Kategorie",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 8.dp)
                )
                KategorieButtons(selectedCategory) { category -> selectedCategory = category }
                Spacer(modifier = Modifier.height(16.dp))
                selectedCategory?.let { category ->
                    if (category == Kategorie.GRUNDSCHULE) {
                        Text(
                            text = "Technik",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        GrundschuleButtons(selectedGrundschule)
                    }
                    if (category == Kategorie.KATA) {
                        Text(
                            text = "Technik",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        KataButtons(selectedKata)
                    }
                    if (category == Kategorie.KUMITE) {
                        Text(
                            text = "Technik",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        KumiteButtons(selectedKumite)
                    }
                    if (category == Kategorie.KIHONKUMITE) {
                        Text(
                            text = "Technik",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        KihonKumiteButtons(selectedKihonKumite)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Gurtfarbe",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 8.dp)
                )
                KyuButtons(selectedKyu)
                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 16.dp)
                ) {
                    Button(
                        onClick = {
                            HomeKarateModel.setSelectedFilters(
                                selectedCategory,
                                selectedGrundschule.toList(),
                                selectedKyu.toList()
                            )
                            onNavigate(Screen.ERGEBNIS)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.background
                        ),
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "Ergebnisse anzeigen",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun KategorieButtons(selectedCategory: Kategorie?, onCategorySelected: (Kategorie) -> Unit) {
    FlowRow(
        modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
    ) {
        Kategorie.values().forEach { kategorie ->
            val isSelected = kategorie == selectedCategory
            Button(
                onClick = { onCategorySelected(kategorie) },
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.background
                )
            ) {
                Text(
                    text = kategorie.kategorie,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodySmall
                    )
            }
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GrundschuleButtons(selectedGrundschule: MutableList<Grundschule>) {
    FlowRow(
        modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
    ) {
        Grundschule.values().forEach { grundschule ->
            val isSelected = grundschule in selectedGrundschule
            Button(
                onClick = {
                    if (isSelected) {
                        selectedGrundschule.remove(grundschule)
                    } else {
                        selectedGrundschule.add(grundschule)
                    }
                },
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.background
                )
            ) {
                Text(
                    text = grundschule.grundschule,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun KataButtons(selectedKata: MutableList<Kata>) {
    FlowRow(
        modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
    ) {
        Kata.values().forEach { kata ->
            val isSelected = kata in selectedKata
            Button(
                onClick = {
                    if (isSelected) {
                        selectedKata.remove(kata)
                    } else {
                        selectedKata.add(kata)
                    }
                },
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.background
                )
            ) {
                Text(
                    text = kata.kata,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun KumiteButtons(selectedKumite: MutableList<Kumite>) {
    FlowRow(
        modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
    ){
        Kumite.values().forEach { kumite ->
            val isSelected = kumite in selectedKumite
            Button(
                onClick = {
                    if (isSelected) {
                        selectedKumite.remove(kumite)
                    } else {
                        selectedKumite.add(kumite)
                    }
                },
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.background
                )
            ) {
                Text(
                    text = kumite.kumite,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun KihonKumiteButtons(selectedKihonKumite: MutableList<KihonKumite>) {
    FlowRow(
        modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
    ) {
        KihonKumite.values().forEach { kihonKumite ->
            val isSelected = kihonKumite in selectedKihonKumite
            Button(
                onClick = {
                    if (isSelected) {
                        selectedKihonKumite.remove(kihonKumite)
                    } else {
                        selectedKihonKumite.add(kihonKumite)
                    }
                },
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.background
                )
            ) {
                Text(
                    text = kihonKumite.kihonKumite,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun KyuButtons(selectedKyu: MutableList<Kyu>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(top = 8.dp)
    ) {
        items(Kyu.values()) { kyu ->
            val isSelected = kyu in selectedKyu
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Surface(
                    shape = CircleShape,
                    color = getKyuColor(kyu.kyu),
                    border = if (isSelected) BorderStroke(2.dp, Color.Black) else null,
                    modifier = Modifier
                        .size(80.dp)
                        .clickable {
                            if (isSelected) {
                                selectedKyu.remove(kyu)
                            } else {
                                selectedKyu.add(kyu)
                            }
                        }
                ) {
                }
                Text(
                    text = kyu.kyu,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 4.dp),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}