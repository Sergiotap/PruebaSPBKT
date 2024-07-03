package com.example.Prueba.Servicies

import com.example.Prueba.Entities.Album
import com.example.Prueba.Repositories.AlbumRepository
import org.springframework.stereotype.Service

@Service
class AlbumService (private val albumRepository: AlbumRepository) {
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
}