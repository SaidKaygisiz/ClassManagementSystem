
package Entity;


public class birimler {
    
    private int birim_id;
    private String fakulte;
    private String bolum;
    private ogretmen ogretmen;
    
    public birimler() {
  
    }

    public birimler(int birim_id, String fakulte, String bolum) {
        this.birim_id = birim_id;
        this.fakulte = fakulte;
        this.bolum = bolum;
    }

    @Override
    public String toString() {
        return "birimler{" + "birim_id=" + birim_id + ", fakulte=" + fakulte + ", bolum=" + bolum + '}';
    }

    public int getBirim_id() {
        return birim_id;
    }

    public void setBirim_id(int birim_id) {
        this.birim_id = birim_id;
    }


    public String getFakulte() {
        return fakulte;
    }

    public void setFakulte(String fakulte) {
        this.fakulte = fakulte;
    }

    public String getBolum() {
        return bolum;
    }

    public void setBolum(String bolum) {
        this.bolum = bolum;
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
    
    
    
    
}
