package com.example.Prueba.Services

import com.example.Prueba.Entities.Photo
import com.example.Prueba.Repositories.PhotoRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class PhotoService(private val restTemplate: RestTemplate, private val photoRepository: PhotoRepository) {
    private val logger = LoggerFactory.getLogger(AlbumService::class.java)
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
    fun fetchAndSavePhotos() {
        val url = "https://jsonplaceholder.typicode.com/photos/"
        val response = restTemplate.getForObject(url, Array<Photo>::class.java)
        if (response != null) {
            logger.info("Fetched ${response.size} photos from $url")
            response.forEach { photo ->
                logger.info("Saving album: $photo")
            }
            photoRepository.saveAll(response.toList())
        } else {
            logger.warn("Failed to fetch photos from $url")
        }
    }
}