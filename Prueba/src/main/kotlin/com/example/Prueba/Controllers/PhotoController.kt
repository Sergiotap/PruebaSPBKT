package com.example.Prueba.Controllers

import com.example.Prueba.Entities.Photo
import com.example.Prueba.Services.PhotoService
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
@RequestMapping("/api/photos")
class PhotoController (private val photoService: PhotoService){
    @GetMapping("/")
    fun getAllPhotos(): List<Photo> = photoService.getAllPhotos()
    @GetMapping("/{id}")
    fun getPhotoById(@PathVariable id:Long):ResponseEntity<Photo>{
        return photoService.getPhotoById(id)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()

    }
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun createPhoto(@RequestBody photo: Photo): Photo =photoService.createPhoto(photo)
    @PutMapping("/{id}")
    fun updatePhoto(@PathVariable id: Long, @RequestBody updatedPhoto: Photo): ResponseEntity<Photo> {
        val existingAlbum = photoService.getPhotoById(id)
        return if (existingAlbum != null) {
            updatedPhoto.id = id
            val updated = photoService.updatePhoto(id, updatedPhoto)
            ResponseEntity.ok(updated)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deletePhoto(@PathVariable id: Long):ResponseEntity<String>{
        return if (photoService.deletePhoto(id)){
            ResponseEntity.ok("Se ha eliminado la foto con id $id")
        }
        else{
            ResponseEntity.notFound().build()
        }
    }

}