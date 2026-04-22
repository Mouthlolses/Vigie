package com.seuvigie.domain.model

data class HomeData(
    val user: User,
    val bills: List<Bill>
)