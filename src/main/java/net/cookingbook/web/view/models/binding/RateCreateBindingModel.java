package net.cookingbook.web.view.models.binding;

import net.cookingbook.service.models.services.PostServiceModel;

public class RateCreateBindingModel {

    private PostServiceModel postServiceModel;

    public RateCreateBindingModel() {
    }

    public PostServiceModel getPostServiceModel() {
        return this.postServiceModel;
    }

    public void setPostServiceModel(PostServiceModel postServiceModel) {
        this.postServiceModel = postServiceModel;
    }
}
