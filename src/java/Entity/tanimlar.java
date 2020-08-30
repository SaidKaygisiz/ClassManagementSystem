package Entity;

public class tanimlar {

    private int tanimlar_id;
    private String ekleme_tarih;
    private gorev gorev;
    private ogretmen ogretmen;

    public tanimlar() {
    }

    public tanimlar(int tanimlar_id, String ekleme_tarih) {
        this.tanimlar_id = tanimlar_id;
        this.ekleme_tarih = ekleme_tarih;
    }

    @Override
    public String toString() {
        return "tanimlar{" + "tanimlar_id=" + tanimlar_id + ", ekleme_tarih=" + ekleme_tarih + ", gorev=" + gorev + ", ogretmen=" + ogretmen + '}';
    }

    public String getEkleme_tarih() {
        return ekleme_tarih;
    }

    public void setEkleme_tarih(String ekleme_tarih) {
        this.ekleme_tarih = ekleme_tarih;
    }

    public gorev getGorev() {
        if(this.gorev==null){
            this.gorev=new gorev();
        }
        return gorev;
    }

    public void setGorev(gorev gorev) {
        this.gorev = gorev;
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

    public int getTanimlar_id() {
        return tanimlar_id;
    }

    public void setTanimlar_id(int tanimlar_id) {
        this.tanimlar_id = tanimlar_id;
    }

}
