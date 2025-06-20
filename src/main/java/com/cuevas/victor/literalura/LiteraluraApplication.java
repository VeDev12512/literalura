package com.cuevas.victor.literalura;

import com.cuevas.victor.literalura.consola.MenuConsola;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    @Autowired
    private MenuConsola menu;

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) {
        menu.mostrarMenu(); // Inicia el menú en consola
    }
}

//package com.cuevas.victor.literalura;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class LiteraluraApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(LiteraluraApplication.class, args);
//    }
//
//}
