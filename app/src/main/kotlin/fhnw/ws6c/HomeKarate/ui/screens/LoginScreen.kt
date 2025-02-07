package fhnw.ws6c.HomeKarate.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import fhnw.ws6c.HomeKarate.model.HomeKarateModel
import fhnw.ws6c.HomeKarate.model.HomeKarateModel.currentScreen
import fhnw.ws6c.HomeKarate.model.Screen
import fhnw.ws6c.HomeKarate.ui.hkdarkColorScheme
import fhnw.ws6c.HomeKarate.ui.hklightColorScheme


@OptIn (ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onNavigate: (Screen) -> Unit){

    val currentColorScheme = if (HomeKarateModel.isDarkTheme) hkdarkColorScheme else hklightColorScheme
    val isDarkTheme = HomeKarateModel.isDarkTheme

    MaterialTheme(colorScheme = currentColorScheme){
        Column (modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = {
                    Text(
                        text = "Login",
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
                TextFieldComponent(labelValue = "Email")
                Spacer(modifier = Modifier.height(16.dp))
                PasswortFieldComponent(labelValue = "Passwort")
                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = {onNavigate(Screen.HOME)},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.background
                            ),
                            shape = MaterialTheme.shapes.medium,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            Text(
                                text = "Login",
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}
//
//@Composable
//fun TextFieldComponent(labelValue: String){
//    val textValue = remember {
//        mutableStateOf("")
//    }
//
//    OutlinedTextField(
//        modifier = Modifier.fillMaxWidth(),
//        label = { Text(text = labelValue)},
//        value = textValue.value,
//        onValueChange = {
//            textValue.value = it
//        },
//    )
//}
//
//@Composable
//fun PasswortFieldComponent(labelValue: String){
//    val passwort = remember {
//        mutableStateOf("")
//    }
//
//    val passwortVisible = remember {
//        mutableStateOf(false)
//    }
//
//    OutlinedTextField(
//        modifier = Modifier.fillMaxWidth(),
//        label = { Text(text = labelValue)},
//        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//        value = passwort.value,
//        onValueChange = {
//            passwort.value = it
//        },
//        trailingIcon = {
//            val iconImage = if(passwortVisible.value){
//                Icons.Filled.Visibility
//            } else {
//                Icons.Filled.VisibilityOff
//            }
//
//            IconButton(onClick = { passwortVisible.value = !passwortVisible.value }) {
//                Icon(imageVector = iconImage, contentDescription = null)
//            }
//        }
//    )
//}
