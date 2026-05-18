package com.elion.assistant.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "elion_prefs")

@Singleton
class AppPreferences @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val dataStore = context.dataStore

    companion object {
        val ASSISTANT_NAME     = stringPreferencesKey("assistant_name")
        val MORNING_HOUR       = intPreferencesKey("morning_hour")
        val MORNING_MINUTE     = intPreferencesKey("morning_minute")
        val EVENING_HOUR       = intPreferencesKey("evening_hour")
        val EVENING_MINUTE     = intPreferencesKey("evening_minute")
        val NOTIFICATION_SOUND = booleanPreferencesKey("notification_sound")
        val TTS_ENABLED        = booleanPreferencesKey("tts_enabled")
        val COMMENT_TONE       = floatPreferencesKey("comment_tone") // 0=komik, 0.5=nötr, 1=sert
        val FIRST_LAUNCH       = booleanPreferencesKey("first_launch")
    }

    val assistantName: Flow<String> = dataStore.data.map { it[ASSISTANT_NAME] ?: "ELION" }
    val morningHour: Flow<Int>      = dataStore.data.map { it[MORNING_HOUR]   ?: 8 }
    val morningMinute: Flow<Int>    = dataStore.data.map { it[MORNING_MINUTE] ?: 0 }
    val eveningHour: Flow<Int>      = dataStore.data.map { it[EVENING_HOUR]   ?: 21 }
    val eveningMinute: Flow<Int>    = dataStore.data.map { it[EVENING_MINUTE] ?: 0 }
    val notificationSound: Flow<Boolean> = dataStore.data.map { it[NOTIFICATION_SOUND] ?: true }
    val ttsEnabled: Flow<Boolean>   = dataStore.data.map { it[TTS_ENABLED]    ?: true }
    val commentTone: Flow<Float>    = dataStore.data.map { it[COMMENT_TONE]   ?: 0f }
    val isFirstLaunch: Flow<Boolean> = dataStore.data.map { it[FIRST_LAUNCH]   ?: true }

    suspend fun setAssistantName(name: String) = dataStore.edit { it[ASSISTANT_NAME] = name }
    suspend fun setMorningTime(hour: Int, minute: Int) = dataStore.edit {
        it[MORNING_HOUR] = hour; it[MORNING_MINUTE] = minute
    }
    suspend fun setEveningTime(hour: Int, minute: Int) = dataStore.edit {
        it[EVENING_HOUR] = hour; it[EVENING_MINUTE] = minute
    }
    suspend fun setNotificationSound(enabled: Boolean) = dataStore.edit { it[NOTIFICATION_SOUND] = enabled }
    suspend fun setTtsEnabled(enabled: Boolean)        = dataStore.edit { it[TTS_ENABLED] = enabled }
    suspend fun setCommentTone(tone: Float)            = dataStore.edit { it[COMMENT_TONE] = tone }
    suspend fun setFirstLaunchDone()                   = dataStore.edit { it[FIRST_LAUNCH] = false }
    suspend fun clearAll()                             = dataStore.edit { it.clear() }
}
