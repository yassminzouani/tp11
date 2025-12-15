package com.example.tp11;

import com.example.tp11.entities.Client;
import com.example.tp11.entities.Compte;
import com.example.tp11.entities.TypeCompte;
import com.example.tp11.repositories.ClientRepository;
import com.example.tp11.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;

@SpringBootApplication
public class Tp11Application {

    public static void main(String[] args) {
        SpringApplication.run(Tp11Application.class, args);
    }

    @Bean
    CommandLineRunner start(
            CompteRepository compteRepository,
            ClientRepository clientRepository,
            RepositoryRestConfiguration restConfiguration) {

        return args -> {

            // 🔹 Exposer les IDs dans les réponses JSON
            restConfiguration.exposeIdsFor(Compte.class, Client.class);

            // 🔹 Création des clients (CONSTRUCTEUR CORRECT)
            Client c1 = clientRepository.save(
                    new Client(null, "Amal", null, null));

            Client c2 = clientRepository.save(
                    new Client(null, "Ali", null, null));

            // 🔹 Création des comptes
            compteRepository.save(
                    new Compte(null, Math.random() * 9000, new Date(), TypeCompte.EPARGNE, c1));

            compteRepository.save(
                    new Compte(null, Math.random() * 9000, new Date(), TypeCompte.COURANT, c1));

            compteRepository.save(
                    new Compte(null, Math.random() * 9000, new Date(), TypeCompte.EPARGNE, c2));

            // 🔹 Affichage console
            compteRepository.findAll().forEach(System.out::println);
        };
    }
}
