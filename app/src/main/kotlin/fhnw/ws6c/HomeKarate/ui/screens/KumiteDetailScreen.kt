import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.ws6c.HomeKarate.data.KarateTechnik
import fhnw.ws6c.HomeKarate.data.TechnikSteps
import fhnw.ws6c.HomeKarate.model.HomeKarateModel
import fhnw.ws6c.HomeKarate.ui.getKyuColor
import fhnw.ws6c.HomeKarate.ui.hkdarkColorScheme
import fhnw.ws6c.HomeKarate.ui.hklightColorScheme
import fhnw.ws6c.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KumiteDetailScreen(technik: KarateTechnik, onNavigateBack: () -> Unit, isFavorite: Boolean, onToggleFavorite: (KarateTechnik) -> Unit){
    val kyuColor = getKyuColor(technik.kyu.kyu)
    val currentColorScheme = if (HomeKarateModel.isDarkTheme) hkdarkColorScheme else hklightColorScheme

    MaterialTheme (
        colorScheme = currentColorScheme
    ) {
        Column  (
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
                            text = technik.name,
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    shape = MaterialTheme.shapes.medium,
                    shadowElevation = 4.dp
                ) {
                    Text(
                        text = technik.kumite?.kumite ?: "Unbekannt",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Surface(
                    color = kyuColor,
                    shape = MaterialTheme.shapes.medium,
                    shadowElevation = 4.dp
                ) {
                    Text(
                        text = technik.kyu.kyu,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { onToggleFavorite(technik) }) {
                    if(isFavorite) {
                        Icon(
                            Icons.Filled.Bookmark,
                            contentDescription = "Favorite",
                            tint = Color.Blue
                        )
                    } else{
                        Icon(
                            Icons.Filled.BookmarkBorder,
                            contentDescription = "Favorite"
                        )
                    }
                }
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(technik.steps ?: emptyList()) { schritt ->
                    StepDetailCard(schritt = schritt, kyuColor = kyuColor)
                }
            }
        }
    }
}

@Composable
fun StepDetailCard(schritt: TechnikSteps, kyuColor: Color) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(300.dp)
    ) {
        val painter =
            painterResource(id = R.drawable.placeholder)
        Image(
            painter = painter,
            contentDescription = "Schritt ${schritt.nummer} Bild",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp))
        )

        Text(
            text = "Schritt ${schritt.nummer}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 8.dp),
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = schritt.text,
            modifier = Modifier.padding(top = 16.dp, start = 8.dp),
            style = MaterialTheme.typography.bodySmall
        )
    }
}