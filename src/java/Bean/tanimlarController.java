package Bean;

import Dao.tanimlarDAO;
import Entity.tanimlar;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("tnmlr")
@SessionScoped
public class tanimlarController implements Serializable {

    private tanimlar tanimlar;
    private List<tanimlar> tanimlarList;
    private tanimlarDAO tanimlardao;
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
        return this.page <= (this.getTanimlardao().tanimlarSayisi() / ListİtemCount);
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
        this.tanimlarList=this.getTanimlardao().getList(page, ListİtemCount);
    }

    public void last() {
        page = 1 + (this.getTanimlardao().tanimlarSayisi() / ListİtemCount);
        ListİtemCount = 5;
        this.tanimlarList = this.getTanimlardao().getList(page, ListİtemCount);

    }

    public tanimlar getTanimlar() {
        if (this.tanimlar == null) {
            this.tanimlar = new tanimlar();
        }
        return tanimlar;
    }

    public void setTanimlar(tanimlar tanimlar) {
        this.tanimlar = tanimlar;
    }

    public List<tanimlar> getTanimlarList() {
        this.tanimlarList = this.getTanimlardao().getList(page,ListİtemCount);
        return tanimlarList;
    }

    public void setTanimlarList(List<tanimlar> tanimlarList) {
        this.tanimlarList = tanimlarList;
    }

    public tanimlarDAO getTanimlardao() {
        if (this.tanimlardao == null) {
            this.tanimlardao = new tanimlarDAO();
        }
        return tanimlardao;
    }

    public void setTanimlardao(tanimlarDAO tanimlardao) {
        this.tanimlardao = tanimlardao;
    }

    public void Remove() {
        this.getTanimlardao().Delete(this.tanimlar);
        this.clearForm();
    }

    public void insert() {
        this.getTanimlardao().Save(this.tanimlar);
        this.clearForm();
    }

    public void clearForm() {
        this.tanimlar=new tanimlar();
    }

    public void updateForm(tanimlar tanimlar) {
        this.tanimlar = tanimlar;
    }

    public void updatee() {
        this.getTanimlardao().update(this.tanimlar);
        this.clearForm();
    }

}
