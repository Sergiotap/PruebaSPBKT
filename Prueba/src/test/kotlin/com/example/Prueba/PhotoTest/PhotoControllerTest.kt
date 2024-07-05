package com.example.Prueba.PhotoTest

import com.example.Prueba.Controllers.PhotoController
import com.example.Prueba.Entities.Photo
import com.example.Prueba.Services.PhotoService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class PhotoControllerTest {

    private lateinit var photoService: PhotoService
    private lateinit var photoController: PhotoController

    @BeforeEach
    fun setUp() {
        photoService = Mockito.mock(PhotoService::class.java)
        photoController = PhotoController(photoService)
    }

    @Test
    fun testGetAllPhotos_Success() {
        // Arrange
        val photos = listOf(Photo(albumId = 1, id = 1, title = "title1", url = "url1", thumbnailUrl = "thumb1"))
        Mockito.`when`(photoService.getAllPhotos()).thenReturn(photos)

        // Act
        val result = photoController.getAllPhotos()

        // Assert
        assertEquals(photos, result)
    }

    @Test
    fun testGetPhotoById_Success() {
        // Arrange
        val photo = Photo(albumId = 1, id = 1, title = "title1", url = "url1", thumbnailUrl = "thumb1")
        Mockito.`when`(photoService.getPhotoById(1L)).thenReturn(photo)

        // Act
        val result = photoController.getPhotoById(1L)

        // Assert
        assertEquals(ResponseEntity.ok(photo), result)
    }

    @Test
    fun testGetPhotoById_NotFound() {
        // Arrange
        Mockito.`when`(photoService.getPhotoById(1L)).thenReturn(null)

        // Act
        val result = photoController.getPhotoById(1L)

        // Assert
        assertEquals(ResponseEntity.notFound().build(), result)
    }

    @Test
    fun testCreatePhoto_Success() {
        // Arrange
        val photo = Photo(albumId = 1, id = 1, title = "title1", url = "url1", thumbnailUrl = "thumb1")
        Mockito.`when`(photoService.createPhoto(photo)).thenReturn(photo)

        // Act
        val result = photoController.createPhoto(photo)

        // Assert
        assertEquals(photo, result)
    }

    @Test
    fun testUpdatePhoto_Success() {
        // Arrange
        val photo = Photo(albumId = 1, id = 1, title = "title1", url = "url1", thumbnailUrl = "thumb1")
        Mockito.`when`(photoService.getPhotoById(1L)).thenReturn(photo)
        Mockito.`when`(photoService.updatePhoto(1L, photo)).thenReturn(photo)

        // Act
        val result = photoController.updatePhoto(1L, photo)

        // Assert
        assertEquals(ResponseEntity.ok(photo), result)
    }

    @Test
    fun testUpdatePhoto_NotFound() {
        // Arrange
        val photo = Photo(albumId = 1, id = 1, title = "title1", url = "url1", thumbnailUrl = "thumb1")
        Mockito.`when`(photoService.getPhotoById(1L)).thenReturn(null)

        // Act
        val result = photoController.updatePhoto(1L, photo)

        // Assert
        assertEquals(ResponseEntity.notFound().build(), result)
    }

    @Test
    fun testDeletePhoto_Success() {
        // Arrange
        Mockito.`when`(photoService.deletePhoto(1L)).thenReturn(true)

        // Act
        val result = photoController.deletePhoto(1L)

        // Assert
        assertEquals(ResponseEntity.ok("Se ha eliminado la foto con id 1"), result)
    }

    @Test
    fun testDeletePhoto_NotFound() {
        // Arrange
        Mockito.`when`(photoService.deletePhoto(1L)).thenReturn(false)

        // Act
        val result = photoController.deletePhoto(1L)

        // Assert
        assertEquals(ResponseEntity.notFound().build(), result)
    }
}
