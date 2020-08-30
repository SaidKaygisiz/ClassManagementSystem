
package Converters;

import Dao.ogretmenDAO;
import Entity.ogretmen;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "ogretmenConverter")
public class ogretmenConverter implements Converter {

    ogretmenDAO ogrtdao;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return this.getOgrtdao().bulOgretmen(Integer.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        
        ogretmen og = (ogretmen) o;
        return String.valueOf(og.getOgrt_id());
    }

    public ogretmenDAO getOgrtdao() {
        if (this.ogrtdao == null) {
            this.ogrtdao = new ogretmenDAO();
        }
        return ogrtdao;
    }

}
