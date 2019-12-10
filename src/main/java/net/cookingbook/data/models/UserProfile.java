package net.cookingbook.data.models;

import javax.persistence.*;

@Entity
@Table(name = "user_profile")
public class UserProfile extends BaseEntity {

    private String firstName;
    private String lastName;
    private String imageUrl;
    private User user;


    public UserProfile() {
    }

    @Column
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column
    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "users_id",
            referencedColumnName = "id"
    )
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
