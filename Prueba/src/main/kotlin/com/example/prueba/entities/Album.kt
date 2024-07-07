package com.example.prueba.entities

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Entity


@Entity
data class Album(
    val userId: Long,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    val title: String
)
{
    constructor(): this(0, 0, "")
}