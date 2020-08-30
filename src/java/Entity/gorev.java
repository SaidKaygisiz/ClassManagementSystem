package Entity;

public class gorev {

    private int gorev_id;
    private String gorev_ad;
    private tip tip;

    public gorev() {
    }

    public gorev(int gorev_id, String gorev_ad, tip tip) {
        this.gorev_id = gorev_id;
        this.gorev_ad = gorev_ad;
        this.tip = tip;
    }

    public int getGorev_id() {
        return gorev_id;
    }

    public void setGorev_id(int gorev_id) {
        this.gorev_id = gorev_id;
    }

    public String getGorev_ad() {
        return gorev_ad;
    }

    public void setGorev_ad(String gorev_ad) {
        this.gorev_ad = gorev_ad;
    }

    public tip getTip() {
        if(this.tip==null){
            this.tip=new tip();
        }
        return tip;
    }

    public void setTip(tip tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return "gorev{" + "gorev_id=" + gorev_id + ", gorev_ad=" + gorev_ad + ", tip=" + tip + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.gorev_id;
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
        final gorev other = (gorev) obj;
        if (this.gorev_id != other.gorev_id) {
            return false;
        }
        return true;
    }

}
