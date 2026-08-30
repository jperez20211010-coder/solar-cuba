package com.solarcuba.engineering

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Colores Caribe Técnico
val CaribbeanDeepBlue = Color(0xFF0A4D68)
val SolarTurquoise = Color(0xFF05BFDB)
val CubanSunYellow = Color(0xFFFFD23F)
val PalmGreen = Color(0xFF00A878)
val SandWhite = Color(0xFFFAF8F5)
val CoralRed = Color(0xFFE63946)
val EngineeringGray = Color(0xFF2B2D42)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SolarCubaApp()
        }
    }
}

@Composable
fun SolarCubaApp() {
    var currentScreen by remember { mutableStateOf("onboarding") }
    
    when (currentScreen) {
        "onboarding" -> OnboardingScreen(
            onStart = { currentScreen = "dashboard" }
        )
        "dashboard" -> DashboardScreen(
            onNavigate = { screen -> currentScreen = screen }
        )
        "cargas" -> CargasScreen(onBack = { currentScreen = "dashboard" })
        "comisionamiento" -> ComisionamientoScreen(onBack = { currentScreen = "dashboard" })
    }
}

// ============================================================
// PANTALLA 1: ONBOARDING
// ============================================================
@Composable
fun OnboardingScreen(onStart: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SandWhite)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Sol animado simple
        Text(
            text = "☀️",
            fontSize = 80.sp
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "Solar-Cuba",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = CaribbeanDeepBlue,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Ingeniería fotovoltaica para Cuba",
            fontSize = 18.sp,
            color = EngineeringGray,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Características principales
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                FeatureRow(Icons.Default.Bolt, "Dimensionamiento automático")
                FeatureRow(Icons.Default.Shield, "Verificación de seguridad")
                FeatureRow(Icons.Default.Description, "Diagramas unifilares IEC")
                FeatureRow(Icons.Default.Draw, "Comisionamiento con firma digital")
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(
            onClick = onStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = CaribbeanDeepBlue)
        ) {
            Text("Comenzar", fontSize = 18.sp)
        }
    }
}

@Composable
fun FeatureRow(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = SolarTurquoise,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = text,
            fontSize = 16.sp,
            color = EngineeringGray
        )
    }
}

// ============================================================
// PANTALLA 2: DASHBOARD
// ============================================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(onNavigate: (String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Solar-Cuba") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = CaribbeanDeepBlue,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Inicio") },
                    selected = true,
                    onClick = { }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Bolt, contentDescription = null) },
                    label = { Text("Cargas") },
                    selected = false,
                    onClick = { onNavigate("cargas") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.CheckCircle, contentDescription = null) },
                    label = { Text("Comisionar") },
                    selected = false,
                    onClick = { onNavigate("comisionamiento") }
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(SandWhite)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Tarjeta de bienvenida
            Card(
                colors = CardDefaults.cardColors(containerColor = CaribbeanDeepBlue)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Bienvenido 👋",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Diseña, dimensiona y certifica sistemas fotovoltaicos para Cuba.",
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }
            
            // Tarjeta de nuevo proyecto
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "📋 Nuevo Proyecto",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = CaribbeanDeepBlue
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Crea un proyecto para comenzar el dimensionamiento.",
                        color = EngineeringGray
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = SolarTurquoise)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Crear Proyecto")
                    }
                }
            }
            
            // Estadísticas
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    value = "0",
                    label = "Proyectos",
                    color = SolarTurquoise,
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    value = "0",
                    label = "Certificados",
                    color = PalmGreen,
                    modifier = Modifier.weight(1f)
                )
            }
            
            // Información de la app
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = CubanSunYellow.copy(alpha = 0.2f)
                )
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        Icons.Default.Info,
                        contentDescription = null,
                        tint = CaribbeanDeepBlue
                    )
                    Column {
                        Text(
                            text = "Versión 1.0.0-alpha",
                            fontWeight = FontWeight.Bold,
                            color = CaribbeanDeepBlue
                        )
                        Text(
                            text = "APK de prueba generada con GitHub Actions. " +
                                  "Funcionalidad completa en próximas versiones.",
                            fontSize = 14.sp,
                            color = EngineeringGray
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StatCard(value: String, label: String, color: Color, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = color
            )
            Text(
                text = label,
                color = EngineeringGray
            )
        }
    }
}

// ============================================================
// PANTALLA 3: CARGAS
// ============================================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CargasScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Seleccionar Cargas") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = CaribbeanDeepBlue,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(SandWhite)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Equipos comunes en Cuba",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = CaribbeanDeepBlue
            )
            
            // Lista de cargas de ejemplo
            LoadItem("❄️", "Nevera Haier", "150W • Crítica")
            LoadItem("🌀", "Ventilador", "60W • Importante")
            LoadItem("📺", "TV LED", "80W • Importante")
            LoadItem("💡", "Iluminación LED", "10W • Importante")
            LoadItem("❄️", "Split 12000 BTU", "1280W • Diferible")
        }
    }
}

@Composable
fun LoadItem(icon: String, name: String, details: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = icon, fontSize = 32.sp)
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    color = EngineeringGray
                )
                Text(
                    text = details,
                    fontSize = 14.sp,
                    color = EngineeringGray.copy(alpha = 0.7f)
                )
            }
            Checkbox(
                checked = false,
                onCheckedChange = { }
            )
        }
    }
}

// ============================================================
// PANTALLA 4: COMISIONAMIENTO
// ============================================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComisionamientoScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Comisionamiento") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = CaribbeanDeepBlue,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(SandWhite)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Checklist de Comisionamiento",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = CaribbeanDeepBlue
            )
            
            // Secciones del checklist
            ChecklistSection("👁️", "Inspección Visual", "10 verificaciones")
            ChecklistSection("⚡", "Cableado DC", "8 verificaciones")
            ChecklistSection("🛡️", "Protecciones DC", "5 verificaciones")
            ChecklistSection("🔌", "Inversor", "11 verificaciones")
            ChecklistSection("🔋", "Banco de Baterías", "10 verificaciones")
            ChecklistSection("〰️", "Cableado AC", "7 verificaciones")
            ChecklistSection("⏚", "Puesta a Tierra", "8 verificaciones")
            ChecklistSection("✅", "Pruebas Funcionales", "8 verificaciones")
            
            // Botón de firma (deshabilitado en MVP)
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
                colors = ButtonDefaults.buttonColors(
                    containerColor = PalmGreen
                )
            ) {
                Icon(Icons.Default.Draw, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Firmar Comisionamiento")
            }
        }
    }
}

@Composable
fun ChecklistSection(icon: String, title: String, details: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = icon, fontSize = 28.sp)
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    color = EngineeringGray
                )
                Text(
                    text = details,
                    fontSize = 14.sp,
                    color = EngineeringGray.copy(alpha = 0.7f)
                )
            }
            Icon(
                Icons.Default.ChevronRight,
                contentDescription = null,
                tint = CaribbeanDeepBlue
            )
        }
    }
}
