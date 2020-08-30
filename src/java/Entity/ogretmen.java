package Entity;

import java.util.List;

public class ogretmen {

    private int ogrt_id;
    private String ogrt_giris;
    private kullanici kul;
    private List<dokuman> ogretmenDokumans;

    public ogretmen() {
    }

    public ogretmen(int ogrt_id, String ogrt_giris) {
        this.ogrt_id = ogrt_id;
        this.ogrt_giris = ogrt_giris;
    }

    public int getOgrt_id() {
        return ogrt_id;
    }

    public void setOgrt_id(int ogrt_id) {
        this.ogrt_id = ogrt_id;
    }

    public String getOgrt_giris() {
        return ogrt_giris;
    }

    public void setOgrt_giris(String ogrt_giris) {
        this.ogrt_giris = ogrt_giris;
    }

   

    @Override
    public String toString() {
        return "ogretmen{" + "ogrt_id=" + ogrt_id + ", ogrt_giris=" + ogrt_giris + '}';
    }

    public List<dokuman> getOgretmenDokumans() {
        return ogretmenDokumans;
    }

    public void setOgretmenDokumans(List<dokuman> ogretmenDokumans) {
        this.ogretmenDokumans = ogretmenDokumans;
    }

    public kullanici getKul() {
        if(this.kul==null){
            this.kul=new kullanici();
        }
        return kul;
    }

    public void setKul(kullanici kul) {
        this.kul = kul;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.ogrt_id;
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
        final ogretmen other = (ogretmen) obj;
        if (this.ogrt_id != other.ogrt_id) {
            return false;
        }
        return true;
    }

    
    
    
    
}
