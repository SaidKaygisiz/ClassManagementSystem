package Bean;

import Dao.tipDAO;
import Entity.tip;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;


import javax.inject.Named;

@Named("tp")
@SessionScoped
public class tipController implements Serializable {

    private tip tip;
    private List<tip> tipListesi;
    private tipDAO tipdao;

    public tipController() {
        this.tipListesi = new ArrayList<>();
        tipdao = new tipDAO();
    }

    public tip getTip() {
        if (this.tip == null) {
            this.tip = new tip();
        }
        return tip;
    }

    public void setTip(tip tip) {
        this.tip = tip;
    }

    public List<tip> getTipListesi() {
        this.tipListesi = this.tipdao.getList();
        return tipListesi;
    }

    public void setTipListesi(List<tip> tipListesi) {
        this.tipListesi = tipListesi;
    }

    public tipDAO getTipdao() {
        return tipdao;
    }

    public void setTipdao(tipDAO tipdao) {
        this.tipdao = tipdao;
    }

    public void Remove() {
        this.getTipdao().Delete(this.tip);
        this.clearForm();
    }

    public void insert() {
        this.getTipdao().Save(this.tip);
        this.clearForm();
    }

    public void clearForm() {
        this.tip = new tip();
    }

    public void updateForm(tip tip) {
        this.tip = tip;
    }

    public void updatee() {
        this.getTipdao().update(this.tip);
        this.clearForm();
    }

}
