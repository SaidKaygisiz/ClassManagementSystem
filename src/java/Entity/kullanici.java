package Entity;


public class kullanici {

    private int k_id;
    private String ad;
    private String soyad;
    private String email;
    private String sifre;
    private String tel_no;
    private grup grup;
    private ogrenci ogrenci;
    private int grup_id;

    public int getGrup_id() {
        return grup_id;
    }

    public void setGrup_id(int grup_id) {
        this.grup_id = grup_id;
    }

    public kullanici() {
    }

    public kullanici(int k_id, String ad, String soyad, String email, String sifre, String tel_no, grup grup, ogrenci ogrenci, int grup_id) {
        this.k_id = k_id;
        this.ad = ad;
        this.soyad = soyad;
        this.email = email;
        this.sifre = sifre;
        this.tel_no = tel_no;
        this.grup = grup;
        this.ogrenci = ogrenci;
        this.grup_id = grup_id;
    }

    

    public int getK_id() {
        return k_id;
    }

    public void setK_id(int k_id) {
        this.k_id = k_id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getTel_no() {
        return tel_no;
    }

    public void setTel_no(String tel_no) {
        this.tel_no = tel_no;
    }

    public grup getGrup() {
        if (this.grup == null) {
            this.grup = new grup();
        }
        return grup;
    }

    public void setGrup(grup grup) {
        this.grup = grup;
    }

    public ogrenci getOgrenci() {
        if (this.ogrenci == null) {
            this.ogrenci = new ogrenci();
        }
        return ogrenci;
    }

    public void setOgrenci(ogrenci ogrenci) {
        this.ogrenci = ogrenci;
    }

    @Override
    public String toString() {
        return "kullanici{" + "k_id=" + k_id + ", ad=" + ad + ", soyad=" + soyad + ", email=" + email + ", sifre=" + sifre + ", tel_no=" + tel_no + '}';
    }
    
    

}
