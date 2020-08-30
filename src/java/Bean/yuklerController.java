package Bean;

import Dao.yuklerDAO;
import Entity.yukler;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

@Named("yukler")
@SessionScoped
public class yuklerController implements Serializable {

    private yukler yukler;
    private List<yukler> yuklerList;
    private yuklerDAO yuklerdao;
    private int page = 1;
    private int ListİtemCount = 3;

    public int getPage() {
        return page;
    }

    public boolean hasPrev() {
        return this.page > 1;
    }

    public boolean hasNext() {
        return this.page <= (this.getYuklerdao().yuklerSayisi() / ListİtemCount);
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
        this.yuklerList = this.getYuklerdao().getList(page, ListİtemCount);
    }

    public void last() {
        page = 1 + (this.getYuklerdao().yuklerSayisi() / ListİtemCount);
        ListİtemCount = 3;
        this.yuklerList = this.getYuklerdao().getList(page, ListİtemCount);

    }

    public yukler getYukler() {
        if (this.yukler == null) {
            this.yukler = new yukler();
        }
        return yukler;
    }

    public void setYukler(yukler yukler) {
        this.yukler = yukler;
    }

    public List<yukler> getYuklerList() {
        this.yuklerList = this.getYuklerdao().getList(page, ListİtemCount);
        return yuklerList;
    }

    public void setYuklerList(List<yukler> yuklerList) {
        this.yuklerList = yuklerList;
    }

    public yuklerDAO getYuklerdao() {
        if (this.yuklerdao == null) {
            this.yuklerdao = new yuklerDAO();
        }
        return yuklerdao;
    }

    public void setYuklerdao(yuklerDAO yuklerdao) {
        this.yuklerdao = yuklerdao;
    }

    public void Remove() {
        this.getYuklerdao().Delete(this.yukler);
        this.clearForm();
    }

    public void insert() {
        this.getYuklerdao().Save(this.yukler);
        this.clearForm();
    }

    public void clearForm() {
        this.yukler = new yukler();
    }

    public void updateForm(yukler yukler) {
        this.yukler = yukler;
    }

    public void updatee() {
        this.getYuklerdao().update(this.yukler);
        this.clearForm();
    }

}
