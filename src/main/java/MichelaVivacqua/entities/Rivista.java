package MichelaVivacqua.entities;

public class Rivista extends ElementoBibliografico{

    private TipoRivista tipoRivista;
    public Rivista(long isbn, String titolo, int annoPubblicazione, long numeroPagine, TipoRivista tipoRivista) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.tipoRivista=tipoRivista;
    }

    public TipoRivista getTipoRivista() {
        return tipoRivista;
    }

    public void setTipoRivista(TipoRivista tipoRivista) {
        this.tipoRivista = tipoRivista;
    }
}
