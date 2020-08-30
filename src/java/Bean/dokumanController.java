package Bean;

import Dao.dokumanDAO;
import Entity.dokuman;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;
import javax.servlet.http.Part;

@Named("dok")
@SessionScoped
public class dokumanController implements Serializable {
    
    private dokuman dokuman;
    private List<dokuman> dokumanListesi;
    private List<dokuman> dokumanListesiLastFive;
    private List<dokuman> dokumanListesiAll;
    private dokumanDAO dokdao;
    private int page = 1;
    private int ListİtemCount = 2;
    private Part file;
    private final String uploadTo ="C:\\Users\\s_a-i\\netbeans\\Projemiz\\web\\documents\\";

    
    
    public void upload(){
        try{
            InputStream input=file.getInputStream();
            File f=new File(uploadTo+file.getSubmittedFileName());
            Files.copy(input, f.toPath());
            
            dokuman=this.getDokuman();
            dokuman.setUzanti(f.getParent());
            dokuman.setDok_ad(f.getName());
            dokuman.setTip(file.getContentType());
            this.getDokdao().Save(dokuman);
            
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    
    public String getUploadTo() {
        return uploadTo;
    }
    
    

    public List<dokuman> getDokumanListesiLastFive() {
        this.dokumanListesiLastFive=this.getDokdao().getListLastFive();
        return dokumanListesiLastFive;
    }

    public void setDokumanListesiLastFive(List<dokuman> dokumanListesiLastFive) {
        this.dokumanListesiLastFive = dokumanListesiLastFive;
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
    
    public boolean hasPrev() {
        return this.page > 1;
    }
    
    public boolean hasNext() {
        return this.page <= (this.getDokdao().dokumanSayisi() / ListİtemCount);
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
        this.dokumanListesi = this.getDokdao().getList(page, ListİtemCount);
    }
    
    public void last() {
        page = 1 + (this.getDokdao().dokumanSayisi() / ListİtemCount);
        ListİtemCount = 2;
        this.dokumanListesi = this.getDokdao().getList(page, ListİtemCount);
        
    }
    
    public void Remove() {
        this.getDokdao().Delete(this.dokuman);
        this.clearForm();
    }
 
    public void clearForm() {
        this.dokuman = new dokuman();
    }
    
    public void updateForm(dokuman dokuman) {
        this.dokuman = dokuman;
    }
    
    public void updatee() {
        this.getDokdao().update(this.dokuman);
        this.clearForm();
    }
    
    public dokumanController() {
        this.dokumanListesi = new ArrayList<>();
        dokdao = new dokumanDAO();
    }
    
    public dokuman getDokuman() {
        if (this.dokuman == null) {
            this.dokuman = new dokuman();
        }
        return dokuman;
    }
    
    public void setDokuman(dokuman dokuman) {
        this.dokuman = dokuman;
    }
    
    public List<dokuman> getDokumanListesi() {
        this.dokumanListesi = this.dokdao.getList(page, ListİtemCount);
        return dokumanListesi;
    }
    
    public void setDokumanListesi(List<dokuman> dokumanListesi) {
        this.dokumanListesi = dokumanListesi;
    }
    
    public dokumanDAO getDokdao() {
        return dokdao;
    }
    
    public void setDokdao(dokumanDAO dokdao) {
        this.dokdao = dokdao;
    }
    
    public List<dokuman> getDokumanListesiAll() {
        this.dokumanListesiAll = this.getDokdao().getListAll();
        return dokumanListesiAll;
    }
    
    public void setDokumanListesiAll(List<dokuman> dokumanListesiAll) {
        this.dokumanListesiAll = dokumanListesiAll;
    }
    
    public Part getFile() {
        return file;
    }
    
    public void setFile(Part file) {
        this.file = file;
    }
    
    
    
    
}
