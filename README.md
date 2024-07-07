API with JSON Placeholder using Albums and Photos.
## Decissions

For developing this project I chose **Intellij Idea Community** because of the facilities of this IDE for developing with Kotlin.

I chose the **Spring Boot** version 2.5.6 due to compatibility with **Swagger UI** 2.

I chose **Java** 17 for compatibility reasons with **Spring Boot** 2.5.6.

## Installation
```sh
$ git clone https://github.com/Sergiotap/PruebaSPBKT
$ cd Prueba
```
You could use **Intellij Idea Community** to run this project. You can download it here:
https://www.jetbrains.com/idea/download/?section=windows.

You also could use **Postman** to fetch the API's endpoints. You can download it here:
https://www.postman.com/downloads/.

You should configure your ```JAVA_HOME``` evvironment variable to use the jdk version 17. You can download it here:
https://www.oracle.com/es/java/technologies/downloads/#jdk17-windows.

## Usage

Firstly, you have to run the file called ```PruebaAplication.kt```

To execute the tests you can run ```AlbumControllerTest```, ```AlbumServiceTest```, ```PhotoControllerTest``` and ```PhotoServiceTest```.

To access albums you can fetch https://localhost:8080/api/albums/.

To access photos you can fetch https://localhost:8080/api/photos/.

To access swagger ui you can go to https://localhost:8080/swagger-ui.html.

## Creator
[Sergio Tapia Rodríguez](https://github.com/Sergiotap)
