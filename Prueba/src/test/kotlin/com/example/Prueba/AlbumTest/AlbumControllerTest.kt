package com.example.Prueba.AlbumTest

import com.example.Prueba.Controllers.AlbumController
import com.example.Prueba.Entities.Album
import com.example.Prueba.Services.AlbumService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class AlbumControllerTest {

    @Mock
    private lateinit var albumService: AlbumService

    @InjectMocks
    private lateinit var albumController: AlbumController

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testGetAllAlbums() {
        // Arrange
        val albums = listOf(
            Album(1, 1, "Album 1"),
            Album(1, 2, "Album 2"),
            Album(2, 3, "Album 3")
        )
        `when`(albumService.getAllAlbums()).thenReturn(albums)

        // Act
        val response: List<Album> = albumController.getAllAlbums()

        // Assert
        assertEquals(albums, response)
    }

    @Test
    fun testGetAlbumById_Success() {
        // Arrange
        val id = 1L
        val expectedAlbum = Album(1, id, "Test Album")
        `when`(albumService.getAlbumById(id)).thenReturn(expectedAlbum)

        // Act
        val response: ResponseEntity<Album> = albumController.getAlbumById(id)

        // Assert
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(expectedAlbum, response.body)
    }

    @Test
    fun testGetAlbumById_NotFound() {
        // Arrange
        val id = 2L
        `when`(albumService.getAlbumById(id)).thenReturn(null)

        // Act
        val response: ResponseEntity<Album> = albumController.getAlbumById(id)

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }

    @Test
    fun testCreateAlbum() {
        // Arrange
        val newAlbum = Album(1, 0, "New Album")
        `when`(albumService.createAlbum(newAlbum)).thenReturn(newAlbum.copy(id = 1))

        // Act
        val createdAlbum: Album = albumController.createAlbum(newAlbum)

        // Assert
        assertEquals(1L, createdAlbum.id)
        assertEquals(newAlbum.title, createdAlbum.title)
    }

    @Test
    fun testUpdateAlbum_Success() {
        // Arrange
        val id = 1L
        val updatedAlbum = Album(1, id, "Updated Album")
        val existingAlbum = Album(1, id, "Original Album")
        `when`(albumService.getAlbumById(id)).thenReturn(existingAlbum)
        `when`(albumService.updateAlbum(id, updatedAlbum)).thenReturn(updatedAlbum)

        // Act
        val response: ResponseEntity<Album> = albumController.updateAlbum(id, updatedAlbum)

        // Assert
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(updatedAlbum, response.body)
    }

    @Test
    fun testUpdateAlbum_NotFound() {
        // Arrange
        val id = 2L
        val updatedAlbum = Album(1, id, "Updated Album")
        `when`(albumService.getAlbumById(id)).thenReturn(null)

        // Act
        val response: ResponseEntity<Album> = albumController.updateAlbum(id, updatedAlbum)

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }

    @Test
    fun testDeleteAlbum_Success() {
        // Arrange
        val id = 1L
        `when`(albumService.deleteAlbum(id)).thenReturn(true)

        // Act
        val response: ResponseEntity<String> = albumController.deleteAlbum(id)

        // Assert
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals("Se ha eliminado el álbum con id $id", response.body)
    }

    @Test
    fun testDeleteAlbum_NotFound() {
        // Arrange
        val id = 2L
        `when`(albumService.deleteAlbum(id)).thenReturn(false)

        // Act
        val response: ResponseEntity<String> = albumController.deleteAlbum(id)

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }
}
