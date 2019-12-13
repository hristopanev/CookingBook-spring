package net.cookingbook.data.models;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Message extends BaseEntity {

    private String description;
    private User sender;
    private User user;

    public Message() {
    }

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToOne(targetEntity = User.class)
    @JoinColumn(
            name = "sender_id",
            referencedColumnName = "id"
    )
    public User getSender() {
        return this.sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
