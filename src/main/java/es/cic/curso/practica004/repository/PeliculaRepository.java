package es.cic.curso.practica004.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.cic.curso.practica004.model.Pelicula;

/*El repositorio es la capa que interactúa directamente con la
 base de datos. Usaremos Spring Data JPA para esto. Extiende 
 JpaRepository<Pelicula, Long>: Esto proporciona métodos CRUD 
 como save(), findAll(), findById(), deleteById(), etc., sin 
 necesidad de escribir código adicional.
Anotaciones: No necesita anotaciones adicionales, ya que 
hereda el comportamiento de JpaRepository.*/


/*/ El repositorio extiende JpaRepository, lo que le proporciona 
métodos CRUD básicos automáticamente. */
public interface PeliculaRepository extends JpaRepository <Pelicula, Long>{
}
