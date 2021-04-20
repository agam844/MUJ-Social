package Model;

public class user {

    private String username;
    private String bio;
    private String email;
    private String imageurl;
    private String Id;

    public user(){

    }

    public user(String username, String bio, String email, String imageurl, String id) {
        this.username = username;
        this.bio = bio;
        this.email = email;
        this.imageurl = imageurl;
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
