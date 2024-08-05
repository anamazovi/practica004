package es.cic.curso.practica004.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/*
 * Esta anotación marca la clase Pelicula como una entidad JPA. 
 * Esto significa que esta clase se mapeará a una tabla en la 
 * base de datos con el mismo nombre (por defecto).
 */
@Entity
public class Pelicula {

    /*
     * @Id: Marca el campo id como la clave primaria de la entidad. Cada película tendrá un identificador único.
     * @GeneratedValue(strategy = GenerationType.AUTO): Especifica que el proveedor de persistencia elegirá la 
     * estrategia para generar el valor del id. GenerationType.AUTO permite a JPA decidir la mejor estrategia 
     * según la base de datos (por ejemplo, secuencias, auto-incrementos, etc.).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titulo;
    private String director;
    private int ano;
    private String genero;
    private boolean disponible;

    

    public Pelicula() {
    }


    public Pelicula(Long id, String titulo, String director, int ano, String genero, boolean disponible) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.ano = ano;
        this.genero = genero;
        this.disponible = disponible;
    }


    //Getters y Setters

   

    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }

    
    public String getTitulo() {
        return titulo;
    }



    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }



    public String getDirector() {
        return director;
    }



    public void setDirector(String director) {
        this.director = director;
    }



    public int getAno() {
        return ano;
    }



    public void setAno(int ano) {
        this.ano = ano;
    }



    public String getGenero() {
        return genero;
    }



    public void setGenero(String genero) {
        this.genero = genero;
    }



    public boolean isDisponible() {
        return disponible;
    }



    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

}