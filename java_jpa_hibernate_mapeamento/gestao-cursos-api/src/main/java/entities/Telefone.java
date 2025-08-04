package entities;

public class Telefone {
    
    private int id;
    private int DDD;
    private long numero;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getDDD() {
        return DDD;
    }
    public void setDDD(int dDD) {
        DDD = dDD;
    }
    public long getNumero() {
        return numero;
    }
    public void setNumero(long numero) {
        this.numero = numero;
    }
}
