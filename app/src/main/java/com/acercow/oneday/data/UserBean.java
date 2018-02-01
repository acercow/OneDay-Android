package com.acercow.oneday.data;

/**
 * Created by zhaosen on 2018/2/1.
 */

public class UserBean {
    private long id;
    private String nickName;
    private String email;
    private int gender;
    private int phone;


    public UserBean(long id, String nickName, String email, int gender, int phone) {
        this.id = id;
        this.nickName = nickName;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
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

        if (id != userBean.id) return false;
        if (!nickName.equals(userBean.nickName)) return false;
        return email.equals(userBean.email);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + nickName.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}
