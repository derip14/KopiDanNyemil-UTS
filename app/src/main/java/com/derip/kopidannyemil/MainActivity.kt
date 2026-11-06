package com.derip.kopidannyemil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.derip.kopidannyemil.ui.theme.KopiDanNyemilTheme

private val CoffeeBrown = Color(0xFF6F4E37)
private val Cream = Color(0xFFFFF8F0)
private val DarkBrown = Color(0xFF3E2723)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KopiDanNyemilTheme {
                var currentScreen by remember {
                    mutableStateOf("welcome")
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Cream
                ) {
                    when (currentScreen) {
                        "welcome" -> WelcomeScreen {
                            currentScreen = "signup"
                        }

                        "signup" -> SignUpScreen(
                            onLogin = {
                                currentScreen = "login"
                            },
                            onRegister = {
                                currentScreen = "register"
                            }
                        )

                        "login" -> LoginScreen(
                            onBack = {
                                currentScreen = "signup"
                            },
                            onSuccess = {
                                currentScreen = "home"
                            }
                        )

                        "register" -> RegisterScreen(
                            onBack = {
                                currentScreen = "signup"
                            },
                            onSuccess = {
                                currentScreen = "home"
                            }
                        )

                        "home" -> HomeScreen(
                            onProfile = {
                                currentScreen = "profile"
                            },
                            onOrder = {
                                currentScreen = "address"
                            }
                        )

                        "profile" -> ProfileScreen(
                            onBack = {
                                currentScreen = "home"
                            }
                        )

                        "address" -> AddressScreen(
                            onBack = {
                                currentScreen = "home"
                            },
                            onConfirm = {
                                currentScreen = "confirmation"
                            }
                        )

                        "confirmation" -> ConfirmationScreen {
                            currentScreen = "home"
                        }
                    }
                }
            }
        }
    }
}

// HALAMAN WELCOME
@Composable
fun WelcomeScreen(onStart: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("☕", fontSize = 90.sp)

        Spacer(Modifier.height(24.dp))

        Text(
            "Kopi dan Nyemil",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = DarkBrown
        )

        Spacer(Modifier.height(12.dp))

        Text(
            "Get a taste of your wish",
            color = CoffeeBrown
        )

        Spacer(Modifier.height(50.dp))

        Button(
            onClick = onStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = CoffeeBrown
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Start Now")
        }
    }
}

// HALAMAN SIGN UP
@Composable
fun SignUpScreen(
    onLogin: () -> Unit,
    onRegister: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Welcome!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = DarkBrown
        )

        Spacer(Modifier.height(12.dp))

        Text(
            "Join us and enjoy your favorite coffee and snacks.",
            color = CoffeeBrown
        )

        Spacer(Modifier.height(35.dp))

        Button(
            onClick = onRegister,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = CoffeeBrown
            )
        ) {
            Text("Register")
        }

        Spacer(Modifier.height(12.dp))

        OutlinedButton(
            onClick = onLogin,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login", color = CoffeeBrown)
        }
    }
}

// HALAMAN LOGIN
@Composable
fun LoginScreen(
    onBack: () -> Unit,
    onSuccess: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Login User",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = DarkBrown
        )

        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        if (message.isNotEmpty()) {
            Text(message, color = Color.Red)
        }

        Button(
            onClick = {
                if (username.isNotBlank() && password.isNotBlank()) {
                    onSuccess()
                } else {
                    message = "Username dan password wajib diisi"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = CoffeeBrown
            )
        ) {
            Text("Login")
        }

        TextButton(onClick = onBack) {
            Text("Kembali")
        }
    }
}

// HALAMAN REGISTER
@Composable
fun RegisterScreen(
    onBack: () -> Unit,
    onSuccess: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Register",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = DarkBrown
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nama Lengkap") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        if (message.isNotEmpty()) {
            Text(message, color = Color.Red)
        }

        Button(
            onClick = {
                if (
                    name.isNotBlank() &&
                    username.isNotBlank() &&
                    password.isNotBlank()
                ) {
                    onSuccess()
                } else {
                    message = "Semua kolom wajib diisi"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = CoffeeBrown
            )
        ) {
            Text("Register")
        }

        TextButton(onClick = onBack) {
            Text("Kembali")
        }
    }
}

// DATA PRODUK & KATEGORI
data class CoffeeProduct(
    val name: String,
    val description: String,
    val price: String,
    val imageUrl: String
)

data class CategoryMenu(
    val name: String,
    val emoji: String
)

// HALAMAN HOME
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onProfile: () -> Unit,
    onOrder: () -> Unit
) {
    var selectedCategory by remember { mutableStateOf("Semua") }
    var selectedBottomNav by remember { mutableStateOf("Home") }

    val categories = listOf(
        CategoryMenu("Semua", "☕"),
        CategoryMenu("Kopi", "🥤"),
        CategoryMenu("Makanan", "🍴"),
        CategoryMenu("Donat", "🍩"),
        CategoryMenu("Roti", "🍞"),
        CategoryMenu("Minuman", "🍹")
    )

    // Data produk sudah ditambahkan Donat, Roti, dan Croissant
    val products = listOf(
        CoffeeProduct("Kopi Susu Gula Aren", "Espresso, susu, gula aren", "Rp 22.000", "https://images.unsplash.com/photo-1572442388796-11668a67e53d?w=500"),
        CoffeeProduct("Cappuccino", "Espresso dan foam susu", "Rp 25.000", "https://images.unsplash.com/photo-1534778101976-62847782c213?w=500"),
        CoffeeProduct("Donat Cokelat", "Donat empuk lapis cokelat", "Rp 12.000", "https://images.unsplash.com/photo-1551024601-bec78aea704b?w=500"),
        CoffeeProduct("Roti Sourdough", "Roti panggang alami", "Rp 35.000", "https://images.unsplash.com/photo-1509440159596-0249088772ff?w=500"),
        CoffeeProduct("Matcha Latte", "Matcha Jepang dan susu", "Rp 27.000", "https://images.unsplash.com/photo-1515823662972-da6a2e4d3002?w=500"),
        CoffeeProduct("Croissant", "Pastry mentega renyah", "Rp 18.000", "https://images.unsplash.com/photo-1549903072-7e6e0bedb7fb?w=500")
    )

    Scaffold(
        containerColor = Cream,
        bottomBar = {
            NavigationBar(
                containerColor = Cream,
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    selected = selectedBottomNav == "Home",
                    onClick = { selectedBottomNav = "Home" },
                    icon = { Text("🏠", fontSize = 20.sp) },
                    label = { Text("Home", color = if (selectedBottomNav == "Home") DarkBrown else Color.Gray) },
                    colors = NavigationBarItemDefaults.colors(indicatorColor = Color(0xFFEFEBE9))
                )
                NavigationBarItem(
                    selected = selectedBottomNav == "Keranjang",
                    onClick = { selectedBottomNav = "Keranjang" },
                    icon = { Text("🛒", fontSize = 20.sp) },
                    label = { Text("Keranjang", color = if (selectedBottomNav == "Keranjang") DarkBrown else Color.Gray) }
                )
                NavigationBarItem(
                    selected = selectedBottomNav == "Pesanan",
                    onClick = { selectedBottomNav = "Pesanan" },
                    icon = { Text("🧾", fontSize = 20.sp) },
                    label = { Text("Pesanan", color = if (selectedBottomNav == "Pesanan") DarkBrown else Color.Gray) }
                )
                NavigationBarItem(
                    selected = selectedBottomNav == "Setting",
                    onClick = { selectedBottomNav = "Setting" },
                    icon = { Text("⚙️", fontSize = 20.sp) },
                    label = { Text("Setting", color = if (selectedBottomNav == "Setting") DarkBrown else Color.Gray) }
                )
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onOrder,
                containerColor = CoffeeBrown,
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("🛒 Order dan Kirim", fontWeight = FontWeight.Bold)
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { Spacer(Modifier.height(8.dp)) }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Halo, Derip!", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = DarkBrown)
                        Text("Mau kopi atau nyemil hari ini?", fontSize = 14.sp, color = DarkBrown)
                    }
                    Button(
                        onClick = onProfile,
                        colors = ButtonDefaults.buttonColors(containerColor = CoffeeBrown),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("👤 Profile")
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = CoffeeBrown)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "Kopi dan\nNyemil",
                                color = Color.White,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 32.sp
                            )
                            Spacer(Modifier.height(8.dp))
                            Text("Get a taste of your wish ♡", color = Color.White, fontSize = 12.sp)
                        }
                        AsyncImage(
                            model = "https://images.unsplash.com/photo-1497935586351-b67a49e012bf?w=500",
                            contentDescription = "Banner Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(90.dp)
                        )
                    }
                }
            }

            item {
                Text("☕ Kategori", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DarkBrown)
                Spacer(Modifier.height(8.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(categories) { category ->
                        val isSelected = selectedCategory == category.name
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .background(
                                    color = if (isSelected) CoffeeBrown else Color.White,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable { selectedCategory = category.name }
                                .padding(horizontal = 16.dp, vertical = 12.dp)
                        ) {
                            Text(category.emoji, fontSize = 24.sp)
                            Spacer(Modifier.height(4.dp))
                            Text(
                                category.name,
                                fontSize = 12.sp,
                                color = if (isSelected) Color.White else DarkBrown
                            )
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("⭐ Menu Favorit", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DarkBrown)
                    Text("Lihat Semua →", fontSize = 12.sp, color = DarkBrown)
                }
            }

            items(products.chunked(2)) { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    for (product in rowItems) {
                        Card(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                AsyncImage(
                                    model = product.imageUrl,
                                    contentDescription = product.name,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(110.dp)
                                )

                                Spacer(Modifier.height(12.dp))
                                Text(product.name, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = DarkBrown)
                                Text(product.description, fontSize = 11.sp, color = Color.Gray, maxLines = 1)

                                Spacer(Modifier.height(12.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(product.price, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = DarkBrown)
                                    Box(
                                        modifier = Modifier
                                            .size(28.dp)
                                            .background(CoffeeBrown, RoundedCornerShape(8.dp)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text("🛒", fontSize = 12.sp)
                                    }
                                }
                            }
                        }
                    }
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }

            item { Spacer(Modifier.height(80.dp)) }
        }
    }
}

// HALAMAN PROFILE
@Composable
fun ProfileScreen(onBack: () -> Unit) {
    var name by remember {
        mutableStateOf("Nama Pengguna")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Profile Saya",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = DarkBrown
        )

        Spacer(Modifier.height(20.dp))

        Text("Profile Setting")

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nama") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = CoffeeBrown
            )
        ) {
            Text("Save")
        }

        TextButton(onClick = onBack) {
            Text("Kembali ke Home")
        }
    }
}

// HALAMAN ALAMAT PENGIRIMAN
@Composable
fun AddressScreen(
    onBack: () -> Unit,
    onConfirm: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var detail by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Alamat Pengiriman",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = DarkBrown
        )

        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nama Lengkap") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Alamat") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = detail,
            onValueChange = { detail = it },
            label = { Text("Patokan atau detail lengkap") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        if (message.isNotEmpty()) {
            Text(message, color = Color.Red)
        }

        Button(
            onClick = {
                if (name.isNotBlank() && address.isNotBlank()) {
                    onConfirm()
                } else {
                    message = "Nama dan alamat wajib diisi"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = CoffeeBrown
            )
        ) {
            Text("Order dan Kirim")
        }

        TextButton(onClick = onBack) {
            Text("Kembali")
        }
    }
}

// HALAMAN KONFIRMASI PESANAN
@Composable
fun ConfirmationScreen(onHome: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("✅", fontSize = 80.sp)

        Spacer(Modifier.height(20.dp))

        Text(
            "Pesanan Berhasil!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = DarkBrown
        )

        Spacer(Modifier.height(12.dp))

        Text(
            "Terima kasih sudah memesan.\nPesanan kamu sedang diproses.",
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(30.dp))

        Button(
            onClick = onHome,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = CoffeeBrown
            )
        ) {
            Text("Kembali ke Home")
        }
    }
}