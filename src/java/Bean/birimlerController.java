package Bean;

import Dao.birimlerDAO;
import Entity.birimler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("birim")
@SessionScoped
public class birimlerController implements Serializable {

    private birimler birimler;
    private List<birimler> birimlerListesi;
    private birimlerDAO birimdao;
     private int page = 1;
    private int ListİtemCount = 5;

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
    
     public boolean hasPrev() {
        return this.page > 1;
    }

    public boolean hasNext() {
        return this.page <= (this.getBirimdao().birimSayisi() / ListİtemCount);
    }

    public void previous() {
        this.page--;
    }

    public void next() {
        this.page++;
    }

    public void first() {
        page = 1;
        ListİtemCount = 5;
        this.birimlerListesi=this.getBirimdao().getList(page, ListİtemCount);
    }

    public void last() {
        page = 1 + (this.getBirimdao().birimSayisi() / ListİtemCount);
        ListİtemCount = 5;
        this.birimlerListesi = this.getBirimdao().getList(page, ListİtemCount);

    }

    public void Remove() {
        this.getBirimdao().Delete(this.birimler);
        clearForm();
    }

    public void insert() {
        this.getBirimdao().Save(this.birimler);
        this.clearForm();
    }

    public void clearForm() {
        this.birimler = new birimler();
    }

    public void updateForm(birimler birimler) {
        this.birimler = birimler;
    }

    public void updatee() {
        this.getBirimdao().update(this.birimler);
        this.clearForm();
    }

    public birimlerController() {
        this.birimlerListesi = new ArrayList<>();
        birimdao = new birimlerDAO();
    }

    public birimler getBirimler() {
        if (this.birimler == null) {
            this.birimler = new birimler();
        }
        return birimler;
    }

    public void setBirimler(birimler birimler) {
        this.birimler = birimler;
    }

    public List<birimler> getBirimlerListesi() {
        this.birimlerListesi = this.birimdao.getList(page,ListİtemCount);
        return birimlerListesi;
    }

    public void setBirimlerListesi(List<birimler> birimlerListesi) {
        this.birimlerListesi = birimlerListesi;
    }

    public birimlerDAO getBirimdao() {
        return birimdao;
    }

    public void setBirimdao(birimlerDAO birimdao) {
        this.birimdao = birimdao;
    }

}
