package es.cic.curso.practica004;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso.practica004.model.Pelicula;
import es.cic.curso.practica004.repository.PeliculaRepository;
import jakarta.transaction.Transactional;

 /*
 * @SpringBootTest: Esta anotación le indica a Spring Boot que cargue el contexto 
 * de la aplicación completo para los tests de integración.
 * @AutoConfigureMockMvc: Configura automáticamente el objeto MockMvc que se usa 
 * para hacer solicitudes HTTP simuladas.
 * @Transactional: Indica que cada test debe ejecutarse dentro de una transacción, 
 * y al finalizar, cualquier cambio en la base de datos será revertido. Esto ayuda 
 * a mantener la base de datos en un estado limpio para cada prueba.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PeliculaControllerIntegrationTest {

    /*
    * @Autowired: Estas anotaciones indican a Spring que inyecte automáticamente las dependencias. 
    * Aquí se inyectan MockMvc, PeliculaRepository y ObjectMapper.
    * MockMvc: Se usa para simular solicitudes HTTP a la aplicación.
    * PeliculaRepository: Interfaz para interactuar con la base de datos.
    * ObjectMapper: Utilidad para convertir objetos Java a JSON y viceversa.
    * private Pelicula pelicula;: Variable que almacenará una instancia de Pelicula para usar en las pruebas. 
    */
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Pelicula pelicula;

    /*
     * @BeforeEach: Método que se ejecuta antes de cada test. 
     * Aquí se crea una nueva película y se guarda en la base de datos.
     */
    @BeforeEach
    public void setUp() {
        pelicula = new Pelicula();
        pelicula.setTitulo("Título 1");
        pelicula.setDirector("Director");
        pelicula.setAno(2024);
        pelicula.setGenero("Suspense");
        pelicula.setDisponible(true);
        pelicula = peliculaRepository.save(pelicula);
    }

    /*
     * @AfterEach: Método que se ejecuta después de cada test. 
     * Limpia la base de datos eliminando todas las películas.
     */
    @AfterEach
    public void tearDown() {
        peliculaRepository.deleteAll();
    }

    /*
     * TEST PARA OBTNER UNA PELICULA POR SU ID
     * mockMvc.perform(get("/peliculas/{id}", pelicula.getId()): 
     * Realiza una solicitud HTTP GET a la URL /peliculas/{id} con el ID de la película.
     * .contentType(MediaType.APPLICATION_JSON): 
     * Indica que el tipo de contenido de la solicitud es JSON.
     * .andExpect(status().isOk()): Espera que el estatus de la respuesta sea 200 OK.
     * .andExpect(jsonPath("$.titulo").value(pelicula.getTitulo())): Verifica que el 
     * campo titulo del JSON de respuesta tenga el valor Test Pelicula.
     * .andExpect(jsonPath("$.director").value(pelicula.getDirector())): Verifica que 
     * el campo director del JSON de respuesta tenga el valor Test Director.
     * .andExpect(jsonPath("$.ano").value(pelicula.getAno())): Verifica que el campo 
     * ano del JSON de respuesta tenga el valor 2024.
     * .andExpect(jsonPath("$.genero").value(pelicula.getGenero())): Verifica que el 
     * campo genero del JSON de respuesta tenga el valor Test Genero.
     * .andExpect(jsonPath("$.disponible").value(pelicula.isDisponible())): Verifica 
     * que el campo disponible del JSON de respuesta tenga el valor true.
     */
    
    @Test
    public void testGetPeliculaById() throws Exception {
        mockMvc.perform(get("/api/peliculas/{id}", pelicula.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value(pelicula.getTitulo()))
                .andExpect(jsonPath("$.director").value(pelicula.getDirector()))
                .andExpect(jsonPath("$.ano").value(pelicula.getAno()))
                .andExpect(jsonPath("$.genero").value(pelicula.getGenero()))
                .andExpect(jsonPath("$.disponible").value(pelicula.isDisponible()));
    }

    /*
     * TEST CREAR PELICULA
     * Pelicula nuevaPelicula = new Pelicula();: Crea una nueva instancia de Pelicula.
     * mockMvc.perform(post("/peliculas"): Realiza una solicitud HTTP POST a la URL /peliculas.     
     * .content(objectMapper.writeValueAsString(nuevaPelicula)): Convierte el objeto nuevaPelicula 
     * a JSON y lo incluye en la solicitud.
     * .andExpect(status().isCreated()): Espera que el estatus de la respuesta sea 201 Created.
     * .andExpect(jsonPath("$.titulo").value("New Pelicula")): Verifica que el campo titulo del 
     * JSON de respuesta tenga el valor New Pelicula.
     * .andExpect(jsonPath("$.director").value("New Director")): Verifica que el campo director 
     * del JSON de respuesta tenga el valor New Director.
     * .andExpect(jsonPath("$.ano").value(2022)): Verifica que el campo ano del JSON de respuesta 
     * tenga el valor 2024.
     * andExpect(jsonPath("$.genero").value("New Genero")): Verifica que el campo genero del JSON 
     * de respuesta tenga el valor New Genero.
     * .andExpect(jsonPath("$.disponible").value(false)): Verifica que el campo disponible del JSON 
     * de respuesta tenga el valor false.
     */
    @Test
    public void testCreatePelicula() throws Exception {
        Pelicula nuevaPelicula = new Pelicula();
        nuevaPelicula.setTitulo("New Pelicula");
        nuevaPelicula.setDirector("New Director");
        nuevaPelicula.setAno(2024);
        nuevaPelicula.setGenero("New Genero");
        nuevaPelicula.setDisponible(false);

        mockMvc.perform(post("/api/peliculas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevaPelicula)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("New Pelicula"))
                .andExpect(jsonPath("$.director").value("New Director"))
                .andExpect(jsonPath("$.ano").value(2024))
                .andExpect(jsonPath("$.genero").value("New Genero"))
                .andExpect(jsonPath("$.disponible").value(false));
    }


    /*
     * TEST ACTUALIZAR PELICULA
     * pelicula.setTitulo("Updated Title");: Actualiza el título de la película existente.
     * mockMvc.perform(put("/peliculas/{id}", pelicula.getId()): Realiza una solicitud HTTP PUT a la 
     * URL /peliculas/{id} con el ID de la película.
     * .content(objectMapper.writeValueAsString(pelicula)): Convierte el objeto pelicula actualizado 
     * a JSON y lo incluye en la solicitud.
     * .andExpect(status().isOk()): Espera que el estatus de la respuesta sea 200 OK.
     * .andExpect(jsonPath("$.titulo").value("Updated Title")): Verifica que el campo titulo del JSON 
     * de respuesta tenga el valor Updated Title.
     */
    @Test
    public void testUpdatePelicula() throws Exception {
        pelicula.setTitulo("Updated Title");

        mockMvc.perform(put("/api/peliculas/{id}", pelicula.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pelicula)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Updated Title"));
    }

    /*
     * TEST ELIMINAR UNA PELICULA
     * mockMvc.perform(delete("/peliculas/{id}", pelicula.getId()): Realiza una solicitud HTTP DELETE 
     * a la URL /peliculas/{id} con el ID de la película.
     * .andExpect(status().isNoContent()): Espera que el estatus de la respuesta sea 204 No Content.
     */
    @Test
    public void testDeletePelicula() throws Exception {
        mockMvc.perform(delete("/api/peliculas/{id}", pelicula.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    /*
     * Test para Obtener una Película por su ID que No Existe
     * mockMvc.perform(get("/peliculas/{id}", 999L): Realiza 
     * una solicitud HTTP GET a la URL /peliculas/{id} con un ID que no existe (999L).
     * .andExpect(status().isNotFound()): Espera que el estatus de la respuesta sea 404 Not Found.
     */
    @Test
    public void testGetPeliculaByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/peliculas/{id}", 999L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}