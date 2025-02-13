package src;

public class Wok {
    private Base base;
    private Ingredient[] ingredinets;
    private Salsa salsa;
    private double preu;

    public Wok(Base base,Ingredient[] ingredinets,Salsa salsa){
        this.base = base;
        this.ingredinets = ingredinets;
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
