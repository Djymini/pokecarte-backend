package com.loutredev.pokecarte.api.responses;

public class TCGDEXResponse {
    String id;
    String logo;
    String name;
    String releaseDate;

    public TCGDEXResponse(String id, String logo, String name, String releaseDate) {
        this.id = id;
        this.logo = logo;
        this.name = name;
        this.releaseDate = releaseDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
