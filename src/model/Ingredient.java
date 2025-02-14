package src.model;

public class Ingredient {
    private String descripcio;
    private double preu;

    public Ingredient(String descripcio,double preu){
        this.descripcio = descripcio;
        this.preu = preu;
    }

    public String getDescripcio(){
        return this.descripcio;
    }

    public double getPreu(){
        return this.preu;
    }

    @Override
    public String toString() {
        return  "Ingredient: " +descripcio + " - "+   String.format("%.2f",preu) + "€";
    }
}
