package src;

public class Base {
    private String descripcio;
    private double preu;
    private String mida;

    public Base(String desc,double preu,String mida){
        this.descripcio = desc;
        this.preu = preu;
        this.mida = mida;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public String getMida() {
        return mida;
    }

    public double getPreu() {
        return preu;
    }
}
