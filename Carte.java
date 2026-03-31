package ro.example.catalogcarti.model;

public class Carte {
    private int id;
    private String titlu;
    private String autor;
    private int an;
    private String gen;

    public Carte() {}
    public Carte(String titlu, String autor, int an, String gen) {
        this.titlu = titlu;
        this.autor = autor;
        this.an = an;
        this.gen = gen;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitlu() { return titlu; }
    public void setTitlu(String titlu) { this.titlu = titlu; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public int getAn() { return an; }
    public void setAn(int an) { this.an = an; }
    public String getGen() { return gen; }
    public void setGen(String gen) { this.gen = gen; }
}