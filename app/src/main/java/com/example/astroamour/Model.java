package com.example.astroamour;

public class Model {
    String title;
    String date;
    String url;
    String hdurl;
    String media_type;
    String explanation;
    String copyright;
    String service_version;

    public Model() {
    }

    public Model(String resource, Boolean concept_tags, String title, String date, String url, String hdurl, String media_type, String explanation, String concepts, String thumbnail_url, String copyright, String service_version) {
        this.title = title;
        this.date = date;
        this.url = url;
        this.hdurl = hdurl;
        this.media_type = media_type;
        this.explanation = explanation;
        this.copyright = copyright;
        this.service_version = service_version;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHdurl() {
        return hdurl;
    }

    public void setHdurl(String hdurl) {
        this.hdurl = hdurl;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getService_version() {
        return service_version;
    }

    public void setService_version(String service_version) {
        this.service_version = service_version;
    }
}
