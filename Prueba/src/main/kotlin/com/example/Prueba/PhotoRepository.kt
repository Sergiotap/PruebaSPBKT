package com.example.Prueba

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PhotoRepository :JpaRepository<Photo, Long>