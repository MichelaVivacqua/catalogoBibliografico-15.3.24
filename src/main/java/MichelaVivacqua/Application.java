package MichelaVivacqua;

import MichelaVivacqua.entities.Libro;
import MichelaVivacqua.entities.Rivista;
import MichelaVivacqua.entities.TipoRivista;
import com.github.javafaker.Faker;

import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    public static void main(String[] args){
        Faker faker = new Faker(Locale.ITALY);
        Logger logger = LoggerFactory.getLogger(Application.class);

        // CREO 10 LIBRI
        for (int i = 0; i < 10; i++) {
            long isbn = faker.number().numberBetween(2147483648L, 2247483648L);
            String titolo = faker.book().title();
            int annoPubblicazione = faker.number().numberBetween(1963, 2023);
            long numeroPagine = faker.number().numberBetween(50, 1000);
            String autore = faker.book().author();
            String genere = faker.book().genre();

            Libro libro = new Libro(isbn, titolo, annoPubblicazione, numeroPagine, autore, genere);
            System.out.println(libro);
        }


        // CREO 10 RIVISTE
        for (int i = 0; i < 10; i++) {
            long isbn = faker.number().randomNumber();
            String titolo = faker.book().title();
            int annoPubblicazione = faker.number().numberBetween(2000, 2023);
            long numeroPagine = faker.number().numberBetween(10, 100);
            TipoRivista tipoRivista = TipoRivista.values()[faker.number().numberBetween(0, TipoRivista.values().length)];

            Rivista rivista = new Rivista(isbn, titolo, annoPubblicazione, numeroPagine, tipoRivista);
            System.out.println(rivista);
        }


    }
}
