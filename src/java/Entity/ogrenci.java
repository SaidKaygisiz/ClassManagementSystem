package Entity;

import java.util.List;

public class ogrenci {

    private int ogr_id;
    private Long sinif;
    private kullanici kul;
    private List<dersler> ogrenciDersler;
    private dersler ders;

    public dersler getDers() {
        return ders;
    }

    public void setDers(dersler ders) {
        this.ders = ders;
    }

    public ogrenci() {
    }

    public ogrenci(int ogr_id, Long sinif) {
        this.ogr_id = ogr_id;
        this.sinif = sinif;
    }

    @Override
    public String toString() {
        return "ogrenci{" + "ogr_id=" + ogr_id + ", sinif=" + sinif +  '}';
    }

    public int getOgr_id() {
        return ogr_id;
    }

    public void setOgr_id(int ogr_id) {
        this.ogr_id = ogr_id;
    }

    public Long getSinif() {
        return sinif;
    }

    public void setSinif(Long sinif) {
        this.sinif = sinif;
    }

   

    public kullanici getKul() {
        if (this.kul == null) {
            this.kul = new kullanici();
        }
        return kul;
    }

    public void setKul(kullanici kul) {
        this.kul = kul;
    }

    public List<dersler> getOgrenciDersler() {
        return ogrenciDersler;
    }

    public void setOgrenciDersler(List<dersler> ogrenciDersler) {
        this.ogrenciDersler = ogrenciDersler;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.ogr_id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ogrenci other = (ogrenci) obj;
        if (this.ogr_id != other.ogr_id) {
            return false;
        }
        return true;
    }

}
