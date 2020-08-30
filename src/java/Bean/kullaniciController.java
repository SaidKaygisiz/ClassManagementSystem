package Bean;

import Dao.kullaniciDAO;
import Entity.kullanici;
import Entity.ogrenci;
import Entity.ogretmen;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("kul")
@SessionScoped
public class kullaniciController implements Serializable {

    private int page = 1;
    private int ListİtemCount = 3;
    private kullanici kullanici;
    private List<kullanici> kullanicilist;
    private kullaniciDAO kdao;
    private ogrenci ogr;
    private ogretmen ogrt;

    public ogretmen getOgrt() {
        if (this.ogrt == null) {
            this.ogrt = new ogretmen();
        }
        return ogrt;
    }

    public void setOgrt(ogretmen ogrt) {
        this.ogrt = ogrt;
    }

    public ogrenci getOgr() {
        if (this.ogr == null) {
            this.ogr = new ogrenci();
        }
        return ogr;
    }

    public void setOgr(ogrenci ogr) {
        this.ogr = ogr;
    }

    public int getPage() {
        return page;
    }

    public int getListİtemCount() {
        return ListİtemCount;
    }

    public void setListİtemCount(int ListİtemCount) {
        this.ListİtemCount = ListİtemCount;
    }

    public boolean hasPrev() {
        return this.page > 1;
    }

    public boolean hasNext() {
        return this.page <= (this.getKdao().kullaniciSayisi() / ListİtemCount);
    }

    public void previous() {
        this.page--;
    }

    public void next() {
        this.page++;
    }

    public void first() {
        page = 1;
        ListİtemCount = 3;
        this.kullanicilist = this.getKdao().getList(page, ListİtemCount);
    }

    public void last() {
        page = 1 + (this.getKdao().kullaniciSayisi() / ListİtemCount);
        ListİtemCount = 3;
        this.kullanicilist = this.getKdao().getList(page, ListİtemCount);

    }

    public kullaniciController() {
        this.kullanicilist = new ArrayList<>();
        kdao = new kullaniciDAO();
    }

    public kullanici getKullanici() {
        if (this.kullanici == null) {
            this.kullanici = new kullanici();
        }
        return kullanici;
    }

    public void setKullanici(kullanici kullanici) {
        this.kullanici = kullanici;
    }

    public List<kullanici> getKullanicilist() {
        this.kullanicilist = this.kdao.getList(page, ListİtemCount);
        return kullanicilist;
    }

    public void setKullanicilist(List<kullanici> kullanicilist) {
        this.kullanicilist = kullanicilist;
    }

    public kullaniciDAO getKdao() {
        if (this.kdao == null) {
            this.kdao = new kullaniciDAO();
        }
        return kdao;
    }

    public void setKdao(kullaniciDAO kdao) {
        this.kdao = kdao;
    }

    public void delete() {
        this.getKdao().Delete(this.kullanici);
        this.clearForm();
    }

    public void insert() {
        try {

            this.getKdao().Save(this.kullanici);

        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
        this.clearForm();
    }

    public String insert2() {
        try {

            this.getKdao().Save2(this.kullanici);

        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
        this.clearForm();
        return "/index.xhtml?faces-redirect=true";
    }

    public void clearForm() {
        this.kullanici = new kullanici();
        this.ogr = new ogrenci();
        this.ogrt = new ogretmen();
    }

    public void updateForm(kullanici kul) {
        this.kullanici = kul;
    }

    public void updatee() {
        this.getKdao().update(this.kullanici);
        this.clearForm();
    }

   

    public String girisKontrol() {

        if (this.getKdao().varMi(this.kullanici.getEmail(), this.kullanici.getSifre())==1) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("valid_admin", this.kullanici);
            return "admin/adminPaneli.xhtml?faces-redirect=true";
        }
        else if (this.getKdao().varMi(this.kullanici.getEmail(), this.kullanici.getSifre())==2) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("valid_admin", this.kullanici);
            return "ogretmen/ogretmenPaneli.xhtml?faces-redirect=true";
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hatalı Giriş!!"));
            return "dogrulama.xhtml?faces-redirect=true";
        }
    }


    public String logOut() {
        this.kullanici = new kullanici();
        return "/index.xhtml?faces-redirect=true";
    }

}
