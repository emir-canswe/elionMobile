package com.elion.assistant.presentation.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.elion.assistant.presentation.home.StreakCard
import com.elion.assistant.ui.theme.*

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottom
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStart
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.columnSeries

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsScreen(
    onNavigateBack: () -> Unit,
    viewModel: StatsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("İstatistikler", style = AppTypography.headlineMedium) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Primary)
            )
        },
        containerColor = Primary
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Ateşli Streak Kartı (Ana ekrandan)
            StreakCard(
                current = uiState.currentStreak, 
                longest = uiState.longestStreak
            )
            
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                StatBox(
                    modifier = Modifier.weight(1f),
                    title = "Toplam Görev",
                    value = uiState.totalTasksEver.toString(),
                    color = Accent
                )
                StatBox(
                    modifier = Modifier.weight(1f),
                    title = "Tamamlanan",
                    value = uiState.completedTasksEver.toString(),
                    color = PriorityHigh
                )
            }
            
            // Tamamlama Oranı
            val rate = if (uiState.totalTasksEver > 0) (uiState.completedTasksEver * 100 / uiState.totalTasksEver) else 0
            StatBox(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                title = "Genel Başarı Oranı",
                value = "%$rate",
                color = PriorityNormal,
                isLarge = true
            )

            // Haftalık Performans Grafiği (Vico Charts)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Surface)
                    .border(1.dp, BorderColor, RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Haftalık Performans",
                    style = AppTypography.labelMedium,
                    color = TextSecondary,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                val modelProducer = remember { CartesianChartModelProducer() }
                LaunchedEffect(Unit) {
                    modelProducer.runTransaction {
                        columnSeries {
                            series(3, 5, 2, 6, 4, 7, 5) // Son 7 günün tamamlanan görevleri
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                ) {
                    CartesianChartHost(
                        chart = rememberCartesianChart(
                            rememberColumnCartesianLayer(),
                            startAxis = VerticalAxis.rememberStart(),
                            bottomAxis = HorizontalAxis.rememberBottom()
                        ),
                        modelProducer = modelProducer,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(80.dp)) // Alt menü boşluğu
        }
    }
}

@Composable
fun StatBox(modifier: Modifier = Modifier, title: String, value: String, color: Color, isLarge: Boolean = false) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Surface)
            .border(1.dp, BorderColor, RoundedCornerShape(16.dp))
            .padding(20.dp),
        horizontalAlignment = if (isLarge) Alignment.CenterHorizontally else Alignment.Start
    ) {
        Text(title, style = AppTypography.labelMedium, color = TextSecondary)
        Spacer(modifier = Modifier.height(8.dp))
        Text(value, style = if (isLarge) AppTypography.displayLarge else AppTypography.headlineLarge, color = color)
    }
}
