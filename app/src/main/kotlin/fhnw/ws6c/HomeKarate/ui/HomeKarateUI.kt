package fhnw.ws6c.HomeKarate.ui

import GrundschuleDetailScreen
import HomeScreen
import KataDetailScreen
import KihonKumiteDetailScreen
import KumiteDetailScreen
import ProfileScreen
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import fhnw.ws6c.HomeKarate.data.KarateTechnik
import fhnw.ws6c.HomeKarate.data.Kategorie
import fhnw.ws6c.HomeKarate.model.HomeKarateModel
import fhnw.ws6c.HomeKarate.model.Screen
import fhnw.ws6c.HomeKarate.ui.screens.ErgebnisScreen
import fhnw.ws6c.HomeKarate.ui.screens.FavsScreen
import fhnw.ws6c.HomeKarate.ui.screens.GrundschuleScreen
import fhnw.ws6c.HomeKarate.ui.screens.KataScreen
import fhnw.ws6c.HomeKarate.ui.screens.KihonKumiteScreen
import fhnw.ws6c.HomeKarate.ui.screens.KumiteScreen
import fhnw.ws6c.HomeKarate.ui.screens.LoginScreen
import fhnw.ws6c.HomeKarate.ui.screens.SearchScreen
import fhnw.ws6c.HomeKarate.ui.screens.SignUpScreen
import fhnw.ws6c.R


@Composable
fun AppUI(homeKarateModel: HomeKarateModel) {
    val backgroundBrush = if (homeKarateModel.isDarkTheme) DarkGradient else LightGradient

            val tabs = listOf(
                Screen.HOME to R.drawable.hk,
                Screen.SEARCH to R.drawable.search,
                Screen.FAVS to R.drawable.save,
                Screen.PROFILE to R.drawable.profile
            )
            var tabIndex by remember { mutableStateOf(0) }
            var currentScreen by remember { mutableStateOf(Screen.SIGNUP) }
            var selectedTechnik by remember { mutableStateOf<KarateTechnik?>(null) }
    MaterialTheme (
        typography = CustomTypography,
        colorScheme = if (homeKarateModel.isDarkTheme) hkdarkColorScheme else hklightColorScheme
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundBrush)
        )
        {
            Crossfade(targetState = currentScreen, modifier = Modifier.weight(1f, fill = true)) { screen ->
                when (screen) {
                    Screen.HOME -> { HomeScreen(onNavigate = { newScreen -> currentScreen = newScreen }) }
                    Screen.SEARCH -> {
                        SearchScreen(onNavigate = { newScreen ->
                            currentScreen = newScreen
                        })
                    }
                    Screen.ERGEBNIS -> {
                        ErgebnisScreen(
                            onNavigateBack = { currentScreen = Screen.SEARCH },
                            onNavigateToDetailScreen = { technik ->
                                selectedTechnik = technik
                                currentScreen = when (technik.kategorie) {
                                    Kategorie.KATA -> Screen.KATADETAIL
                                    Kategorie.KUMITE -> Screen.KUMITEDETAIL
                                    Kategorie.GRUNDSCHULE -> Screen.GRUNDSCHULEDETAIL
                                    Kategorie.KIHONKUMITE -> Screen.KIHONKUMITEDETAIL
                                    else -> currentScreen
                                }
                            }
                        )
                    }
                    Screen.FAVS -> {
                        FavsScreen(onNavigate = { newScreen, technik ->
                            currentScreen = newScreen
                            selectedTechnik = technik
                        }) }
                    Screen.PROFILE -> { ProfileScreen() }
                    Screen.LOGIN -> {
                        LoginScreen(onNavigate = { currentScreen = Screen.HOME })
                    }
                    Screen.SIGNUP -> {
                        SignUpScreen(onNavigate = { newScreen -> currentScreen = newScreen })
                    }
                    Screen.KATA -> {
                        KataScreen(
                            onNavigateBack = { currentScreen = Screen.HOME },
                            onNavigate = { newScreen, technik ->
                            currentScreen = newScreen
                            selectedTechnik = technik
                        })
                    }
                    Screen.KATADETAIL -> {
                        selectedTechnik?.let { technik ->
                            KataDetailScreen(
                                technik = technik,
                                onNavigateBack = { currentScreen = Screen.KATA },
                                isFavorite = homeKarateModel.isFavorite(technik),
                                onToggleFavorite = { tech ->
                                    homeKarateModel.toggleFavorite(tech)
                                }
                            )
                        }
                    }
                    Screen.KIHONKUMITE -> {
                        KihonKumiteScreen(
                            onNavigateBack = { currentScreen = Screen.HOME },
                            onNavigate = { newScreen, technik ->
                                currentScreen = newScreen
                                selectedTechnik = technik
                            })
                    }
                    Screen.KIHONKUMITEDETAIL -> {
                        selectedTechnik?.let { technik ->
                            KihonKumiteDetailScreen(
                                technik = technik,
                                onNavigateBack = { currentScreen = Screen.KIHONKUMITE },
                                isFavorite = homeKarateModel.isFavorite(technik),
                                onToggleFavorite = { tech ->
                                    homeKarateModel.toggleFavorite(tech)
                                }
                            )
                        }
                    }
                    Screen.KUMITE -> {
                        KumiteScreen(
                            onNavigateBack = { currentScreen = Screen.HOME },
                            onNavigate = { newScreen, technik ->
                                currentScreen = newScreen
                                selectedTechnik = technik
                            })
                    }
                    Screen.KUMITEDETAIL -> {
                        selectedTechnik?.let { technik ->
                            KumiteDetailScreen(
                                technik = technik,
                                onNavigateBack = { currentScreen = Screen.KUMITE },
                                isFavorite = homeKarateModel.isFavorite(technik),
                                onToggleFavorite = { tech ->
                                    homeKarateModel.toggleFavorite(tech)
                                }
                            )
                        }
                    }
                    Screen.GRUNDSCHULE -> {
                        GrundschuleScreen(
                            onNavigateBack = { currentScreen = Screen.HOME },
                            onNavigate = { newScreen, technik ->
                            currentScreen = newScreen
                            selectedTechnik = technik
                        })
                    }
                    Screen.GRUNDSCHULEDETAIL -> {
                        selectedTechnik?.let { technik ->
                            GrundschuleDetailScreen(
                                technik = technik,
                                onNavigateBack = { currentScreen = Screen.GRUNDSCHULE },
                                isFavorite = homeKarateModel.isFavorite(technik),
                                onToggleFavorite = { tech ->
                                    homeKarateModel.toggleFavorite(tech)
                                }
                            )
                        }
                    }
                }
            }

            TabRow(
                selectedTabIndex = tabIndex,
                containerColor = Color.Transparent,
                modifier = Modifier.height(56.dp),
                indicator = { },
            ) {
                tabs.forEachIndexed { index, (screen, icon) ->
                    val isSelected = tabIndex == index
                    Tab(
                        selected = isSelected,
                        onClick = { tabIndex = index; currentScreen = screen },
                        icon = {
                            if(isSelected){
                                Icon(
                                    painter = painterResource(id = icon),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                            else{
                                Icon(
                                    painter = painterResource(id = icon),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}
