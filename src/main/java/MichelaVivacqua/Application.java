package MichelaVivacqua;

import MichelaVivacqua.entities.ElementoBibliografico;
import MichelaVivacqua.entities.Libro;
import MichelaVivacqua.entities.Rivista;
import MichelaVivacqua.entities.TipoRivista;
import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    public static void main(String[] args){
        Faker faker = new Faker(Locale.ITALY);
        Logger logger = LoggerFactory.getLogger(Application.class);
        Scanner scanner = new Scanner(System.in);

        List<ElementoBibliografico> archivio = new ArrayList<>();

        // CREO 10 LIBRI
        for (int i = 0; i < 10; i++) {
            long isbn = faker.number().numberBetween(2147483648L, 2247483648L);
            String titolo = faker.book().title();
            int annoPubblicazione = faker.number().numberBetween(1963, 2023);
            long numeroPagine = faker.number().numberBetween(50, 1000);
            String autore = faker.book().author();
            String genere = faker.book().genre();

            Libro libro = new Libro(isbn, titolo, annoPubblicazione, numeroPagine, autore, genere);
            archivio.add(libro);
//            System.out.println(libro);
        }


        // CREO 10 RIVISTE
        for (int i = 0; i < 10; i++) {
            long isbn = faker.number().randomNumber();
            String titolo = faker.book().title();
            int annoPubblicazione = faker.number().numberBetween(2000, 2023);
            long numeroPagine = faker.number().numberBetween(10, 100);
            TipoRivista tipoRivista = TipoRivista.values()[faker.number().numberBetween(0, TipoRivista.values().length)];

            Rivista rivista = new Rivista(isbn, titolo, annoPubblicazione, numeroPagine, tipoRivista);
            archivio.add(rivista);
//            System.out.println(rivista);
        }
        System.out.println("ECCO L'ARCHIVIO ATTUALE!");
        for (ElementoBibliografico elemento : archivio) {
            System.out.println(elemento);
        }


        System.out.println("1.CREA UN LIBRO");
        System.out.println("2.CREA UNA RIVISTA");
        String scelta = scanner.nextLine();


        // TASK1:Aggiunta di un elemento

        if (scelta.equals("1")) {
            try {
                System.out.println("Inserisci i dettagli del libro da aggiungere:");

                System.out.print("ISBN: ");
                long isbn = scanner.nextLong();
                scanner.nextLine();

                System.out.print("Titolo: ");
                String titolo = scanner.nextLine();

                System.out.print("Anno di pubblicazione: ");
                int annoPubblicazione = scanner.nextInt();

                System.out.print("Numero di pagine: ");
                long numeroPagine = scanner.nextLong();
                scanner.nextLine();

                System.out.print("Autore: ");
                String autore = scanner.nextLine();

                System.out.print("Genere: ");
                String genere = scanner.nextLine();

                Libro nuovoLibro = new Libro(isbn, titolo, annoPubblicazione, numeroPagine, autore, genere);
                archivio.add(nuovoLibro);
                System.out.println("GRAZIE. ECCO L'ARCHIVIO AGGIORNATO!");
                archivio.forEach(System.out::println);
            } catch (InputMismatchException e) {
                logger.error("Non hai inserito il formato corretto!");
            }

        } else if (scelta.equals("2")) {
            try {
                System.out.println("Inserisci i dettagli della rivista da aggiungere:");

                System.out.print("ISBN: ");
                long isbn = scanner.nextLong();
                scanner.nextLine();

                System.out.print("Titolo: ");
                String titolo = scanner.nextLine();

                System.out.print("Anno di pubblicazione: ");
                int annoPubblicazione = scanner.nextInt();

                System.out.print("Numero di pagine: ");
                long numeroPagine = scanner.nextLong();
                scanner.nextLine();

                System.out.print("Tipo rivista (SETTIMANALE, MENSILE, SEMESTRALE): ");
                String tipoRivistaInput = scanner.nextLine();
                TipoRivista tipoRivista = TipoRivista.valueOf(tipoRivistaInput.toUpperCase());

                Rivista nuovaRivista = new Rivista(isbn, titolo, annoPubblicazione, numeroPagine, tipoRivista);
                archivio.add(nuovaRivista);
                System.out.println("GRAZIE. ECCO L'ARCHIVIO AGGIORNATO!");
                archivio.forEach(System.out::println);
            } catch (InputMismatchException e) {
                logger.error("Non hai inserito il formato corretto!");
            }
        } else {
            logger.error( scelta + " non è un'opzione valida!  ");
        }


// TASK2:Rimozione di un elemento dato un codice ISBN

        System.out.print("Inserisci il codice ISBN dell'elemento da rimuovere: ");
        long isbnToRemove = scanner.nextLong();

        boolean removed = archivio.removeIf(elemento -> elemento.getIsbn() == isbnToRemove);

        if (removed) {
            logger.info("Elemento con ISBN " + isbnToRemove + " rimosso con successo.");
            System.out.println("GRAZIE. ECCO L'ARCHIVIO AGGIORNATO!");
            archivio.forEach(System.out::println);;
        } else {
            logger.warn("Nessun elemento trovato con ISBN " + isbnToRemove);
        }


        // TASK3:Ricerca per ISBN

        System.out.print("Inserisci il codice ISBN dell'elemento da cercare: ");
        long isbnToSearch = scanner.nextLong();

        Optional<ElementoBibliografico> foundElement = archivio.stream()
                .filter(elemento -> elemento.getIsbn() == isbnToSearch)
//                .limit(1)
                .findFirst();

        if (foundElement.isPresent()) {
            logger.info("Elemento trovato: " + foundElement.get());
        } else {
            logger.warn("Nessun elemento trovato con ISBN " + isbnToSearch);
        }

        scanner.close();



    }
}
