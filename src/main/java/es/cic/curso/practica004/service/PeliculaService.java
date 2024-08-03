package es.cic.curso.practica004.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso.practica004.exception.PeliculaNotFoundException;
import es.cic.curso.practica004.model.Pelicula;
import es.cic.curso.practica004.repository.PeliculaRepository;

/* Marca la clase como un servicio de Spring, lo que permite 
que Spring la gestione e inyecte en otras partes de la 
aplicación. */
@Service
public class PeliculaService {

    /*Inyección de Dependencias (@Autowired): Inyecta una 
    instancia del repositorio PeliculaRepository. */
    @Autowired
    private PeliculaRepository peliculaRepository;

    //Métodos CRUD

    //Método para obtener todas las películas 
    public List <Pelicula> findAll() {
        return peliculaRepository.findAll();
    }

     /*Método para obtener una película por su id, utilizamos 
     * Optional porque el resultado puede no existir
     * Metemos .orElseThrow para meter la excepción si el Optional está vacío
     * después de crear la clase exception
     * peliculaRepository.findById(id): Esto devuelve un Optional<Pelicula>,
     * por eso ahora hemos quitado el Optional
     */
    public Pelicula findById(Long id) {
        return peliculaRepository.findById(id)
        .orElseThrow (() -> new PeliculaNotFoundException("Pelicula no encontrada con id" + id));
    }
    

    //Método para guardar una nueva o actualizar una película
    public Pelicula save (Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    /*Método para eliminar una película por su id
      Utilizamos void para métodos que realizan una acción 
      pero no necesitan devolver un valor. Ideal para 
      operaciones de eliminación que no requieren un resultado
      Después de crear la clase de excepciones las metemos en el método*/
    public void deleteById(Long id) {
        if (!peliculaRepository.existsById(id)) {
            throw new PeliculaNotFoundException("Pelicula no encontrada con id" + id);
        }
        peliculaRepository.deleteById(id);
    }

}
