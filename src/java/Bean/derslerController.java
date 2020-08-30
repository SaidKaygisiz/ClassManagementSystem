package Bean;

import Dao.derslerDAO;
import Dao.ogrenciDAO;
import Entity.dersler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;


import javax.inject.Named;

@Named("ders")
@SessionScoped
public class derslerController implements Serializable {

    private dersler dersler;
    private List<dersler> dersListesi;
    private List<dersler> dersListesiAll;
    private derslerDAO dersdao;
    private ogrenciDAO ogrdao;
    private List<Integer> selectedOgrenciler;
    private int page = 1;
    private int ListİtemCount = 3;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getListİtemCount() {
        return ListİtemCount;
    }

    public void setListİtemCount(int ListİtemCount) {
        this.ListİtemCount = ListİtemCount;
    }

    public List<dersler> getDersListesiAll() {
        this.dersListesiAll=this.getDersdao().getListAll();
        return dersListesiAll;
    }

    public void setDersListesiAll(List<dersler> dersListesiAll) {
        this.dersListesiAll = dersListesiAll;
    }
    
    
     public boolean hasPrev() {
        return this.page > 1;
    }

    public boolean hasNext() {
        return this.page <= (this.getDersdao().dersSayisi() / ListİtemCount);
    }

    public void previous() {
        this.page--;
    }

    public void next() {
        this.page++;
    }

    public void first() {
        page = 1;
        ListİtemCount = 2;
        this.dersListesi=this.getDersdao().getList(page, ListİtemCount);
    }

    public void last() {
        page = 1 + (this.getDersdao().dersSayisi() / ListİtemCount);
        ListİtemCount = 2;
        this.dersListesi = this.getDersdao().getList(page, ListİtemCount);

    }

    public void remove() {
        this.getDersdao().delete(this.dersler);
        clearForm();
    }

    public void insert() {
        this.getDersdao().Save(this.dersler);
        this.clearForm();
    }

    public void updatee() {
        this.getDersdao().update(this.dersler);
        this.clearForm();
    }

    public derslerController() {
        this.dersListesi = new ArrayList<>();
        dersdao = new derslerDAO();

    }

    public dersler getDersler() {
        if (this.dersler == null) {
            this.dersler = new dersler();
        }
        return dersler;
    }

    public void setDersler(dersler dersler) {
        this.dersler = dersler;
    }

    public List<dersler> getDersListesi() {
        this.dersListesi = this.dersdao.getList(page,ListİtemCount);
        return dersListesi;
    }

    public void setDersListesi(List<dersler> dersListesi) {
        this.dersListesi = dersListesi;
    }

    public derslerDAO getDersdao() {
        if (this.dersdao == null) {
            this.dersdao = new derslerDAO();
        }
        return dersdao;
    }

    public void setDersdao(derslerDAO dersdao) {
        this.dersdao = dersdao;
    }

    public void clearForm() {
        this.dersler = new dersler();
        this.setSelectedOgrenciler(null);
    }

    public void updateForm(dersler dersler) {
        this.dersler = dersler;
        this.selectedOgrenciler = new ArrayList<>();
        this.dersler.getOgrenciList().forEach((d) -> {
            this.selectedOgrenciler.add(d.getOgr_id());
        });
    }
    public void confirm(dersler ders){
        this.dersler=ders;
    }

    public ogrenciDAO getOgrdao() {
        if (this.ogrdao == null) {
            this.ogrdao = new ogrenciDAO();
        }
        return ogrdao;
    }

    public void setOgrdao(ogrenciDAO ogrdao) {
        this.ogrdao = ogrdao;
    }

    public List<Integer> getSelectedOgrenciler() {
        return selectedOgrenciler;
    }

    public void setSelectedOgrenciler(List<Integer> selectedOgrenciler) {
        this.selectedOgrenciler = selectedOgrenciler;
    }

}
