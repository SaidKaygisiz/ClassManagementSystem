package Entity;

import java.util.List;

public class dersler {

    private int ders_id;
    private String ders_ad;
    private Long akts;
    private List <ogrenci> ogrenciList;
    private alir alir;

    public alir getAlir() {
        return alir;
    }

    public void setAlir(alir alir) {
        this.alir = alir;
    }
    public dersler() {
    }

    public dersler(int ders_id, String ders_ad, Long akts) {
        this.ders_id = ders_id;
        this.ders_ad = ders_ad;
        this.akts = akts;
    }

    public int getDers_id() {
        return ders_id;
    }

    public void setDers_id(int ders_id) {
        this.ders_id = ders_id;
    }

    public String getDers_ad() {
        return ders_ad;
    }

    public void setDers_ad(String ders_ad) {
        this.ders_ad = ders_ad;
    }

    public Long getAkts() {
        return akts;
    }

    public void setAkts(Long akts) {
        this.akts = akts;
    }

    @Override
    public String toString() {
        return "dersler{" + "ders_id=" + ders_id + ", ders_ad=" + ders_ad + ", akts=" + akts + '}';
    }

    public List<ogrenci> getOgrenciList() {
        return ogrenciList;
    }

    public void setOgrenciList(List<ogrenci> ogrenciList) {
        this.ogrenciList = ogrenciList;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.ders_id;
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
        final dersler other = (dersler) obj;
        if (this.ders_id != other.ders_id) {
            return false;
        }
        return true;
    }

}
