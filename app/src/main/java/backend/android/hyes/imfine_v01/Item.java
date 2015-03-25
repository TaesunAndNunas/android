package backend.android.hyes.imfine_v01;

/**
 * Created by hyes on 2015. 3. 25..
 */
public class Item {
    private int articleNumber;
    String color_idx;
    String time;
    String details;

    public Item() {

    }

    public Item(String color_idx, String time, String details) {
        this.color_idx = color_idx;
        this.time = time;
        this.details = details;
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(int articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getColor_idx() {
        return color_idx;
    }

    public void setColor_idx(String color_idx) {
        this.color_idx = color_idx;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
