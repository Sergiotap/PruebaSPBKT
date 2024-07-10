package com.example.prueba

import com.example.prueba.services.AlbumService
import com.example.prueba.services.PhotoService
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class PruebaApplication {
	@Bean
	fun run(albumService: AlbumService, photoService: PhotoService) = ApplicationRunner {
		println("Executing run method...")
		photoService.fetchAndSavePhotos()
		albumService.fetchAndSaveAlbums()
	}
}
fun main(args: Array<String>) {
	val context = runApplication<PruebaApplication>(*args)
	val albumService = context.getBean(AlbumService::class.java)
	val photoService = context.getBean(PhotoService::class.java)
	context.getBean(ApplicationRunner::class.java)
}


