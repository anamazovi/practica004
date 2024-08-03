package es.cic.curso.practica004.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    Optional porque el resultado puede no existir*/
    public Optional <Pelicula> findById(Long id) {
        return peliculaRepository.findById(id);
    }

    //Método para guardar una nueva o actualizar una película
    public Pelicula save (Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    /*Método para eliminar una película por su id
      Utilizamos void para métodos que realizan una acción 
      pero no necesitan devolver un valor. Ideal para 
      operaciones de eliminación que no requieren un resultado*/
    public void deleteById(Long id) {
        peliculaRepository.deleteById(id);
    }

}
