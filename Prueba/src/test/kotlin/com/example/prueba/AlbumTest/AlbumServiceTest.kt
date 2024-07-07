package com.example.prueba.AlbumTest

import com.example.prueba.entities.Album
import com.example.prueba.repositories.AlbumRepository
import com.example.prueba.services.AlbumService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.springframework.web.client.RestTemplate
import java.util.*

class AlbumServiceTest {

    @Mock
    private lateinit var restTemplate: RestTemplate

    @Mock
    private lateinit var albumRepository: AlbumRepository

    @InjectMocks
    private lateinit var albumService: AlbumService

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
        `when`(albumRepository.findAll()).thenReturn(albums)

        // Act
        val result = albumService.getAllAlbums()

        // Assert
        assertEquals(albums, result)
    }

    @Test
    fun testGetAlbumById_Success() {
        // Arrange
        val id = 1L
        val album = Album(1, id, "Test Album")
        `when`(albumRepository.findById(id)).thenReturn(Optional.of(album))

        // Act
        val result = albumService.getAlbumById(id)

        // Assert
        assertNotNull(result)
        assertEquals(album, result)
    }

    @Test
    fun testGetAlbumById_NotFound() {
        // Arrange
        val id = 1L
        `when`(albumRepository.findById(id)).thenReturn(Optional.empty())

        // Act
        val result = albumService.getAlbumById(id)

        // Assert
        assertNull(result)
    }

    @Test
    fun testCreateAlbum() {
        // Arrange
        val album = Album(1, 0, "New Album")
        val savedAlbum = album.copy(id = 1)
        `when`(albumRepository.save(album)).thenReturn(savedAlbum)

        // Act
        val result = albumService.createAlbum(album)

        // Assert
        assertNotNull(result)
        assertEquals(1L, result.id)
        assertEquals("New Album", result.title)
    }

    @Test
    fun testUpdateAlbum_Success() {
        // Arrange
        val id = 1L
        val existingAlbum = Album(1, id, "Existing Album")
        val updatedAlbum = Album(1, id, "Updated Album")
        `when`(albumRepository.existsById(id)).thenReturn(true)
        `when`(albumRepository.save(updatedAlbum)).thenReturn(updatedAlbum)

        // Act
        val result = albumService.updateAlbum(id, updatedAlbum)

        // Assert
        assertNotNull(result)
        assertEquals(updatedAlbum, result)
    }

    @Test
    fun testUpdateAlbum_NotFound() {
        // Arrange
        val id = 1L
        val updatedAlbum = Album(1, id, "Updated Album")
        `when`(albumRepository.existsById(id)).thenReturn(false)

        // Act
        val result = albumService.updateAlbum(id, updatedAlbum)

        // Assert
        assertNull(result)
    }

    @Test
    fun testDeleteAlbum_Success() {
        // Arrange
        val id = 1L
        `when`(albumRepository.existsById(id)).thenReturn(true)
        doNothing().`when`(albumRepository).deleteById(id)

        // Act
        val result = albumService.deleteAlbum(id)

        // Assert
        assertTrue(result)
        verify(albumRepository, times(1)).deleteById(id)
    }

    @Test
    fun testDeleteAlbum_NotFound() {
        // Arrange
        val id = 1L
        `when`(albumRepository.existsById(id)).thenReturn(false)

        // Act
        val result = albumService.deleteAlbum(id)

        // Assert
        assertFalse(result)
        verify(albumRepository, never()).deleteById(id)
    }

    @Test
    fun testFetchAndSaveAlbums_Success() {
        // Arrange
        val url = "https://jsonplaceholder.typicode.com/albums/"
        val albums = arrayOf(
            Album(1, 1, "Album 1"),
            Album(1, 2, "Album 2")
        )
        `when`(restTemplate.getForObject(url, Array<Album>::class.java)).thenReturn(albums)
        `when`(albumRepository.saveAll(albums.toList())).thenReturn(albums.toList())

        // Act
        albumService.fetchAndSaveAlbums()

        // Assert
        verify(albumRepository, times(1)).saveAll(albums.toList())
    }

    @Test
    fun testFetchAndSaveAlbums_Failure() {
        // Arrange
        val url = "https://jsonplaceholder.typicode.com/albums/"
        `when`(restTemplate.getForObject(url, Array<Album>::class.java)).thenReturn(null)

        // Act
        albumService.fetchAndSaveAlbums()

        // Assert
        verify(albumRepository, never()).saveAll(anyList())
    }
}