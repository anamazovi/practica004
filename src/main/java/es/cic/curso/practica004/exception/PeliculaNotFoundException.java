package es.cic.curso.practica004.exception;


public class PeliculaNotFoundException extends RuntimeException {

         /*
         * Constructor sin parámetros:constructor vacío que llama al 
         * constructor vacío de RuntimeException.
         */
        public PeliculaNotFoundException() {
            super();
        }

        /*
        * Constructor que acepta un mensaje de error, que será mostrado 
        * cuando la excepción sea lanzada.
        */
        
        public PeliculaNotFoundException(String mensaje) {
            super(mensaje);
        }
    
        /*
         * Constructor que acepta un mensaje de error y una causa 
         * (otra excepción) para proporcionar más contexto sobre el error.
         */
        public PeliculaNotFoundException(String mensaje, Throwable causa) {
            super(mensaje, causa);
        }
    }
    