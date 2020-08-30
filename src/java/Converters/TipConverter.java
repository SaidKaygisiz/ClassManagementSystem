/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Dao.tipDAO;
import Entity.tip;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author s_a-i
 */
@FacesConverter(value="tipConverter")
public class TipConverter implements Converter {
    tipDAO tipdao;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {    
        return this.getTipdao().find(Integer.valueOf(string));
  
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
       tip t=(tip) o;
       return String.valueOf(t.getTip_id());
    }

    public tipDAO getTipdao() {
        if(this.tipdao==null)
            this.tipdao=new tipDAO();
        return tipdao;
    }
    
    
}
