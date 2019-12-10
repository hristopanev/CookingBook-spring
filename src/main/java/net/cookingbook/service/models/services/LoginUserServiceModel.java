package net.cookingbook.service.models.services;

public class LoginUserServiceModel {
    private String username;
    private String heroName;

    public LoginUserServiceModel() {
    }

    public LoginUserServiceModel(String username, String heroName) {
        this.username = username;
        this.heroName = heroName;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeroName() {
        return this.heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }
}
