package src.model;

public class Salsa {
    private String descripcio;
    private double preu;

    public Salsa(String desc,double preu){
        this.descripcio = desc;
        this.preu = preu;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public double getPreu() {
        return preu;
    }

    @Override
    public String toString() {
        return  "Salsa: " + descripcio + " - " + String.format("%.2f",preu) + "€";
    }
}
