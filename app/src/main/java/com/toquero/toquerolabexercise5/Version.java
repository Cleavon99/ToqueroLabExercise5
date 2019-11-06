package com.toquero.toquerolabexercise5;

public class Version {
    int logo;
    String apis, versions, levels, relDate, description;

    public Version(int logo, String apis, String versions, String levels, String relDate, String description) {
        this.logo = logo;
        this.apis = apis;
        this.versions = versions;
        this.levels = levels;
        this.relDate = relDate;
        this.description = description;
    }

    public int getLogo(){
        return logo;
    }

    public String getApis() {
        return apis;
    }

    public String getVersions(){
        return versions;
    }

    public String getLevels() {
        return levels;
    }

    public String getRelDate() {
        return relDate;
    }
    public String getDescription() {
        return description;
    }

}


