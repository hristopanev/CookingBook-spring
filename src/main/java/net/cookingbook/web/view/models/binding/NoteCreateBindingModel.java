package net.cookingbook.web.view.models.binding;

import java.util.Date;

public class NoteCreateBindingModel {

    private String title;
    private String description;
    private Date createTime;


    public NoteCreateBindingModel() {
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
