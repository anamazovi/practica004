package es.cic.curso.practica004.controller;
/*
 * Esta clase es un controlador REST en Spring Boot que maneja
 * las solicitudes HTTP relacionadas con la entidad Pelicula.
 */

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso.practica004.model.Pelicula;
import es.cic.curso.practica004.service.PeliculaService;

/*
 * @RestController: Marca la clase como un controlador REST, 
 * que maneja solicitudes HTTP y devuelve respuestas HTTP en 
 * formato JSON.
 * @RequestMapping("/peliculas"): Define la ruta base para 
 * todas las solicitudes manejadas por este controlador. 
 * Todas las rutas definidas en esta clase comenzarán con 
 * /peliculas.
 */
@RestController
@RequestMapping ("api/peliculas")
public class PeliculaController {

    /*
     * @Autowired: Inyecta automáticamente una instancia de 
     * PeliculaService. Esto permite que el controlador use 
     * los métodos del servicio para manejar las solicitudes.
     */
    @Autowired
    private PeliculaService peliculaService;


    /*
    * OBTENER TODAS LAS PELICULAS
    * @GetMapping: Maneja solicitudes HTTP GET en la ruta base /peliculas.
    * Método: getAllPeliculas().
    * Propósito: Recuperar y devolver una lista de todas las películas. 
    * Retorno: Devuelve una lista de objetos Pelicula en formato JSON.
    */
    @GetMapping
    public List <Pelicula> getAllPeliculas() {
        return peliculaService.findAll();
    }

    /*
     * OBTENER UNA PELICULA POR SU ID
     * @GetMapping("/{id}"): Maneja solicitudes HTTP GET en la ruta 
     * /peliculas/{id}, donde {id} es un parámetro de ruta.
     * ResponseEntity es una clase de Spring Framework que representa
     * una respuesta de HTTP
     * @PathVariable Long id: Extrae el valor del parámetro 
     * de ruta y lo asigna al argumento id del método.
     * Método: getPeliculaById(Long id).
     * Propósito: Recuperar una película específica por su ID.
     * Retorno: Devuelve un ResponseEntity<Pelicula>:
     * Si la película existe devuelve la película con un estado HTTP
     * 200 (ok)
     * Si la plícula no existe, devuelve un estado HTTP 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> getPeliculaById(@PathVariable Long id) {
        Optional<Pelicula> pelicula = peliculaService.findById(id);
        if (pelicula.isPresent()) {
            return ResponseEntity.ok(pelicula.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*
     * CREAR UNA NUEVA PELICULA
     * @PostMapping: Maneja solicitudes HTTP POST en la ruta base /peliculas.
     * @RequestBody Pelicula pelicula: Indica que el cuerpo de la solicitud 
     * HTTP se debe deserializar en un objeto Pelicula.
     * Método: createPelicula(Pelicula pelicula).
     * Propósito: Crear una nueva película.
     * Retorno: Devuelve un ResponseEntity<Pelicula> con la película guardada 
     * y un estado HTTP 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Pelicula> createPelicula(@RequestBody Pelicula pelicula) {
        Pelicula savedPelicula = peliculaService.save(pelicula);
        return ResponseEntity.status (HttpStatus.CREATED).body(savedPelicula);
    }

    /*
     * ACTUALIZAR UNA PELICULA EXISTENTE
     * @PutMapping("/{id}"): Maneja solicitudes HTTP PUT en la ruta /peliculas/{id}.
     * @PathVariable Long id: Extrae el valor del parámetro de ruta y lo asigna al argumento id del método.
     * @RequestBody Pelicula peliculaDetails: Indica que el cuerpo de la solicitud HTTP se debe deserializar 
     * en un objeto Pelicula.
     * Método: updatePelicula(Long id, Pelicula peliculaDetails).
     * Propósito: Actualizar una película existente.
     * Retorno: Devuelve un ResponseEntity<Pelicula>:
     * Si la película existe, actualiza los detalles y devuelve la 
     * película actualizada con un estado HTTP 200 (OK).
     * Si la película no existe, devuelve un estado HTTP 404 (Not Found).
     */
    @PutMapping ("/{id}")
    public ResponseEntity<Pelicula> updatePelicula(@PathVariable Long id, @RequestBody Pelicula peliculaDetails) {
        Optional <Pelicula> optionalPelicula = peliculaService.findById(id);
        if (optionalPelicula.isPresent()) {
            Pelicula existingPelicula = optionalPelicula.get();
            existingPelicula.setTitulo(peliculaDetails.getTitulo());
            existingPelicula.setDirector(peliculaDetails.getDirector());
            existingPelicula.setAno(peliculaDetails.getAno());
            existingPelicula.setGenero(peliculaDetails.getGenero());
            existingPelicula.setDisponible(peliculaDetails.isDisponible());
            Pelicula updatePelicula = peliculaService.save (existingPelicula);
            return ResponseEntity.ok (updatePelicula);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*
     * ELIMINAR UNA PELICULA POR ID
     * @DeleteMapping("/{id}"): Maneja solicitudes HTTP DELETE 
     * en la ruta /peliculas/{id}.
     * @PathVariable Long id: Extrae el valor del parámetro de 
     * ruta y lo asigna al argumento id del método.
     * Método: deletePelicula(Long id).
     * Propósito: Eliminar una película específica por su ID.
     * Retorno: Devuelve un ResponseEntity<Void>:
     * Si la película existe, la elimina y devuelve un estado HTTP 204 (No Content).
     * Si la película no existe, devuelve un estado HTTP 404 (Not Found).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePelicula(@PathVariable Long id) {
        if (peliculaService.findById(id).isPresent()) {
            peliculaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
        
    }


            
}   

    


