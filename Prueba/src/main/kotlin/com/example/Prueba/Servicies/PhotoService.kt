package com.example.Prueba.Servicies

import com.example.Prueba.Entities.Photo
import com.example.Prueba.Repositories.PhotoRepository
import org.springframework.stereotype.Service

@Service
class PhotoService(private val photoRepository: PhotoRepository) {
    fun getAllPhotos(): List<Photo> = photoRepository.findAll()
    fun getPhotoById(id:Long): Photo? = photoRepository.findById(id).orElse(null)
    fun createPhoto(photo: Photo): Photo = photoRepository.save(photo)
    fun updatePhoto(id: Long, updatedPhoto: Photo): Photo? {
        return if (photoRepository.existsById(id)) {
            photoRepository.save(updatedPhoto)
        }
        else{
            null
        }
    }
    fun deletePhoto(id: Long): Boolean {
        return if (photoRepository.existsById(id)) {
            photoRepository.deleteById(id)
            true
        }
        else{
            false
        }
    }
}