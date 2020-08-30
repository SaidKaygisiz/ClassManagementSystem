package Entity;

public class grup {

    private int grup_id;
    private String grup_ad;

    public grup() {
    }

    public grup(int grup_id, String grup_ad) {
        this.grup_id = grup_id;
        this.grup_ad = grup_ad;
    }

    public int getGrup_id() {
        return grup_id;
    }

    public void setGrup_id(int grup_id) {
        this.grup_id = grup_id;
    }

    public String getGrup_ad() {
        return grup_ad;
    }

    public void setGrup_ad(String grup_ad) {
        this.grup_ad = grup_ad;
    }

    @Override
    public String toString() {
        return "grup{" + "grup_id=" + grup_id + ", grup_ad=" + grup_ad + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.grup_id;
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
        final grup other = (grup) obj;
        if (this.grup_id != other.grup_id) {
            return false;
        }
        return true;
    }

   
    
}
