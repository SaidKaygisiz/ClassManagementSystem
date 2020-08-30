package Bean;

import Dao.derslerDAO;
import Dao.ogrenciDAO;
import Entity.ogrenci;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

/**
 *
 * @author s_a-i
 */
@Named("ogr")
@SessionScoped
public class ogrenciController implements Serializable {

    private ogrenci ogrenci;
    private List<ogrenci> ogrencilist;
    private List<ogrenci> ogrencilistLastFive;
    private List<ogrenci> ogrencilistAll;
    private ogrenciDAO ogrdao;
    private derslerDAO dersdao;
    private List<Integer> selectedDersler;
    private int page = 1;
    private int ListİtemCount = 2;

    public List<ogrenci> getOgrencilistLastFive() {
        this.ogrencilistLastFive = this.getOgrdao().getListLastFive();
        return ogrencilistLastFive;
    }

    public void setOgrencilistLastFive(List<ogrenci> ogrencilistLastFive) {
        this.ogrencilistLastFive = ogrencilistLastFive;
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

    public List<ogrenci> getOgrencilistAll() {
        this.ogrencilistAll = this.getOgrdao().getListAll();
        return ogrencilistAll;
    }

    public void setOgrencilistAll(List<ogrenci> ogrencilistAll) {
        this.ogrencilistAll = ogrencilistAll;
    }

    public void Remove() {
        this.getOgrdao().Delete(this.ogrenci);
        this.clearForm();
    }

    public void insert() {
        this.getOgrdao().Save(this.ogrenci);
        this.clearForm();
    }

    public void clearForm() {
        this.ogrenci = new ogrenci();
        this.selectedDersler.removeAll(selectedDersler);
    }

    public void updateForm(ogrenci ogrenci) {
        this.ogrenci = ogrenci;
    }

    public void updatee() {
        this.getOgrdao().update(this.ogrenci);
        this.clearForm();
    }

    public ogrenciController() {
        this.selectedDersler = new ArrayList<>();
        this.ogrencilist = new ArrayList<>();
        ogrdao = new ogrenciDAO();
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

    public List<ogrenci> getOgrencilist() {
        this.ogrencilist = this.getOgrdao().getList(page, ListİtemCount);
        return ogrencilist;
    }

    public void setOgrencilist(List<ogrenci> ogrencilist) {
        this.ogrencilist = ogrencilist;
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

    public derslerDAO getDersdao() {
        if (this.dersdao == null) {
            this.dersdao = new derslerDAO();
        }
        return dersdao;
    }

    public List<Integer> getSelectedDersler() {
        return selectedDersler;
    }

    public void setSelectedDersler(List<Integer> selectedDersler) {
        this.selectedDersler = selectedDersler;
    }

    public boolean hasPrev() {
        return this.page > 1;
    }

    public boolean hasNext() {
        return this.page <= (this.getOgrdao().ogrenciSayisi() / ListİtemCount);
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
        this.ogrencilist = this.getOgrdao().getList(page, ListİtemCount);
    }

    public void last() {
        page = 1 + (this.getOgrdao().ogrenciSayisi() / ListİtemCount);
        ListİtemCount = 2;
        this.ogrencilist = this.getOgrdao().getList(page, ListİtemCount);

    }

}
