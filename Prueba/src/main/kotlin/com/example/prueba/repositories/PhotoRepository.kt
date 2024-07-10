package com.example.prueba.repositories

import com.example.prueba.entities.Photo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PhotoRepository : JpaRepository<Photo, Long>{
    @Query("SELECT p FROM Photo p WHERE p.albumId = :albumId")
    fun getPhotosByAlbumId(@Param("albumId") albumId: Long): List<Photo>
}