/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Dao.gorevDAO;
import Entity.gorev;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author s_a-i
 */
@FacesConverter(value="gorevConverter")
public class GorevConverter implements Converter {
    gorevDAO gorevdao;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return this.getGorevdao().findGorev(Integer.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        gorev g=(gorev) o;
        return String.valueOf(g.getGorev_id());
    }

    public gorevDAO getGorevdao() {
        if(this.gorevdao==null){
            this.gorevdao=new gorevDAO();
        }
        return gorevdao;
    }
    
}
