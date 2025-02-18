package src.model;

public class Base extends Producte {
    private MidaBase mida;

    public Base(String desc,double preu,MidaBase mida){
        super( desc, preu);
        this.mida = mida;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public MidaBase getMida() {
        return this.mida;
    }

    public double getPreu() {
        return super.preu;
    }

    @Override
    public String toString() {
        return "Base: "  + descripcio + " - " + String.format("%.2f",preu) + "€"+
                "\nMida: " + mida;
    }
}
