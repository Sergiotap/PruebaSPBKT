package com.example.prueba.controllers

import com.example.prueba.entities.Album
import com.example.prueba.repositories.AlbumRepository
import com.example.prueba.services.AlbumService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/albums")
class AlbumController (private val albumService: AlbumService) {
    @Autowired
    private lateinit var alBumRepository: AlbumRepository
    @GetMapping("/")
    fun getAllAlbums(): List<Album> = albumService.getAllAlbums()
    @GetMapping("/{id}")
    fun getAlbumById(@PathVariable id:Long):ResponseEntity<Album>{
        val album = albumService.getAlbumById(id)
        return if (album != null) {
            ResponseEntity.ok(album)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun createAlbum(@RequestBody album: Album): Album = albumService.createAlbum(album)
    @PutMapping("/{id}")
    fun updateAlbum(@PathVariable id: Long, @RequestBody updatedAlbum: Album): ResponseEntity<Album> {
        updatedAlbum.id=id
        val updated = albumService.updateAlbum(id, updatedAlbum)
        return if (updated != null) {
            ResponseEntity.ok(updated)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    @DeleteMapping("/{id}")
    fun deleteAlbum(@PathVariable id: Long):ResponseEntity<String>{
        return if (albumService.deleteAlbum(id)){
            ResponseEntity.ok("Se ha eliminado el álbum con id $id")
        }
        else{
            ResponseEntity.notFound().build()
        }
    }
}