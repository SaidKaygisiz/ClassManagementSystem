/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Dao.dokumanDAO;
import Entity.dokuman;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author s_a-i
 */

@FacesConverter(value="dokumanConverter")
public class DokumanConverter implements Converter {
    dokumanDAO dokdao;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return this.getDokdao().find(Integer.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        dokuman d=(dokuman)o;
        return String.valueOf(d.getDok_id());
        
    }

    public dokumanDAO getDokdao() {
        if(this.dokdao==null)
            this.dokdao=new dokumanDAO();
        return dokdao;
    }
    
}
