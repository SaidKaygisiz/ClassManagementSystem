package Bean;

import Dao.grupDAO;
import Entity.grup;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("grp")
@SessionScoped
public class grupController implements Serializable {

    private grup grup;
    private List<grup> grupListesi;
    private grupDAO grupdao;

    public void remove() {
        this.getGrupdao().delete(this.grup);
        this.clearForm();
    }

    public void insert() {
        this.getGrupdao().Save(this.grup);
        this.clearForm();
    }

    public void clearForm() {
        this.grup = new grup();
    }

    public void updateForm(grup grup) {
        this.grup = grup;
    }

    public void deleteconfirm(grup grp) {
        this.grup = grp;

    }

    public void updatee() {
        this.getGrupdao().update(this.grup);
        this.clearForm();
    }

    public grupController() {
        this.grupListesi = new ArrayList<>();
        grupdao = new grupDAO();
    }

    public grup getGrup() {
        if (this.grup == null) {
            this.grup = new grup();
        }
        return grup;
    }

    public void setGrup(grup grup) {
        this.grup = grup;
    }

    public List<grup> getGrupListesi() {
        this.grupListesi = this.grupdao.getList();
        return grupListesi;
    }

    public void setGrupListesi(List<grup> grupListesi) {
        this.grupListesi = grupListesi;
    }

    public grupDAO getGrupdao() {
        if (this.grupdao == null) {
            this.grupdao = new grupDAO();
        }
        return grupdao;
    }

    public void setGrupdao(grupDAO grupdao) {
        this.grupdao = grupdao;
    }

   
}
