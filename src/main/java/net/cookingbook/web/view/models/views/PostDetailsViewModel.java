package net.cookingbook.web.view.models.views;


import net.cookingbook.service.models.services.RateServiceModel;

public class PostDetailsViewModel {

    private String name;
    private String imageUrl;
    private String products;
    private String description;
    private RateServiceModel rate;

    public PostDetailsViewModel() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProducts() {
        return this.products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RateServiceModel getRate() {
        return this.rate;
    }

    public void setRate(RateServiceModel rate) {
        this.rate = rate;
    }
}
