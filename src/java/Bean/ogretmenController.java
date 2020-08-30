package Bean;

import Dao.ogretmenDAO;
import Entity.ogretmen;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

@Named("ogrt")
@SessionScoped
public class ogretmenController implements Serializable {

    private ogretmen ogretmen;
    private List<ogretmen> ogretmenList;
    private List<ogretmen> ogretmenListLastFive;
    private List<ogretmen> ogretmenListAll;
    private ogretmenDAO ogrtdao;
    private int page = 1;
    private int ListİtemCount = 2;

    public List<ogretmen> getOgretmenListLastFive() {
        this.ogretmenListLastFive=this.getOgrtdao().getListLastFive();
        return ogretmenListLastFive;
    }

    public void setOgretmenListLastFive(List<ogretmen> ogretmenListLastFive) {
        this.ogretmenListLastFive = ogretmenListLastFive;
    }
    
    
    
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

    public List<ogretmen> getOgretmenListAll() {
        this.ogretmenListAll=this.getOgrtdao().getListAll();
        return ogretmenListAll;
    }

    public void setOgretmenListAll(List<ogretmen> ogretmenListAll) {
        this.ogretmenListAll = ogretmenListAll;
    }

    
    public boolean hasPrev() {
        return this.page > 1;
    }

    public boolean hasNext() {
        return this.page <= (this.getOgrtdao().ogretmenSayisi() / ListİtemCount);
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
        this.ogretmenList=this.getOgrtdao().getList(page, ListİtemCount);
    }

    public void last() {
        page = 1 + (this.getOgrtdao().ogretmenSayisi() / ListİtemCount);
        ListİtemCount = 2;
        this.ogretmenList = this.getOgrtdao().getList(page, ListİtemCount);

    }

    public void Remove() {
        this.getOgrtdao().Delete(this.ogretmen);
        this.clearForm();
    }

    public void insert() {
        this.getOgrtdao().Save(this.ogretmen);
        this.clearForm();
    }

    public void clearForm() {
        this.ogretmen = new ogretmen();
    }

    public void updateForm(ogretmen ogretmen) {
        this.ogretmen = ogretmen;
    }

    public void updatee() {
        this.getOgrtdao().update(this.ogretmen);
        this.clearForm();
    }

    public ogretmenController() {
        this.ogretmenList = new ArrayList<>();
        this.ogrtdao = new ogretmenDAO();
    }

    public ogretmen getOgretmen() {
        if (this.ogretmen == null) {
            this.ogretmen = new ogretmen();
        }
        return ogretmen;
    }

    public void setOgretmen(ogretmen ogretmen) {
        this.ogretmen = ogretmen;
    }

    public List<ogretmen> getOgretmenList() {
        this.ogretmenList = this.ogrtdao.getList(page,ListİtemCount);
        return ogretmenList;
    }

    public void setOgretmenList(List<ogretmen> ogretmenList) {
        this.ogretmenList = ogretmenList;
    }

    public ogretmenDAO getOgrtdao() {
        if (this.ogrtdao == null) {
            this.ogrtdao = new ogretmenDAO();
        }
        return ogrtdao;
    }

    public void setOgrtdao(ogretmenDAO ogrtdao) {
        this.ogrtdao = ogrtdao;
    }
}
