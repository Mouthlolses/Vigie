package com.seuvigie.domain.model

data class Bill(
    val id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val expirationDate: String? = null,
    val accountTypes: List<AccountType?> = emptyList(),
    val recurrenceType: RecurrenceType? = null,
)

enum class AccountType {
    LIGHT,
    WATER,
    INTERNET,
    SUBSCRIPTION
}

enum class RecurrenceType {
    DAY,
    MOUTH,
    YEAR
}
