package com.mobileappscompany.training.recycler;


import com.orm.SugarRecord;

/**
 * Created by admin on 2/27/2016.
 */
public class Person  extends SugarRecord<Person>{

    private String fullName;
    private String status;
    private int photoId;
    private String date;

    public Person(String fullName, String status, int photoId, String date) {
        this.fullName = fullName;
        this.status = status;
        this.photoId = photoId;
        this.date= date;
    }

    public Person() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
}
