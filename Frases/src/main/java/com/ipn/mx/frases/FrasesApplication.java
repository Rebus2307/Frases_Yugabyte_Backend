package com.ipn.mx.frases;

import com.ipn.mx.frases.domain.entities.Frase;
import com.ipn.mx.frases.domain.repository.FraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.Builder;

@SpringBootApplication
public class FrasesApplication implements CommandLineRunner {
    
    @Override
    public void run(String... args) throws Exception {
        Frase f = new Frase();
//      f.setId(1L); Es autonumerica, no lo ponemos
        //f.setId(1L);
//        f.setAutor("Uzumaki Naruto");
//        f.setTexto("No vivas con falsedades ni miedos, porque terminaras odiandote a ti mismo");

//        dao.save(f); // el save hace un save or update. Sirve para crear o actualizar
        //dao.deleteById(f.getId());
//
//        System.out.println(dao.findById(1L));
//        System.out.println(dao.findAll());

//        System.out.println(f);

//        Frase f2 = Frase.builder()
//                .id(2L)
//                .texto("Ya me quiero ir")
//                .autor("Yo")
//                .build();

//        System.out.println(f2);
    }

    public static void main(String[] args) {
        SpringApplication.run(FrasesApplication.class, args);
    }

}
