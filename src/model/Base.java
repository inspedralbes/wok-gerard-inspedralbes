package src.model;

public class Base extends Producte {
    private String mida;

    public Base(String desc,double preu,String mida){
        super( desc, preu);
        this.mida = mida;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public String getMida() {
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
