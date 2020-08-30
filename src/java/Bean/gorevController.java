package Bean;

import Dao.gorevDAO;
import Entity.gorev;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

@Named("grv")
@SessionScoped
public class gorevController implements Serializable {
    

    private gorev gorev;
    private List<gorev> gorevListesi;
    private List<gorev> gorevListesiAll;
    private gorevDAO gorevdao;
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
    
    
    
     public boolean hasPrev() {
        return this.page > 1;
    }

    public boolean hasNext() {
        return this.page <= (this.getGorevdao().gorevSayisi() / ListİtemCount);
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
        this.gorevListesi=this.getGorevdao().getList(page, ListİtemCount);
    }

    public void last() {
        page = 1 + (this.getGorevdao().gorevSayisi() / ListİtemCount);
        ListİtemCount = 3;
        this.gorevListesi = this.getGorevdao().getList(page, ListİtemCount);

    }
    
    public void Remove() {
        this.getGorevdao().Delete(this.gorev);
        this.clearForm();
    }

    public void insert() {
        this.getGorevdao().Save(this.gorev);
        this.clearForm();
    }

    public void clearForm() {
        this.gorev=new gorev();
    }

    public void updateForm(gorev gorev) {
        this.gorev = gorev;
    }

    public void updatee() {
        this.getGorevdao().update(this.gorev);
        this.clearForm();
    }

    public gorevController() {
        this.gorevListesi = new ArrayList<>();
        gorevdao = new gorevDAO();
    }

    public gorev getGorev() {
        if (this.gorev == null) {
            this.gorev = new gorev();
        }
        return gorev;
    }

    public void setGorev(gorev gorev) {
        this.gorev = gorev;
    }

    public List<gorev> getGorevListesi() {
        this.gorevListesi = this.gorevdao.getList(page,ListİtemCount);
        return gorevListesi;
    }

    public void setGorevListesi(List<gorev> gorevListesi) {
        this.gorevListesi = gorevListesi;
    }

    public gorevDAO getGorevdao() {
        return gorevdao;
    }

    public void setGorevdao(gorevDAO gorevdao) {
        this.gorevdao = gorevdao;
    }

    public List<gorev> getGorevListesiAll() {
        this.gorevListesiAll=this.getGorevdao().getListAll();
        return gorevListesiAll;
    }

    public void setGorevListesiAll(List<gorev> gorevListesiAll) {
        this.gorevListesiAll = gorevListesiAll;
    }

    
}
