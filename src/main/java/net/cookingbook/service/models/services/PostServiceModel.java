package net.cookingbook.service.models.services;



import java.util.Date;
import java.util.List;

public class PostServiceModel extends BaseServiceModel {

    private String name;
    private String imageUrl;
    private String products;
    private String description;
    private Date postTime;
    private UserServiceModel uploader;
    private RateServiceModel rate;
//    private List<Group> groups;
    private List<CommentServiceModel> comments;

    public PostServiceModel() {
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

    public Date getPostTime() {
        return this.postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public UserServiceModel getUploader() {
        return this.uploader;
    }

    public void setUploader(UserServiceModel uploader) {
        this.uploader = uploader;
    }

    public RateServiceModel getRate() {
        return this.rate;
    }

    public void setRate(RateServiceModel rate) {
        this.rate = rate;
    }

    public List<CommentServiceModel> getComments() {
        return this.comments;
    }

    public void setComments(List<CommentServiceModel> comments) {
        this.comments = comments;
    }

}
