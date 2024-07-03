package com.example.Prueba.Entities
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Photo(
    @Column(name = "album_id")
    val albumId: Long = 0,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var title: String="",
    var url: String="",
    var thumbnailUrl: String=""
)
/*
{
    constructor(title: String, url: String, thumbnailUrl: String) : this(
        id = 0,
        title = title,
        url = url,
        thumbnailUrl = thumbnailUrl
    )
}
*/