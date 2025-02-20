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
        if(this.mida == MidaBase.GRAN){
            return super.preu + Wok.getPreuMidaGran();
        }else {
            return super.preu;
        }
    }

    @Override
    public String toString() {
        String result = "Base: "  + descripcio + " - " + String.format("%.2f",preu) + "€"+
                "\nMida: " + mida;

        if(this.mida == MidaBase.GRAN) {
            result += String.format(" %.2f",Wok.getPreuMidaGran());
        }else{
            result += String.format(" %.2f",0.0);
        }
        return result;
    }
}
