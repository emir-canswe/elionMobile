package com.elion.assistant.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.elion.assistant.domain.model.Priority
import com.elion.assistant.domain.model.Task
import com.elion.assistant.ui.theme.*
import java.time.LocalDate

@Composable
fun TaskCard(
    task: Task,
    onCompleteToggle: () -> Unit,
    modifier: Modifier = Modifier,
    categoryName: String? = null,
    categoryColorHex: String? = null,
    onDelete: (() -> Unit)? = null
) {
    val isOverdue = task.dueDate != null && task.dueDate.isBefore(LocalDate.now()) && !task.isCompleted
    val parsedColor = remember(categoryColorHex) {
        try {
            Color(android.graphics.Color.parseColor(categoryColorHex ?: "#4A90D9"))
        } catch (e: Exception) {
            Accent
        }
    }
    
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Surface)
            .border(1.dp, BorderColor, RoundedCornerShape(16.dp))
            .clickable { onCompleteToggle() }
            .padding(14.dp)
            .alpha(if (task.isCompleted) 0.5f else 1f),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Checkbox
        Box(
            modifier = Modifier
                .size(22.dp)
                .clip(CircleShape)
                .background(if (task.isCompleted) Accent else Color.Transparent)
                .border(2.dp, if (task.isCompleted) Accent else Color(0xFF333333), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            if (task.isCompleted) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
        
        Spacer(modifier = Modifier.width(12.dp))
        
        // Body
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = task.title,
                style = AppTypography.titleSmall.copy(
                    textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None,
                    color = if (task.isCompleted) TextSecondary else TextPrimary
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (categoryName != null) {
                    Box(
                        modifier = Modifier
                            .background(parsedColor.copy(alpha = 0.15f), RoundedCornerShape(20.dp))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = categoryName,
                            style = AppTypography.labelSmall,
                            color = parsedColor
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
                
                if (isOverdue) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = PriorityHigh,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Gecikti",
                        style = AppTypography.labelSmall,
                        color = PriorityHigh
                    )
                } else if (task.dueTime != null) {
                    Text(
                        text = task.dueTime.toString(),
                        style = AppTypography.labelSmall,
                        color = TextSecondary
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.width(12.dp))

        if (onDelete != null) {
            IconButton(
                onClick = { onDelete() },
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Sil",
                    tint = PriorityHigh,
                    modifier = Modifier.size(16.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
        
        // Priority dot
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(
                    when (task.priority) {
                        Priority.HIGH -> PriorityHigh
                        Priority.NORMAL -> PriorityNormal
                        Priority.LOW -> PriorityLow
                    }
                )
        )
    }
}
