package com.elion.assistant.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.elion.assistant.presentation.home.HomeScreen
import com.elion.assistant.presentation.settings.SettingsScreen
import com.elion.assistant.presentation.stats.StatsScreen
import com.elion.assistant.presentation.tasks.TasksScreen
import com.elion.assistant.presentation.voice.VoiceCommandScreen
import com.elion.assistant.ui.theme.Accent
import com.elion.assistant.ui.theme.Primary

@Composable
fun ElionNavHost() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            if (currentDestination?.route != "voice") {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF111111))
                        .border(1.dp, Color(0xFF1E1E1E))
                        .navigationBarsPadding()
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val currentRoute = currentDestination?.route
                    val navigate = { route: String ->
                        navController.navigate(route) {
                            popUpTo("home") { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    
                    NavItem("home", "Ana", Icons.Default.Home, currentRoute, navigate)
                    NavItem("tasks", "Görevler", Icons.Default.List, currentRoute, navigate)
                    
                    // Ortalanmış ve aynı hizada Sesli Komut Butonu
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(Accent)
                            .clickable { navController.navigate("voice") },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Mic, 
                            contentDescription = "Sesli Komut", 
                            tint = Color.White, 
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    
                    NavItem("stats", "İstatistik", Icons.Default.BarChart, currentRoute, navigate)
                    NavItem("settings", "Ayarlar", Icons.Default.Settings, currentRoute, navigate)
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen(onNavigateToTasks = { navController.navigate("tasks") }, onNavigateToVoice = { navController.navigate("voice") }) }
            composable("tasks") { TasksScreen(onNavigateBack = { navController.navigateUp() }) }
            composable("stats") { StatsScreen(onNavigateBack = { navController.navigateUp() }) }
            composable("settings") { SettingsScreen(onNavigateBack = { navController.navigateUp() }) }
            composable("voice") { VoiceCommandScreen(onNavigateBack = { navController.navigateUp() }) }
        }
    }
}

@Composable
fun NavItem(route: String, label: String, icon: ImageVector, currentRoute: String?, onNavigate: (String) -> Unit) {
    val selected = currentRoute == route
    val color = if (selected) Accent else Color.Gray
    Column(
        modifier = Modifier
            .clickable { onNavigate(route) }
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon, contentDescription = label, tint = color, modifier = Modifier.size(28.dp))
        Spacer(modifier = Modifier.height(6.dp))
        Text(label, fontSize = 11.sp, color = color)
    }
}
