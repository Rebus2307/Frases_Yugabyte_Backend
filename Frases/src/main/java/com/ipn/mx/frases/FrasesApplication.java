package com.ipn.mx.frases;

import com.ipn.mx.frases.application.FraseService;
import com.ipn.mx.frases.domain.entities.Frase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FrasesApplication implements CommandLineRunner {

    // Inyectamos el servicio para poder guardar en BD
    @Autowired
    private FraseService servicio;

    @Override
    public void run(String... args) throws Exception {
        // Validación para insertar solo si la tabla está vacía
        if (servicio.findAll().isEmpty()) {
            servicio.save(Frase.builder()
                    .texto("La seguridad es un proceso, no un producto.")
                    .autor("Bruce Schneier")
                    .build());

            servicio.save(Frase.builder()
                    .texto("El único sistema seguro es aquel que está apagado.")
                    .autor("Kevin Mitnick")
                    .build());

            servicio.save(Frase.builder()
                    .texto("En el Blue Team, el éxito es que no pase nada.")
                    .autor("Anónimo")
                    .build());

            System.out.println("¡Los 3 registros requeridos se insertaron correctamente!");
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(FrasesApplication.class, args);
    }
}