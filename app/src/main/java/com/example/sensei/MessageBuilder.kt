package com.example.sensei

object MessageBuilder {
    fun buildSmsMessage(statusText: String, startTimeMillis: Long, durationMinutes: Int): String {
        val timeCalculator = TimeCalculator()
        val remainingTimeMsg = timeCalculator.calculateRemainingTimeMessage(
            startTimeMillis,
            durationMinutes
        )
        return "Hi, This is Sensei, ${BuildConfig.USER_NAME}'s assistant. Right now he ${statusText.lowercase()}. $remainingTimeMsg Thank you."
    }
}
