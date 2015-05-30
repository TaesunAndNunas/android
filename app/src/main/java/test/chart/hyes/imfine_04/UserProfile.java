package test.chart.hyes.imfine_04;

/**
 * Created by hyes on 2015. 5. 30..
 */
public class UserProfile {

    private String name, p_id, pwd1, pwd2, gender, birth, image_url;

    public UserProfile(String name, String id, String pwd1, String gender, String birth, String image_url) {
        this.name = name;
        this.p_id = id;
        this.pwd1 = pwd1;

        this.gender = gender;
        this.birth = birth;
        this.image_url = image_url;
    }

    public UserProfile(String name, String id, String pwd1, String gender, String birth) {
        this.name = name;
        this.p_id = id;
        this.pwd1 = pwd1;

        this.gender = gender;
        this.birth = birth;
        this.image_url = image_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getName() {
        return name;
    }

    public String getPid() {
        return p_id;
    }

    public String getPwd1() {
        return pwd1;
    }



    public String getGender() {
        return gender;
    }

    public String getBirth() {
        return birth;
    }
}
