package com.example.prueba.entities

import javax.persistence.*


@Entity
data class Album(
    val userId: Long,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    val title: String,
    @OneToMany(mappedBy = "albumId", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var photos: MutableList<Photo> = mutableListOf()
)
{
    constructor(): this(0, 0, "", mutableListOf())
}