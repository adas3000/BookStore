package com.example.mylib.Data;

import android.annotation.TargetApi;

import java.util.Objects;

@TargetApi(19)
public class User {

    private String nickname;

    public User(String nickname){
        this.nickname = nickname;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(nickname, user.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }



}
