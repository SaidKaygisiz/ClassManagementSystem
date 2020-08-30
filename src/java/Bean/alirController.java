package Bean;

import Dao.alirDAO;
import Entity.alir;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("alr")
@SessionScoped
public class alirController implements Serializable {

    private alir alir;
    private List<alir> alirList;
    private alirDAO alirdao;
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
        return this.page <= (this.getAlirdao().alirSayisi() / ListİtemCount);
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
        this.alirList = this.getAlirdao().getList(page, ListİtemCount);
    }

    public void last() {
        page = 1 + (this.getAlirdao().alirSayisi() / ListİtemCount);
        ListİtemCount = 3;
        this.alirList = this.getAlirdao().getList(page, ListİtemCount);

    }

    public void Remove() {
        this.getAlirdao().Delete(this.alir);
        this.clearForm();
    }

    public void insert() {
        this.getAlirdao().Save(this.alir);
        this.clearForm();

    }

    public void clearForm() {
        this.alir = new alir();
    }

    public void updateForm(alir alir) {
        this.alir = alir;
    }

    public void updatee() {
        this.getAlirdao().update(this.alir);
        this.clearForm();
    }

    public alir getAlir() {
        if (this.alir == null) {
            this.alir = new alir();
        }
        return alir;
    }

    public void setAlir(alir alir) {
        this.alir = alir;
    }

    public List<alir> getAlirList() {
        this.alirList = this.getAlirdao().getList(page, ListİtemCount);
        return alirList;
    }

    public void setAlirList(List<alir> alirList) {
        this.alirList = alirList;
    }

    public alirDAO getAlirdao() {
        if (this.alirdao == null) {
            this.alirdao = new alirDAO();
        }
        return alirdao;
    }

    public void setAlirdao(alirDAO alirdao) {
        this.alirdao = alirdao;
    }

    public Long puanGetir(int a, int b) {
        return this.getAlirdao().puanGetir(a, b);
    }

   

}
