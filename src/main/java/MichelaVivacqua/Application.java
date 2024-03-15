package MichelaVivacqua;

import MichelaVivacqua.entities.ElementoBibliografico;
import MichelaVivacqua.entities.Libro;
import MichelaVivacqua.entities.Rivista;
import MichelaVivacqua.entities.TipoRivista;
import com.github.javafaker.Faker;
import java.io.*;
import java.util.*;
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
                long isbn = Long.parseLong(scanner.nextLine());

                System.out.print("Titolo: ");
                String titolo = scanner.nextLine();

                System.out.print("Anno di pubblicazione: ");
                int annoPubblicazione = Integer.parseInt(scanner.nextLine());

                System.out.print("Numero di pagine: ");
                long numeroPagine = Long.parseLong(scanner.nextLine());

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
                long isbn = Long.parseLong(scanner.nextLine());

                System.out.print("Titolo: ");
                String titolo = scanner.nextLine();

                System.out.print("Anno di pubblicazione: ");
                int annoPubblicazione = Integer.parseInt(scanner.nextLine());

                System.out.print("Numero di pagine: ");
                long numeroPagine = Long.parseLong(scanner.nextLine());

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
        long isbnToRemove = Long.parseLong(scanner.nextLine());

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
        long isbnToSearch = Long.parseLong(scanner.nextLine());

        Optional<ElementoBibliografico> foundElement = archivio.stream()
                .filter(elemento -> elemento.getIsbn() == isbnToSearch)
//                .limit(1)
                .findFirst();

        if (foundElement.isPresent()) {
            logger.info("Elemento trovato: " + foundElement.get());
        } else {
            logger.warn("Nessun elemento trovato con ISBN " + isbnToSearch);
        }


        // TASK4:Ricerca per anno di pubblicazione

        System.out.print("Inserisci l'anno di pubblicazione degli elementi da cercare: ");
        long yearToSearch = Long.parseLong(scanner.nextLine());

        List<ElementoBibliografico> foundElements = archivio.stream()
                .filter(elemento -> elemento.getAnnoPubblicazione() == yearToSearch)
                .toList();

        if (!foundElements.isEmpty()) {
            logger.info("Elementi trovati:");
            foundElements.forEach(elemento -> logger.info(elemento.toString()));
        } else {
            logger.warn("Nessun elemento trovato con anno di pubblicazione " + yearToSearch);
        }


//        TASK 5: Ricerca per autore

        System.out.print("Inserisci l'autore dell'elemento da cercare: ");
        String autoreToSearch = scanner.nextLine();

        List<Libro> foundBooksByAutore = archivio.stream()
                .filter(elemento -> elemento instanceof Libro)
                .map(elemento -> (Libro) elemento)
                .filter(libro -> libro.getAutore().equalsIgnoreCase(autoreToSearch))
                .toList();

        if (!foundBooksByAutore.isEmpty()) {
            logger.info("Libri trovati per l'autore " + autoreToSearch + ":");
            foundBooksByAutore.forEach(libro -> logger.info(libro.toString()));
        } else {
            logger.warn("Nessun libro trovato per l'autore " + autoreToSearch);
        }


        scanner.close();


//TASK 6: Salvataggio su disco dell'archivio

 File file = new File("archivio.txt");
System.out.println("Salvataggio dell'archivio su disco in corso...");
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (ElementoBibliografico elemento : archivio) {
                if (elemento instanceof Libro) {
                    Libro libro = (Libro) elemento;
                    writer.println("Libro;" + libro.getIsbn() + ";" + libro.getTitolo() + ";" + libro.getAnnoPubblicazione() + ";" + libro.getNumeroPagine() + ";" + libro.getAutore() + ";" + libro.getGenere());
                } else if (elemento instanceof Rivista) {
                    Rivista rivista = (Rivista) elemento;
                    writer.println("Rivista;" + rivista.getIsbn() + ";" + rivista.getTitolo() + ";" + rivista.getAnnoPubblicazione() + ";" + rivista.getNumeroPagine() + ";" + rivista.getTipoRivista());
                }
            }
            logger.info("Archivio salvato con successo su disco.");
        } catch (IOException e) {
            logger.error("Errore durante il salvataggio dell'archivio su disco: " + e.getMessage());
        }


//        TASK 7: Caricamento dal disco dell'archivio

        System.out.println("Caricamento dell'archivio dal disco in corso...");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            archivio.clear();
            System.out.println("GRAZIE. ECCO L'ARCHIVIO AGGIORNATO!");
            archivio.forEach(System.out::println);
            System.out.println("ARCHIVIO FORMATTATO!");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length < 2) {
                    logger.error("Formato non valido per la riga: " + line);
                    continue;
                }
                if (parts[0].equals("Libro")) {
                    // è un libro
                    long isbn = Long.parseLong(parts[1]);
                    String titolo = parts[2];
                    int annoPubblicazione = Integer.parseInt(parts[3]);
                    long numeroPagine = Long.parseLong(parts[4]);
                    String autore = parts[5];
                    String genere = parts[6];
                    Libro libro = new Libro(isbn, titolo, annoPubblicazione, numeroPagine, autore, genere);
                    archivio.add(libro);
                } else if (parts[0].equals("Rivista")) {
                    // è una rivista
                    long isbn = Long.parseLong(parts[1]);
                    String titolo = parts[2];
                    int annoPubblicazione = Integer.parseInt(parts[3]);
                    long numeroPagine = Long.parseLong(parts[4]);
                    TipoRivista tipoRivista = TipoRivista.valueOf(parts[5]);
                    Rivista rivista = new Rivista(isbn, titolo, annoPubblicazione, numeroPagine, tipoRivista);
                    archivio.add(rivista);
                } else {
                    logger.error("Tipo di elemento non riconosciuto per la riga: " + line);
                }
            }
            logger.info("Archivio caricato con successo dal disco.");
            System.out.println("GRAZIE. ECCO L'ARCHIVIO AGGIORNATO!");
            archivio.forEach(System.out::println);
        } catch (IOException | IllegalArgumentException e) {
            logger.error("Errore durante il caricamento dell'archivio dal disco: " + e.getMessage());
        }

    }
}
