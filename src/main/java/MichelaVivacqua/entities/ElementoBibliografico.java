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


}
