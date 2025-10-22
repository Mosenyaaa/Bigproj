package com.example.bigproj.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class SendCodeOnEmailResponseDto(
    val id: Int,
    @SerialName("long_id")
    val longId: String,
    val slug: String?,
    @SerialName("creation_dt")
    val creationDt: String, // или используйте Instant если хотите парсить как дату
    val type: String,
    val email: String?,
    @SerialName("user_id")
    val userId: Int?,
    @SerialName("is_active")
    val isActive: Boolean,
    @SerialName("allowed_types")
    val allowedTypes: List<String>
)