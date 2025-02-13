package src;

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
        //TODO: donar valor al atribut preu
    }

    public double getPreu(){
        return this.preu;
    }
}
