/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Dao.ogrenciDAO;
import Entity.ogrenci;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author s_a-i
 */
@FacesConverter(value="ogrenciConverter")
public class ogrenciConverter implements Converter {
    
    ogrenciDAO ogrdao;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return this.getOgrdao().findOgrenci(Integer.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        ogrenci ogr=(ogrenci)o;
        return String.valueOf(ogr.getOgr_id());
    }

    public ogrenciDAO getOgrdao() {
        if(this.ogrdao==null)
        {
            this.ogrdao=new ogrenciDAO();
        }
        return ogrdao;
    }
    
    
}
