package com.elion.assistant.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SettingsVoice
import androidx.compose.material.icons.filled.VolumeUp
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
import com.elion.assistant.ui.theme.*

import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    var showNameDialog by remember { mutableStateOf(false) }
    var tempName by remember { mutableStateOf(uiState.assistantName) }

    var showMorningTimeDialog by remember { mutableStateOf(false) }
    var morningHourStr by remember { mutableStateOf(uiState.morningHour.toString()) }
    var morningMinStr by remember { mutableStateOf(uiState.morningMinute.toString().padStart(2, '0')) }

    var showEveningTimeDialog by remember { mutableStateOf(false) }
    var eveningHourStr by remember { mutableStateOf(uiState.eveningHour.toString()) }
    var eveningMinStr by remember { mutableStateOf(uiState.eveningMinute.toString().padStart(2, '0')) }

    var showResetDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ayarlar", style = AppTypography.headlineMedium) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Primary)
            )
        },
        containerColor = Primary
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            
            // Profil Kartı (Asistan Adı - Tıklanabilir)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Surface)
                    .border(1.dp, BorderColor, RoundedCornerShape(20.dp))
                    .clickable { 
                        tempName = uiState.assistantName
                        showNameDialog = true 
                    }
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(Accent.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.SettingsVoice, contentDescription = null, tint = Accent, modifier = Modifier.size(28.dp))
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text("Asistan", style = AppTypography.labelMedium)
                    Text(uiState.assistantName, style = AppTypography.titleLarge, color = Accent)
                }
                Text("Düzenle 📝", style = AppTypography.labelSmall, color = TextSecondary)
            }

            Text("SES & YANIT", style = AppTypography.labelMedium, modifier = Modifier.padding(top = 8.dp, start = 4.dp))

            // Ayar Kartı
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Surface)
                    .border(1.dp, BorderColor, RoundedCornerShape(20.dp))
                    .padding(vertical = 8.dp)
            ) {
                
                // TTS Switch
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(Color(0xFF2A2A3A)), contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.VolumeUp, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("Sesli Yanıt (TTS)", style = AppTypography.bodyLarge)
                            Text("${uiState.assistantName} size sesli cevap versin", style = AppTypography.bodySmall)
                        }
                    }
                    Switch(
                        checked = uiState.isTtsEnabled,
                        onCheckedChange = { viewModel.updateTtsEnabled(it) },
                        colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = Accent)
                    )
                }
                
                HorizontalDivider(color = BorderColor, modifier = Modifier.padding(horizontal = 20.dp))
                
                // Tone Slider
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                ) {
                    Text("Yorum Tonu", style = AppTypography.bodyLarge)
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Slider(
                        value = uiState.commentTone,
                        onValueChange = { viewModel.updateCommentTone(it) },
                        colors = SliderDefaults.colors(
                            thumbColor = Accent,
                            activeTrackColor = Accent,
                            inactiveTrackColor = Color(0xFF2A2A3A)
                        )
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Komik 🤡", style = AppTypography.labelSmall)
                        Text("Sert 🗿", style = AppTypography.labelSmall)
                    }
                }
            }

            Text("BİLDİRİMLER", style = AppTypography.labelMedium, modifier = Modifier.padding(top = 8.dp, start = 4.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Surface)
                    .border(1.dp, BorderColor, RoundedCornerShape(20.dp))
                    .padding(vertical = 8.dp)
            ) {
                // Bildirim Sesi Switch
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(Color(0xFF2A2A3A)), contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.NotificationsActive, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("Bildirim Sesi", style = AppTypography.bodyLarge)
                            Text("Bildirimlerde ses çalınsın", style = AppTypography.bodySmall)
                        }
                    }
                    Switch(
                        checked = uiState.isNotificationSoundEnabled,
                        onCheckedChange = { viewModel.updateNotificationSound(it) },
                        colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = Accent)
                    )
                }
                
                HorizontalDivider(color = BorderColor, modifier = Modifier.padding(horizontal = 20.dp))

                // Sabah Brifing Saati
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { 
                            morningHourStr = uiState.morningHour.toString()
                            morningMinStr = uiState.morningMinute.toString().padStart(2, '0')
                            showMorningTimeDialog = true 
                        }
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(Color(0xFF2A2A3A)), contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.AccessTime, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("Sabah Brifingi Saati", style = AppTypography.bodyLarge)
                            Text("Her sabah günlük görev özeti", style = AppTypography.bodySmall)
                        }
                    }
                    Text(
                        text = "${uiState.morningHour.toString().padStart(2, '0')}:${uiState.morningMinute.toString().padStart(2, '0')}",
                        style = AppTypography.titleMedium,
                        color = Accent
                    )
                }

                HorizontalDivider(color = BorderColor, modifier = Modifier.padding(horizontal = 20.dp))

                // Akşam Analiz Saati
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { 
                            eveningHourStr = uiState.eveningHour.toString()
                            eveningMinStr = uiState.eveningMinute.toString().padStart(2, '0')
                            showEveningTimeDialog = true 
                        }
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(Color(0xFF2A2A3A)), contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.AccessTime, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("Akşam Analizi Saati", style = AppTypography.bodyLarge)
                            Text("Her akşam gün sonu değerlendirmesi", style = AppTypography.bodySmall)
                        }
                    }
                    Text(
                        text = "${uiState.eveningHour.toString().padStart(2, '0')}:${uiState.eveningMinute.toString().padStart(2, '0')}",
                        style = AppTypography.titleMedium,
                        color = Accent
                    )
                }
            }

            Text("GÜVENLİK VE VERİ", style = AppTypography.labelMedium, modifier = Modifier.padding(top = 8.dp, start = 4.dp))

            // Sıfırla Kartı
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Surface)
                    .border(1.dp, BorderColor, RoundedCornerShape(20.dp))
                    .clickable { showResetDialog = true }
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(PriorityHigh.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.DeleteForever, contentDescription = null, tint = PriorityHigh, modifier = Modifier.size(18.dp))
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text("Tüm Verileri Sıfırla", style = AppTypography.bodyLarge, color = PriorityHigh)
                    Text("Görevleri, ayarları ve tüm verileri siler", style = AppTypography.bodySmall)
                }
            }

            Text("HAKKINDA", style = AppTypography.labelMedium, modifier = Modifier.padding(top = 8.dp, start = 4.dp))

            // Hakkında Kartı
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Surface)
                    .border(1.dp, BorderColor, RoundedCornerShape(20.dp))
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF2A2A3A)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Info, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text("ELION Assistant v1.0.0", style = AppTypography.bodyLarge)
                    Text("Tamamen yerel, kişisel asistanınız.", style = AppTypography.bodySmall)
                }
            }

            Spacer(modifier = Modifier.height(100.dp)) // Alt boşluk
        }
    }

    // dialogs
    if (showNameDialog) {
        AlertDialog(
            onDismissRequest = { showNameDialog = false },
            containerColor = Surface,
            titleContentColor = TextPrimary,
            title = { Text("Asistan Adını Değiştir", style = AppTypography.titleLarge) },
            text = {
                OutlinedTextField(
                    value = tempName,
                    onValueChange = { tempName = it },
                    label = { Text("Asistan Adı", color = TextSecondary) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Accent,
                        unfocusedBorderColor = BorderColor,
                        focusedLabelColor = Accent,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (tempName.isNotBlank()) {
                            viewModel.updateAssistantName(tempName)
                            showNameDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Accent)
                ) {
                    Text("Kaydet", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { showNameDialog = false }) {
                    Text("İptal", color = TextSecondary)
                }
            }
        )
    }

    if (showMorningTimeDialog) {
        AlertDialog(
            onDismissRequest = { showMorningTimeDialog = false },
            containerColor = Surface,
            titleContentColor = TextPrimary,
            title = { Text("Sabah Brifingi Saati", style = AppTypography.titleLarge) },
            text = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = morningHourStr,
                        onValueChange = { if (it.length <= 2 && it.all { char -> char.isDigit() }) morningHourStr = it },
                        label = { Text("Saat", color = TextSecondary) },
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(focusedTextColor = TextPrimary, unfocusedTextColor = TextPrimary)
                    )
                    Text(":", style = AppTypography.titleLarge, color = TextPrimary)
                    OutlinedTextField(
                        value = morningMinStr,
                        onValueChange = { if (it.length <= 2 && it.all { char -> char.isDigit() }) morningMinStr = it },
                        label = { Text("Dakika", color = TextSecondary) },
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(focusedTextColor = TextPrimary, unfocusedTextColor = TextPrimary)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val hr = morningHourStr.toIntOrNull() ?: 8
                        val mn = morningMinStr.toIntOrNull() ?: 0
                        if (hr in 0..23 && mn in 0..59) {
                            viewModel.updateMorningTime(hr, mn)
                            showMorningTimeDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Accent)
                ) {
                    Text("Kaydet", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { showMorningTimeDialog = false }) {
                    Text("İptal", color = TextSecondary)
                }
            }
        )
    }

    if (showEveningTimeDialog) {
        AlertDialog(
            onDismissRequest = { showEveningTimeDialog = false },
            containerColor = Surface,
            titleContentColor = TextPrimary,
            title = { Text("Akşam Analizi Saati", style = AppTypography.titleLarge) },
            text = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = eveningHourStr,
                        onValueChange = { if (it.length <= 2 && it.all { char -> char.isDigit() }) eveningHourStr = it },
                        label = { Text("Saat", color = TextSecondary) },
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(focusedTextColor = TextPrimary, unfocusedTextColor = TextPrimary)
                    )
                    Text(":", style = AppTypography.titleLarge, color = TextPrimary)
                    OutlinedTextField(
                        value = eveningMinStr,
                        onValueChange = { if (it.length <= 2 && it.all { char -> char.isDigit() }) eveningMinStr = it },
                        label = { Text("Dakika", color = TextSecondary) },
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(focusedTextColor = TextPrimary, unfocusedTextColor = TextPrimary)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val hr = eveningHourStr.toIntOrNull() ?: 21
                        val mn = eveningMinStr.toIntOrNull() ?: 0
                        if (hr in 0..23 && mn in 0..59) {
                            viewModel.updateEveningTime(hr, mn)
                            showEveningTimeDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Accent)
                ) {
                    Text("Kaydet", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { showEveningTimeDialog = false }) {
                    Text("İptal", color = TextSecondary)
                }
            }
        )
    }

    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            containerColor = Surface,
            titleContentColor = PriorityHigh,
            title = { Text("TÜM VERİLERİ SIFIRLA", style = AppTypography.titleLarge) },
            text = {
                Text(
                    "Tüm görevlerinizi, ayarlarınızı ve asistan verilerinizi silmek istediğinizden emin misiniz? Bu işlem geri alınamaz ve uygulama varsayılan durumuna sıfırlanacaktır.",
                    style = AppTypography.bodyMedium,
                    color = TextPrimary
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.resetAllData()
                        showResetDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PriorityHigh)
                ) {
                    Text("Evet, Sıfırla", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) {
                    Text("İptal", color = TextSecondary)
                }
            }
        )
    }
}
