package src.model;

public class Wok {
    private Base base;
    private Ingredient[] ingredients;
    private Salsa salsa;
    private double preu;

    public Wok(Base base,Ingredient[] ingredients,Salsa salsa){
        this.base = base;
        this.ingredients = ingredients;
        this.salsa = salsa;
        calcularPreu();
    }

    private void calcularPreu(){
        double preu = this.base.getPreu();
        for( Ingredient ingredient: this.ingredients){
            preu += ingredient.getPreu();
        }
        preu += this.salsa.getPreu();
        this.preu = preu;
    }


    @Override
    public String toString(){
        StringBuilder result = new StringBuilder(this.base.toString() + "\n");
        for (Ingredient i : this.ingredients){
            result.append(i.toString()).append("\n");
        }
        result.append(this.salsa.toString()).append("\n");
        result.append("Preu Total: " + String.format("%.2f",preu) + "€");
        return result.toString();
    }


    public double getPreu(){
        return this.preu;
    }
}
