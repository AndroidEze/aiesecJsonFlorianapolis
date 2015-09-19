package me.ezeezegg.aiesecjsonflorianapolis.models;

/**
 * Created by egarcia on 9/19/15.
 */
public class info {

    private String website;
    private String content;
    private String date;
    private String authors;
    private String title;
    private String image;

    public info(String website, String content, String date, String authors, String title, String image) {
        this.website = website;
        this.content = content;
        this.date = date;
        this.authors = authors;
        this.title = title;
        this.image = image;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
