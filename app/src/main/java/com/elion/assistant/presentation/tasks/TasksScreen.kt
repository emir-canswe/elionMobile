package com.elion.assistant.presentation.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.elion.assistant.presentation.components.TaskCard
import com.elion.assistant.ui.theme.*
import java.time.LocalDate
import com.elion.assistant.domain.model.Task
import com.elion.assistant.domain.model.Priority

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(
    onNavigateBack: () -> Unit,
    viewModel: TasksViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Görevler", style = AppTypography.headlineMedium) },
                actions = {
                    IconButton(onClick = { showAddDialog = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Yeni Görev", tint = Accent)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Primary)
            )
        },
        containerColor = Primary
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            
            // Premium Tab Row
            ScrollableTabRow(
                selectedTabIndex = uiState.filter.ordinal,
                containerColor = Primary,
                contentColor = Accent,
                edgePadding = 20.dp,
                divider = { HorizontalDivider(color = BorderColor) }
            ) {
                TaskFilter.entries.forEachIndexed { index, filter ->
                    val filterName = when (filter.name) {
                        "TODAY" -> "Bugün"
                        "WEEK" -> "Bu Hafta"
                        "ALL" -> "Tümü"
                        "COMPLETED" -> "Bitenler"
                        else -> filter.name
                    }
                    
                    Tab(
                        selected = uiState.filter == filter,
                        onClick = { viewModel.setFilter(filter) },
                        text = { 
                            Text(
                                text = filterName, 
                                style = AppTypography.labelMedium,
                                color = if (uiState.filter == filter) Accent else TextSecondary
                            ) 
                        }
                    )
                }
            }

            if (uiState.tasks.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize().padding(bottom = 80.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("📭", style = AppTypography.displayLarge)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            "Bu kategori için görev bulunmuyor.", 
                            style = AppTypography.bodyMedium, 
                            color = TextSecondary
                        )
                    }
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(top = 20.dp, bottom = 120.dp, start = 20.dp, end = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(uiState.tasks, key = { it.id }) { task ->
                        TaskCard(
                            task = task,
                            onCompleteToggle = { viewModel.completeTask(task) }
                        )
                    }
                }
            }
        }
        
        if (showAddDialog) {
            var taskTitle by remember { mutableStateOf("") }
            var selectedPriority by remember { mutableStateOf(Priority.NORMAL) }
            var selectedDayOffset by remember { mutableStateOf(0) } // 0 = Bugün, 1 = Yarın, 3 = 3 Gün Sonra

            AlertDialog(
                onDismissRequest = { showAddDialog = false },
                containerColor = Surface,
                titleContentColor = TextPrimary,
                textContentColor = TextSecondary,
                title = { Text("Yeni Görev Oluştur", style = AppTypography.titleLarge) },
                text = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        OutlinedTextField(
                            value = taskTitle,
                            onValueChange = { taskTitle = it },
                            label = { Text("Görev Başlığı", color = TextSecondary) },
                            textStyle = AppTypography.bodyLarge,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Accent,
                                unfocusedBorderColor = BorderColor,
                                focusedLabelColor = Accent,
                                cursorColor = Accent,
                                focusedTextColor = TextPrimary,
                                unfocusedTextColor = TextPrimary
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Text("Öncelik", style = AppTypography.labelMedium, color = TextSecondary)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Priority.entries.forEach { priority ->
                                val isSelected = selectedPriority == priority
                                val chipColor = when (priority) {
                                    Priority.LOW -> PriorityLow
                                    Priority.NORMAL -> PriorityNormal
                                    Priority.HIGH -> PriorityHigh
                                }
                                Box(
                                    modifier = Modifier
                                        .weight(1.5f)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (isSelected) chipColor else Color(0xFF222233))
                                        .clickable { selectedPriority = priority }
                                        .padding(vertical = 8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = when(priority) {
                                            Priority.LOW -> "Düşük"
                                            Priority.NORMAL -> "Normal"
                                            Priority.HIGH -> "Yüksek"
                                        },
                                        style = AppTypography.labelMedium,
                                        color = if (isSelected) Color.White else TextSecondary
                                    )
                                }
                            }
                        }

                        Text("Tarih", style = AppTypography.labelMedium, color = TextSecondary)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOf(0 to "Bugün", 1 to "Yarın", 3 to "3 Gün Sonra").forEach { (offset, label) ->
                                val isSelected = selectedDayOffset == offset
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (isSelected) Accent else Color(0xFF222233))
                                        .clickable { selectedDayOffset = offset }
                                        .padding(vertical = 8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = label,
                                        style = AppTypography.labelMedium,
                                        color = if (isSelected) Color.White else TextSecondary
                                    )
                                }
                            }
                        }
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (taskTitle.isNotBlank()) {
                                viewModel.addTask(
                                    Task(
                                        title = taskTitle,
                                        priority = selectedPriority,
                                        dueDate = LocalDate.now().plusDays(selectedDayOffset.toLong())
                                    )
                                )
                                showAddDialog = false
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Accent),
                        enabled = taskTitle.isNotBlank()
                    ) {
                        Text("Ekle", color = Color.White, style = AppTypography.labelMedium)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showAddDialog = false }) {
                        Text("İptal", color = TextSecondary, style = AppTypography.labelMedium)
                    }
                }
            )
        }
    }
}
