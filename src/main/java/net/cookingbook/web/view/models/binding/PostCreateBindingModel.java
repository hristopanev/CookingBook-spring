package net.cookingbook.web.view.models.binding;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

public class PostCreateBindingModel {

    private String name;
    private MultipartFile image;
    private String products;
    private String description;
    private Date postTime;

    public PostCreateBindingModel() {
    }

    @NotEmpty(message = "Recipe name cannot be empty")
    @Size(min = 2, max = 30, message = "Recipe should be between 2 and 30")
    @Pattern(regexp = "")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty(message = "Image cannot be empty")
    public MultipartFile getImage() {
        return this.image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @NotEmpty(message = "Products cannot be empty")
    @Size(min = 10, message = "Recipe should be min 10")
    public String getProducts() {
        return this.products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 10, message = "Description should be min 10")
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
}
