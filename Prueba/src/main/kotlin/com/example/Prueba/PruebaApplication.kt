package com.example.Prueba

import com.example.Prueba.Servicies.AlbumService
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
@SpringBootApplication
class PruebaApplication {
	@Bean
	fun run(albumService: AlbumService) = ApplicationRunner {
		println("Executing run method...")
		albumService.fetchAndSaveAlbums()
	}
}
fun main(args: Array<String>) {
	val context = runApplication<PruebaApplication>(*args)
	val albumService = context.getBean(AlbumService::class.java)
	context.getBean(ApplicationRunner::class.java)
}


