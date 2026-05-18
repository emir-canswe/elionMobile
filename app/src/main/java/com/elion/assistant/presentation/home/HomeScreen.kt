package com.elion.assistant.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
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
    var showQuickAddDialog by remember { mutableStateOf(false) }

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
                DailyAIQuoteCard()
            }

            item {
                ProgressRow(uiState.completedCount, uiState.totalCount, uiState.completionPercentage)
            }

            item {
                AiBatteryCard(uiState.completionPercentage)
            }

            item {
                QuickActionsGrid(
                    onNavigateToVoice = onNavigateToVoice,
                    onNavigateToTasks = onNavigateToTasks,
                    onAddQuickTask = { showQuickAddDialog = true }
                )
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

    if (showQuickAddDialog) {
        var taskTitle by remember { mutableStateOf("") }
        var selectedCategory by remember { mutableStateOf(uiState.categories.firstOrNull()) }

        AlertDialog(
            onDismissRequest = { showQuickAddDialog = false },
            title = { Text("Hızlı Görev Ekle", style = AppTypography.titleLarge, color = TextPrimary) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedTextField(
                        value = taskTitle,
                        onValueChange = { taskTitle = it },
                        label = { Text("Görev Başlığı") },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Surface,
                            unfocusedContainerColor = Surface,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (uiState.categories.isNotEmpty()) {
                        Text("Kategori Seç", style = AppTypography.labelSmall, color = TextSecondary)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            uiState.categories.forEach { category ->
                                val isSelected = selectedCategory?.id == category.id
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(if (isSelected) Color(category.colorHex.replace("#", "FF").toLong(16).toInt()) else Color(0xFF222222))
                                        .clickable { selectedCategory = category }
                                        .padding(horizontal = 12.dp, vertical = 6.dp)
                                ) {
                                    Text(
                                        text = category.name,
                                        style = AppTypography.labelSmall,
                                        color = if (isSelected) Color.White else TextSecondary
                                    )
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (taskTitle.isNotBlank()) {
                            viewModel.addQuickTask(taskTitle, selectedCategory?.id)
                            showQuickAddDialog = false
                        }
                    }
                ) {
                    Text("Ekle", color = Accent)
                }
            },
            dismissButton = {
                TextButton(onClick = { showQuickAddDialog = false }) {
                    Text("İptal", color = TextSecondary)
                }
            },
            containerColor = Surface
        )
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

@Composable
fun DailyAIQuoteCard() {
    val quotes = remember {
        listOf(
            "Kahve hazırsa başlayalım kral, yoksa ben senin yerine ertelemeye devam ederim. ☕",
            "Bugün yapacağın her görev, gelecekteki 'tembel sen' için büyük bir iyiliktir! 😉",
            "Biliyor musun? Bugün hedeflerini tamamlaman için harika bir gün. Tabii üşenmezsen... 🚀",
            "Başarı, bugün yapman gerekenleri yarına bırakmamakla başlar. Ama yine de sen bilirsin. 🤷",
            "Telefonu yavaşça yere bırak ve listedeki ilk göreve dokun. Sana inanıyorum! (ciddiyim) 👀",
            "Plan yapmak harika bir şeydir, ancak onları uygulamak daha da harikadır. Hadi göster kendini! 💪",
            "Asistanın bugün enerjik! Senden de aynı performansı bekliyorum, yoksa akşam raporu sert olur! 😈"
        )
    }
    val randomQuote = remember { quotes.random() }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                brush = androidx.compose.ui.graphics.Brush.linearGradient(
                    colors = listOf(Color(0xFF2A1C5C), Color(0xFF161033))
                )
            )
            .border(1.dp, Color(0xFF4A3494), RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("🧠 Günün Asistan Sözü", style = AppTypography.labelMedium, color = Accent)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "\"$randomQuote\"",
                style = AppTypography.bodyMedium,
                color = TextPrimary,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
            )
        }
    }
}

@Composable
fun AiBatteryCard(percentage: Int) {
    val statusText = when {
        percentage == 0 -> "Daha motoru çalıştırmadık... Asistanın esniyor. 😴"
        percentage <= 30 -> "Isınıyoruz, ama hâlâ uykulu gibisin. Biraz gayret! ☕"
        percentage <= 70 -> "Fena değil! Asistanın şu an senden gurur duyuyor. ⚡"
        percentage < 100 -> "Neredeyse kusursuz bir gün! Az kaldı şampiyon. 🔥"
        else -> "Efsanevi gün! Batarya %100 dolu, şimdi git ve dinlen! 👑"
    }

    val batteryColor = when {
        percentage <= 20 -> PriorityHigh
        percentage <= 60 -> Color(0xFFFFB000)
        else -> Color(0xFF2ECC71)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Surface)
            .border(1.dp, BorderColor, RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("ASİSTAN ENERJİSİ", style = AppTypography.labelMedium, color = TextSecondary)
            Text("%$percentage", style = AppTypography.titleMedium, color = batteryColor, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(10.dp))
        
        // Battery visual
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF222222))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(fraction = percentage / 100f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(batteryColor)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(statusText, style = AppTypography.bodySmall, color = TextPrimary)
    }
}

@Composable
fun QuickActionsGrid(
    onNavigateToVoice: () -> Unit,
    onNavigateToTasks: () -> Unit,
    onAddQuickTask: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
        Text("HIZLI EYLEMLER", style = AppTypography.labelMedium, color = TextSecondary, modifier = Modifier.padding(bottom = 8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Action 1: Voice Command
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Surface)
                    .border(1.dp, BorderColor, RoundedCornerShape(16.dp))
                    .clickable { onNavigateToVoice() }
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("🎙️", style = AppTypography.titleLarge)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Konuş", style = AppTypography.labelMedium, color = TextPrimary)
                }
            }

            // Action 2: Go to Tasks
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Surface)
                    .border(1.dp, BorderColor, RoundedCornerShape(16.dp))
                    .clickable { onNavigateToTasks() }
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("📅", style = AppTypography.titleLarge)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Görevler", style = AppTypography.labelMedium, color = TextPrimary)
                }
            }

            // Action 3: Quick Add Task
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF1E1A3C))
                    .border(1.dp, Color(0xFF3D3270), RoundedCornerShape(16.dp))
                    .clickable { onAddQuickTask() }
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("⚡", style = AppTypography.titleLarge)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Hızlı Ekle", style = AppTypography.labelMedium, color = Accent)
                }
            }
        }
    }
}
