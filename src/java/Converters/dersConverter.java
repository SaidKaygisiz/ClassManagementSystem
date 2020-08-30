/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Dao.derslerDAO;
import Entity.dersler;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author s_a-i
 */
@FacesConverter(value="dersConverter")
public class dersConverter implements Converter {
    derslerDAO dersdao;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return this.getDersdao().find(Integer.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        dersler d=(dersler) o;
        return String.valueOf(d.getDers_id());
    }

    public derslerDAO getDersdao() {
        if(this.dersdao==null){
            this.dersdao=new derslerDAO();
        }
        return dersdao;
    }
    
}
