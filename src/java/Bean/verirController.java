package Bean;

import Dao.verirDAO;
import Entity.verir;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

@Named("vrr")
@SessionScoped
public class verirController implements Serializable {
    
    private verir verir;
    private List<verir> verirList;
    private verirDAO verirdao;
     private int page = 1;
    private int ListİtemCount = 5;

    public int getPage() {
        return page;
    }
    
     public boolean hasPrev() {
        return this.page > 1;
    }

    public boolean hasNext() {
        return this.page <= (this.getVerirdao().verirSayisi() / ListİtemCount);
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
        this.verirList=this.getVerirdao().getList(page, ListİtemCount);
    }

    public void last() {
        page = 1 + (this.getVerirdao().verirSayisi() / ListİtemCount);
        ListİtemCount = 5;
        this.verirList = this.getVerirdao().getList(page, ListİtemCount);

    }

    public verir getVerir() {
        if (this.verir == null) {
            this.verir = new verir();
        }
        return verir;
    }

    public void setVerir(verir verir) {
        this.verir = verir;
    }

    public List<verir> getVerirList() {
        this.verirList = this.getVerirdao().getList(page, ListİtemCount);
        return verirList;
    }

    public void setVerirList(List<verir> verirList) {
        this.verirList = verirList;
    }

    public verirDAO getVerirdao() {
        if (this.verirdao == null) {
            this.verirdao = new verirDAO();
        }
        return verirdao;
    }

    public void setVerirdao(verirDAO verirdao) {
        this.verirdao = verirdao;
    }
    
    public void Remove() {
        this.getVerirdao().Delete(this.verir);
        this.clearForm();
    }

    public void insert() {
        this.getVerirdao().Save(this.verir);
        this.clearForm();
    }

    public void clearForm() {
        this.verir=new verir();
    }

    public void updateForm(verir verir) {
        this.verir = verir;
    }

    public void updatee() {
        this.getVerirdao().update(this.verir);
        this.clearForm();
    }

}
