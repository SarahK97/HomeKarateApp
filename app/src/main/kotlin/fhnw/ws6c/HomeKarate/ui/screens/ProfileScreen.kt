import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fhnw.ws6c.HomeKarate.model.HomeKarateModel
import fhnw.ws6c.HomeKarate.ui.getKyuColor
import fhnw.ws6c.HomeKarate.ui.hkdarkColorScheme
import fhnw.ws6c.HomeKarate.ui.hklightColorScheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    val currentColorScheme = if (HomeKarateModel.isDarkTheme) hkdarkColorScheme else hklightColorScheme
    val isDarkTheme = HomeKarateModel.isDarkTheme

    MaterialTheme(colorScheme = currentColorScheme) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = "ws6C Test",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(top = 12.dp, start = 8.dp)
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                )
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Email",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = "test@fhnw.ch",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 4.dp),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Dojo",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = "Karate Do Brugg",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 4.dp),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Gürtel",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Surface(
                        shape = CircleShape,
                        color = getKyuColor(kyu = "4. Kyu"),
                        modifier = Modifier.size(80.dp)
                    ) {
                    }
                    Text(
                        text = "4. Kyu",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 4.dp),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Design",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Switch(
                    checked = isDarkTheme,
                    onCheckedChange = { HomeKarateModel.toggleTheme() }
                )
            }
        }
    }
}
