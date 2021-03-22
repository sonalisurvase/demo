package com.ssurvase.appthemechanger;

public class PojoModel {

//    "userId": 1,
//            "id": 1,
//            "title": "delectus aut autem",
//            "completed": false

    private int userId;
    private int id;
    private String title;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    private String body;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
