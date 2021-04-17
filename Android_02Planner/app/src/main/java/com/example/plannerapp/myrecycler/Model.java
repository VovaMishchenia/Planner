package com.example.plannerapp.myrecycler;

public class Model {
    private String name;
    private String version;
private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Model(String name, String version, String image) {
        this.name = name;
        this.version = version;
        this.image = image;
    }

    public Model() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
