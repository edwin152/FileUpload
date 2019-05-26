package cn.ddzu.shop.entity;

import java.sql.Date;

public class News {

    private Long id;
    private String title;
    private String sub_title;
    private Date create_time;
    private Date last_edit_time;
    private String content;
    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getLast_edit_time() {
        return last_edit_time;
    }

    public void setLast_edit_time(Date last_edit_time) {
        this.last_edit_time = last_edit_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
