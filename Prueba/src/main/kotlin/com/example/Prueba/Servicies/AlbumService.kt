package com.example.Prueba.Servicies

import com.example.Prueba.Entities.Album
import com.example.Prueba.Repositories.AlbumRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Service
class AlbumService (private val restTemplate:RestTemplate, private val albumRepository: AlbumRepository) {
    private val logger = LoggerFactory.getLogger(AlbumService::class.java)
    fun getAllAlbums(): List<Album> = albumRepository.findAll()
    fun getAlbumById(id:Long): Album? = albumRepository.findById(id).orElse(null)
    fun createAlbum(album: Album): Album = albumRepository.save(album)
    fun updateAlbum(id: Long, updatedAlbum: Album): Album? {
        return if (albumRepository.existsById(id)) {
            albumRepository.save(updatedAlbum)
        }
        else{
            null
        }
    }
    fun deleteAlbum(id: Long): Boolean {
        return if (albumRepository.existsById(id)){
            albumRepository.deleteById(id)
            true
        }
        else{
            false
        }
    }
    fun fetchAndSaveAlbums() {
        val url = "https://jsonplaceholder.typicode.com/albums/"
        val response = restTemplate.getForObject(url, Array<Album>::class.java)
        if (response != null) {
            logger.info("Fetched ${response.size} albums from $url")
            response.forEach { album ->
                logger.info("Saving album: $album")
            }
            albumRepository.saveAll(response.toList())
        } else {
            logger.warn("Failed to fetch albums from $url")
        }
    }
}