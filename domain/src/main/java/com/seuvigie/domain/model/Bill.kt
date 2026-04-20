package com.seuvigie.domain.model

data class Bill(
    val title: String? = null,
    val description: String? = null,
    val expirationDate: String? = null,
    val accountTypes: AccountType? = null,
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
