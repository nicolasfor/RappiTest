package com.rappitest.nicolasfor.rappitest.model;

import android.graphics.Bitmap;
import java.io.Serializable;

/**
 * Created by nicolasfor on 15/12/2016.
 */
public class Post implements Serializable {
    private String description;
    private String descriptionHtml;
    private String subreddit;
    private String submitText;
    private String submitTextHtml;
    private String title;
    private int subscribers;
    private int numComments;
    private String url;
    private String id;
    private String keyColor;
    private Bitmap banner_img;
    private Bitmap icon;
    private String headerTitle;
    private int created;
    private Bitmap headerImage;
    private boolean over18;

    public Post(String id, String headerTitle, String url, int numComments, int subscribers, String title, String subreddit,String description,String descriptionHtml, String submitText, String submitTextHtml, boolean over18,int created, String keyColor, Bitmap banner_img,Bitmap icon,Bitmap headerImage) {
        this.id = id;
        this.url = url;
        this.numComments = numComments;
        this.subscribers = subscribers;
        this.title = title;
        this.subreddit = subreddit;
        this.descriptionHtml=descriptionHtml;
        this.banner_img=banner_img;
        this.icon= icon;
        this.submitTextHtml =submitTextHtml;
        this.description=description;
        this.submitText=submitText;
        this.headerTitle=headerTitle;
        this.headerImage=headerImage;
        this.over18=over18;
        this.created=created;
        this.keyColor=keyColor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }

    public int getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(int subscribers) {
        this.subscribers = subscribers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public Bitmap getBanner_img() {
        return banner_img;
    }

    public void setBanner_img(Bitmap banner_img) {
        this.banner_img = banner_img;
    }

    public String getDescriptionHtml() {
        return descriptionHtml;
    }

    public void setDescriptionHtml(String description) {
        this.descriptionHtml = description;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public String getSubmitTextHtml() {
        return submitTextHtml;
    }

    public void setSubmitTextHtml(String submitText) {
        this.submitTextHtml = submitText;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubmitText() {
        return submitText;
    }

    public void setSubmitText(String submitText) {
        this.submitText = submitText;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public Bitmap getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(Bitmap headerImage) {
        this.headerImage = headerImage;
    }

    public boolean isOver18() {
        return over18;
    }

    public void setOver18(boolean over18) {
        this.over18 = over18;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public String getKeyColor() {
        return keyColor;
    }

    public void setKeyColor(String keyColor) {
        this.keyColor = keyColor;
    }
}
