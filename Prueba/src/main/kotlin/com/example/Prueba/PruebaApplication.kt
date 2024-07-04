package com.example.Prueba

import com.example.Prueba.Services.AlbumService
import com.example.Prueba.Services.PhotoService
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
@SpringBootApplication
class PruebaApplication {
	@Bean
	fun run(albumService: AlbumService, photoService: PhotoService) = ApplicationRunner {
		println("Executing run method...")
		albumService.fetchAndSaveAlbums()
		photoService.fetchAndSavePhotos()
	}
}
fun main(args: Array<String>) {
	val context = runApplication<PruebaApplication>(*args)
	val albumService = context.getBean(AlbumService::class.java)
	val photoService = context.getBean(PhotoService::class.java)
	context.getBean(ApplicationRunner::class.java)
}


