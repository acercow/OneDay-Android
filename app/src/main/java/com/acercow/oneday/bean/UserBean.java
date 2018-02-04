package com.acercow.oneday.bean;

/**
 * Created by zhaosen on 2018/2/1.
 */

public class UserBean {
    private String id;
    private String nickName;
    private String email;
    private int gender;
    private int phone;


    public UserBean(String id, String nickName, String email, int gender, int phone) {
        this.id = id;
        this.nickName = nickName;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBean userBean = (UserBean) o;

        if (id != null ? !id.equals(userBean.id) : userBean.id != null) return false;
        if (nickName != null ? !nickName.equals(userBean.nickName) : userBean.nickName != null)
            return false;
        return email != null ? email.equals(userBean.email) : userBean.email == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
