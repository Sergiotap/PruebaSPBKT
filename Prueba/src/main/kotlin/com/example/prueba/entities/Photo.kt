package com.example.prueba.entities
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Photo(
    @Column(name = "album_id")
    val albumId: Long = 0,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
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