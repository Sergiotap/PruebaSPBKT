package com.example.Prueba.Repositories

import com.example.Prueba.Entities.Photo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PhotoRepository :JpaRepository<Photo, Long>