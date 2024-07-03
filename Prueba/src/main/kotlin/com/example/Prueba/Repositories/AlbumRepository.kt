package com.example.Prueba.Repositories

import com.example.Prueba.Entities.Album
import org.springframework.data.jpa.repository.JpaRepository

interface AlbumRepository : JpaRepository<Album, Long>