package src.model;

public class Salsa extends Producte {

    public Salsa(String desc,double preu){
        super( desc, preu);
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
