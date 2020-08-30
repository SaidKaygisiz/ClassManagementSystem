
package Entity;

public class dokuman {
    
    private int dok_id;
    private String dok_ad;
    private String uzanti;
    private String tip;
    public dokuman() {
    }

    public dokuman(int dok_id, String dok_ad, String uzanti, String tip) {
        this.dok_id = dok_id;
        this.dok_ad = dok_ad;
        this.uzanti = uzanti;
        this.tip = tip;
    }

    

    @Override
    public String toString() {
        return "dokuman{" + "dok_id=" + dok_id + ", dok_ad=" + dok_ad + ", uzanti=" + uzanti + ", tip=" + tip + '}';
    }

    

    public int getDok_id() {
        return dok_id;
    }

    public void setDok_id(int dok_id) {
        this.dok_id = dok_id;
    }

    public String getDok_ad() {
        return dok_ad;
    }

    public void setDok_ad(String dok_ad) {
        this.dok_ad = dok_ad;
    }

    public String getUzanti() {
        return uzanti;
    }

    public void setUzanti(String uzanti) {
        this.uzanti = uzanti;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.dok_id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final dokuman other = (dokuman) obj;
        if (this.dok_id != other.dok_id) {
            return false;
        }
        return true;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
    
    
    
    
    
}
