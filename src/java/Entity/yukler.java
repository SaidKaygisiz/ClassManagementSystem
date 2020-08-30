package Entity;

public class yukler {
    private int yukler_id;
    private int ogrt_id;
    private int ders_id;
    private int dok_id;
    private String ekleme_tarih;
    private dersler ders;
    private ogretmen ogrt;
    private dokuman dokuman;

    public yukler() {
    }

    public yukler(int yukler_id, int ogrt_id, int ders_id, int dok_id, String ekleme_tarih, dersler ders, ogretmen ogrt, dokuman dokuman) {
        this.yukler_id = yukler_id;
        this.ogrt_id = ogrt_id;
        this.ders_id = ders_id;
        this.dok_id = dok_id;
        this.ekleme_tarih = ekleme_tarih;
        this.ders = ders;
        this.ogrt = ogrt;
        this.dokuman = dokuman;
    }

   

    @Override
    public String toString() {
        return "yukler{" + "ogrt_id=" + ogrt_id + ", ders_id=" + ders_id + ", dok_id=" + dok_id + ", ekleme_tarih=" + ekleme_tarih + '}';
    }

    public int getOgrt_id() {
        return ogrt_id;
    }

    public void setOgrt_id(int ogrt_id) {
        this.ogrt_id = ogrt_id;
    }

    public int getDers_id() {
        return ders_id;
    }

    public void setDers_id(int ders_id) {
        this.ders_id = ders_id;
    }

    public int getDok_id() {
        return dok_id;
    }

    public void setDok_id(int dok_id) {
        this.dok_id = dok_id;
    }

    public String getEkleme_tarih() {
        return ekleme_tarih;
    }

    public void setEkleme_tarih(String ekleme_tarih) {
        this.ekleme_tarih = ekleme_tarih;
    }

    public dersler getDers() {
        return ders;
    }

    public void setDers(dersler ders) {
        this.ders = ders;
    }

    public ogretmen getOgrt() {
        return ogrt;
    }

    public void setOgrt(ogretmen ogrt) {
        this.ogrt = ogrt;
    }

    public dokuman getDokuman() {
        return dokuman;
    }

    public void setDokuman(dokuman dokuman) {
        this.dokuman = dokuman;
    }

    public int getYukler_id() {
        return yukler_id;
    }

    public void setYukler_id(int yukler_id) {
        this.yukler_id = yukler_id;
    }

}
