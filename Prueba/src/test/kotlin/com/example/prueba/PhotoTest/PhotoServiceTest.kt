package com.example.prueba.PhotoTest

import com.example.prueba.entities.Photo
import com.example.prueba.repositories.PhotoRepository
import com.example.prueba.services.PhotoService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.web.client.RestTemplate
import java.util.*

class PhotoServiceTest {

    private lateinit var photoService: PhotoService
    private lateinit var photoRepository: PhotoRepository
    private lateinit var restTemplate: RestTemplate

    @BeforeEach
    fun setUp() {
        photoRepository = Mockito.mock(PhotoRepository::class.java)
        restTemplate = Mockito.mock(RestTemplate::class.java)
        photoService = PhotoService(restTemplate, photoRepository)
    }

    @Test
    fun testFetchAndSavePhotos_Success() {
        // Arrange
        val photos = arrayOf(
            Photo(albumId = 1, id = 1, title = "title1", url = "url1", thumbnailUrl = "thumb1"),
            Photo(albumId = 1, id = 2, title = "title2", url = "url2", thumbnailUrl = "thumb2")
        )

        Mockito.`when`(restTemplate.getForObject("https://jsonplaceholder.typicode.com/photos/", Array<Photo>::class.java))
            .thenReturn(photos)

        // Act
        photoService.fetchAndSavePhotos()

        // Assert
        Mockito.verify(photoRepository, Mockito.times(1)).saveAll(photos.toList())
    }

    @Test
    fun testFetchAndSavePhotos_Failure() {
        // Arrange
        Mockito.`when`(restTemplate.getForObject("https://jsonplaceholder.typicode.com/photos/", Array<Photo>::class.java))
            .thenReturn(null)

        // Act
        photoService.fetchAndSavePhotos()

        // Assert
        Mockito.verify(photoRepository, Mockito.never()).saveAll(Mockito.anyList())
    }

    @Test
    fun testGetAllPhotos_Success() {
        // Arrange
        val photos = listOf(Photo(albumId = 1, id = 1, title = "title1", url = "url1", thumbnailUrl = "thumb1"))

        Mockito.`when`(photoRepository.findAll()).thenReturn(photos)

        // Act
        val result = photoService.getAllPhotos()

        // Assert
        assertEquals(photos, result)
    }

    @Test
    fun testGetPhotoById_Success() {
        // Arrange
        val photo = Photo(albumId = 1, id = 1, title = "title1", url = "url1", thumbnailUrl = "thumb1")

        Mockito.`when`(photoRepository.findById(1L)).thenReturn(Optional.of(photo))

        // Act
        val result = photoService.getPhotoById(1L)

        // Assert
        assertEquals(photo, result)
    }

    @Test
    fun testGetPhotoById_NotFound() {
        // Arrange
        Mockito.`when`(photoRepository.findById(1L)).thenReturn(Optional.empty())

        // Act
        val result = photoService.getPhotoById(1L)

        // Assert
        assertNull(result)
    }

    @Test
    fun testCreatePhoto_Success() {
        // Arrange
        val photo = Photo(albumId = 1, id = 1, title = "title1", url = "url1", thumbnailUrl = "thumb1")

        Mockito.`when`(photoRepository.save(photo)).thenReturn(photo)

        // Act
        val result = photoService.createPhoto(photo)

        // Assert
        assertEquals(photo, result)
    }

    @Test
    fun testUpdatePhoto_Success() {
        // Arrange
        val photo = Photo(albumId = 1, id = 1, title = "title1", url = "url1", thumbnailUrl = "thumb1")

        Mockito.`when`(photoRepository.existsById(1L)).thenReturn(true)
        Mockito.`when`(photoRepository.save(photo)).thenReturn(photo)

        // Act
        val result = photoService.updatePhoto(1L, photo)

        // Assert
        assertEquals(photo, result)
    }

    @Test
    fun testUpdatePhoto_NotFound() {
        // Arrange
        val photo = Photo(albumId = 1, id = 1, title = "title1", url = "url1", thumbnailUrl = "thumb1")

        Mockito.`when`(photoRepository.existsById(1L)).thenReturn(false)

        // Act
        val result = photoService.updatePhoto(1L, photo)

        // Assert
        assertNull(result)
    }

    @Test
    fun testDeletePhoto_Success() {
        // Arrange
        Mockito.`when`(photoRepository.existsById(1L)).thenReturn(true)

        // Act
        val result = photoService.deletePhoto(1L)

        // Assert
        assertTrue(result)
        Mockito.verify(photoRepository, Mockito.times(1)).deleteById(1L)
    }

    @Test
    fun testDeletePhoto_NotFound() {
        // Arrange
        Mockito.`when`(photoRepository.existsById(1L)).thenReturn(false)

        // Act
        val result = photoService.deletePhoto(1L)

        // Assert
        assertFalse(result)
        Mockito.verify(photoRepository, Mockito.never()).deleteById(1L)
    }
}
