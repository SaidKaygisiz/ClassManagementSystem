/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Dao.grupDAO;
import Entity.grup;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author s_a-i
 */
@FacesConverter(value="grupConverter")
public class GrupConverter implements Converter {

    private grupDAO grupdao;

    public grupDAO getGrupdao() {
        if (this.grupdao == null) {
            this.grupdao = new grupDAO();
        }
        return grupdao;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return this.getGrupdao().find(Integer.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        grup g = (grup) o;
        return String.valueOf(g.getGrup_id());

    }

}
