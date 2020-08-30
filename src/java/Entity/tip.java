package Entity;

public class tip {

    private int tip_id;
    private String tip_ad;

    public tip() {
    }

    public tip(int tip_id, String tip_ad) {
        this.tip_id = tip_id;
        this.tip_ad = tip_ad;
    }

    public int getTip_id() {
        return tip_id;
    }

    public void setTip_id(int tip_id) {
        this.tip_id = tip_id;
    }

    public String getTip_ad() {
        return tip_ad;
    }

    public void setTip_ad(String tip_ad) {
        this.tip_ad = tip_ad;
    }

    @Override
    public String toString() {
        return "tip{" + "tip_id=" + tip_id + ", tip_ad=" + tip_ad + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.tip_id;
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
        final tip other = (tip) obj;
        if (this.tip_id != other.tip_id) {
            return false;
        }
        return true;
    }

}
