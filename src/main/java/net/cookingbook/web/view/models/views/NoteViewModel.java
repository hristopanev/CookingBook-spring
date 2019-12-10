package net.cookingbook.web.view.models.views;

import java.util.Date;

public class NoteViewModel {

    private String id;
    private String title;
    private String description;
    private Date createTime;

    public NoteViewModel() {
    }

    public String getTitle() {
        return this.title;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
