package Entity;

public class alir {

    private int alir_id;
    private int ders_id;
    private Long alir_puani;
    private dersler dersler;
    private ogrenci ogrenci;

    public alir() {
    }

    public alir(int ders_id, Long alir_puani, int alir_id) {
        this.alir_id = alir_id;
        this.ders_id = ders_id;
        this.alir_puani = alir_puani;
    }

    @Override
    public String toString() {
        return "alir{" + "ders_id=" + ders_id + ",  alir_puani=" + alir_puani + '}';
    }

    public int getDers_id() {
        return ders_id;
    }

    public void setDers_id(int ders_id) {
        this.ders_id = ders_id;
    }

    

    public Long getAlir_puani() {
        
        return alir_puani;
    }

    public void setAlir_puani(Long alir_puani) {
        this.alir_puani = alir_puani;
    }

    public dersler getDersler() {
        return dersler;
    }

    public void setDersler(dersler dersler) {
        this.dersler = dersler;
    }

    public ogrenci getOgrenci() {
        if(this.ogrenci==null){
            this.ogrenci=new ogrenci();
        }
        return ogrenci;
    }

    public void setOgrenci(ogrenci ogrenci) {
        this.ogrenci = ogrenci;
    }

    public int getAlir_id() {
        return alir_id;
    }

    public void setAlir_id(int alir_id) {
        this.alir_id = alir_id;
    }

}
