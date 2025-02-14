package src.model;

public class Ingredient extends Producte{

    public Ingredient(String desc,double preu){
        super( desc, preu);
    }


    @Override
    public String toString() {
        return  "Ingredient: " +descripcio + " - "+   String.format("%.2f",preu) + "€";
    }
}
