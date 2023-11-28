import java.util.ArrayList;

public class Kid {
    private static int NextKidId = 1;
    private int KidId;
    private String KidName;
    private String KidPassword;
    private ArrayList<String> KidWishlist;

    public Kid(String KidName, String KidPassword) {
        this.KidId = NextKidId;
        NextKidId++;
        this.KidName = KidName;
        this.KidPassword = KidPassword;
    }

    //getters setters
    public void setKidId(int kidId) {
        KidId = kidId;
    }
    public int getKidId() {
        return KidId;
    }
    public String getKidName() {
        return KidName;
    }
    public String getKidPassword() {
        return KidPassword;
    }
    // if we want to rename kids.
    public void setKidName(String kidName) {
        KidName = kidName;
    }
    public ArrayList<String> getKidWishlist() {
        return KidWishlist;
    }
    
    
}
