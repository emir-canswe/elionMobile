package com.elion.assistant.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.elion.assistant.domain.model.PostponeAlert
import com.elion.assistant.presentation.components.CircularProgress
import com.elion.assistant.presentation.components.TaskCard
import com.elion.assistant.ui.theme.*
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToTasks: () -> Unit,
    onNavigateToVoice: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
            .statusBarsPadding()
    ) {
        // Custom Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Text("EL", style = AppTypography.headlineMedium, color = TextPrimary)
                Text("ION", style = AppTypography.headlineMedium, color = Accent)
            }
            IconButton(onClick = { /* notification settings */ }) {
                Icon(Icons.Default.Notifications, "Bildirimler", tint = TextPrimary)
            }
        }

        if (uiState.isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Accent)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(bottom = 120.dp)
            ) {
            item {
                BriefingCard(uiState.briefingMessage)
            }

            item {
                ProgressRow(uiState.completedCount, uiState.totalCount, uiState.completionPercentage)
            }

            if (uiState.postponeAlerts.isNotEmpty()) {
                item {
                    PostponeBanner(uiState.postponeAlerts.first())
                }
            }

            item {
                SectionHeader("Bugünkü Görevler", onNavigateToTasks)
            }

            if (uiState.todayTasks.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Surface)
                            .border(1.dp, BorderColor, RoundedCornerShape(16.dp))
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("🎉", style = AppTypography.displayLarge)
                            Spacer(modifier = Modifier.height(12.dp))
                            Text("Bugün için görev yok.", style = AppTypography.bodyMedium, color = TextPrimary)
                            Text("Dinlenmene bak veya yeni görev ekle.", style = AppTypography.labelSmall, color = TextSecondary)
                        }
                    }
                }
            } else {
                items(uiState.todayTasks, key = { it.id }) { task ->
                    val category = uiState.categories.find { it.id == task.categoryId }
                    TaskCard(
                        task = task,
                        onCompleteToggle = { viewModel.toggleTaskCompletion(task) },
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp),
                        categoryName = category?.name,
                        categoryColorHex = category?.colorHex,
                        onDelete = { viewModel.deleteTask(task) }
                    )
                }
            }

            item {
                StreakCard(uiState.currentStreak, uiState.longestStreak)
            }
        }
    }
}
}

@Composable
fun BriefingCard(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(SurfaceVariant)
            .border(1.dp, BorderColor, RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "SABAH BRİFİNGİ",
                style = AppTypography.labelMedium,
                color = Accent,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Text(
                text = message,
                style = AppTypography.bodyMedium,
                color = TextPrimary
            )
        }
    }
}

@Composable
fun ProgressRow(completed: Int, total: Int, percentage: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgress(percentage = percentage)
        Spacer(modifier = Modifier.width(14.dp))
        Column {
            Text(
                text = "Bugün $completed/$total tamamlandı",
                style = AppTypography.titleMedium,
                color = TextPrimary
            )
            Text(
                text = if (total - completed > 0) "${total - completed} görev daha bekliyor" else "Tüm görevler bitti!",
                style = AppTypography.bodySmall,
                color = TextSecondary
            )
        }
    }
}

@Composable
fun PostponeBanner(alert: PostponeAlert) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFF2A0A0A))
            .border(1.dp, Color(0xFF4A1A1A), RoundedCornerShape(14.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = null,
            tint = PriorityHigh,
            modifier = Modifier.size(16.dp).padding(top = 2.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text("Erteleme Dedektörü", style = AppTypography.labelMedium, color = PriorityHigh)
            Spacer(modifier = Modifier.height(2.dp))
            val text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = TextPrimary)) {
                    append("\"${alert.task.title}\" ")
                }
                append(alert.comment)
            }
            Text(text, style = AppTypography.bodySmall, color = Color(0xFFE8A0A0))
        }
    }
}

@Composable
fun StreakCard(current: Int, longest: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 14.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Surface)
            .border(1.dp, BorderColor, RoundedCornerShape(16.dp))
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF2A1F00)),
            contentAlignment = Alignment.Center
        ) {
            Text("🔥", style = AppTypography.titleLarge)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text("mevcut streak", style = AppTypography.labelSmall)
            Text("$current gün", style = AppTypography.titleLarge, color = Color(0xFFFFB000))
        }
        Column(horizontalAlignment = Alignment.End) {
            Text("en uzun", style = AppTypography.labelSmall)
            Text("$longest gün", style = AppTypography.titleMedium, color = TextSecondary)
        }
    }
}

@Composable
fun SectionHeader(title: String, onActionClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, style = AppTypography.titleMedium)
        TextButton(onClick = onActionClick) {
            Text("Tümü", style = AppTypography.labelMedium, color = Accent)
        }
    }
}
