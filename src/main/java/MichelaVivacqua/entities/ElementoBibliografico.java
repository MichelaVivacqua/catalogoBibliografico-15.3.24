package MichelaVivacqua.entities;

public abstract class ElementoBibliografico {
    protected long isbn;
    protected String titolo;
    protected int annoPubblicazione;
    protected long numeroPagine;

    public ElementoBibliografico(long isbn, String titolo, int annoPubblicazione, long numeroPagine) {
        this.isbn = isbn;
        this.titolo=titolo;
        this.annoPubblicazione=annoPubblicazione;
        this.numeroPagine=numeroPagine;
    }

    @Override
    public String toString() {
        return "ElementoBibliografico{" +
                "isbn=" + isbn +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public long getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(long numeroPagine) {
        this.numeroPagine = numeroPagine;
    }
}
