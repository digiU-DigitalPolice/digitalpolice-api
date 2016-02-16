package com.example.entity;

import java.io.Serializable;

public class Category implements Serializable{
    private long id;
    private long parent_id;
    private String locale;
    private long category_position;
    private String category_title;
    private String category_description;
    private String category_color;
    private String category_image;
    private String category_image_thumb;
    private boolean category_visible;
    private boolean category_trusted;

    public Category() {
    }

    public Category(long id, long parent_id, String locale, long category_position, String category_title, String category_description, String category_color, String category_image, String category_image_thumb, boolean category_visible, boolean category_trusted) {
        this.id = id;
        this.parent_id = parent_id;
        this.locale = locale;
        this.category_position = category_position;
        this.category_title = category_title;
        this.category_description = category_description;
        this.category_color = category_color;
        this.category_image = category_image;
        this.category_image_thumb = category_image_thumb;
        this.category_visible = category_visible;
        this.category_trusted = category_trusted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParent_id() {
        return parent_id;
    }

    public void setParent_id(long parent_id) {
        this.parent_id = parent_id;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public long getCategory_position() {
        return category_position;
    }

    public void setCategory_position(long category_position) {
        this.category_position = category_position;
    }

    public String getCategory_title() {
        return category_title;
    }

    public void setCategory_title(String category_title) {
        this.category_title = category_title;
    }

    public String getCategory_description() {
        return category_description;
    }

    public void setCategory_description(String category_description) {
        this.category_description = category_description;
    }

    public String getCategory_color() {
        return category_color;
    }

    public void setCategory_color(String category_color) {
        this.category_color = category_color;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }

    public String getCategory_image_thumb() {
        return category_image_thumb;
    }

    public void setCategory_image_thumb(String category_image_thumb) {
        this.category_image_thumb = category_image_thumb;
    }

    public boolean isCategory_visible() {
        return category_visible;
    }

    public void setCategory_visible(boolean category_visible) {
        this.category_visible = category_visible;
    }

    public boolean isCategory_trusted() {
        return category_trusted;
    }

    public void setCategory_trusted(boolean category_trusted) {
        this.category_trusted = category_trusted;
    }
}
