package MichelaVivacqua.entities;


import com.github.javafaker.Faker;

public class Libro extends ElementoBibliografico  {

    private String autore;
    private String genere;

    public String getAutore() {
        return autore;
    }

    public String getGenere() {
        return genere;
    }

    public Libro(long isbn, String titolo, int annoPubblicazione, long numeroPagine, String autore, String genere) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.autore=autore;
        this.genere=genere;


    }

    @Override
    public String toString() {
        return "Libro{" +
                "isbn=" + isbn +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                ", autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                '}';
    }


}
