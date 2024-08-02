package es.cic.curso.practica004.model;

public class Pelicula {

    private String titulo;
    private String director;
    private int ano;
    private String genero;
    private boolean disponible;

    

    public Pelicula() {
    }

    public Pelicula(String titulo, String director, int ano, String genero, boolean disponible) {
        this.titulo = titulo;
        this.director = director;
        this.ano = ano;
        this.genero = genero;
        this.disponible = disponible;
    }

    //Getter y Setter

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
