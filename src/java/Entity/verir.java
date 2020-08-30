
package Entity;


public class verir {
    private int verir_id;
    private String verir_donem;
    private dersler dersler;
    private ogretmen ogretmen;

    public verir() {
    }

    public verir(int verir_id, String verir_donem) {
        this.verir_id=verir_id;
        this.verir_donem = verir_donem;
    }

    @Override
    public String toString() {
        return "verir{" + "verir_id=" + verir_id + ", verir_donem=" + verir_donem + ", dersler=" + dersler + ", ogretmen=" + ogretmen + '}';
    }

   

    public String getVerir_donem() {
        return verir_donem;
    }

    public void setVerir_donem(String verir_donem) {
        this.verir_donem = verir_donem;
    }

    public dersler getDersler() {
        if(this.dersler==null){
            this.dersler=new dersler();
        }
        return dersler;
    }

    public void setDersler(dersler dersler) {
        this.dersler = dersler;
    }

    public ogretmen getOgretmen() {
        if(this.ogretmen==null){
            this.ogretmen=new ogretmen();
        }
        return ogretmen;
    }

    public void setOgretmen(ogretmen ogretmen) {
        this.ogretmen = ogretmen;
    }

    public int getVerir_id() {
        return verir_id;
    }

    public void setVerir_id(int verir_id) {
        this.verir_id = verir_id;
    }
    
    
    
    
}
