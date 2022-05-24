package com.atzzazz.pojo;

/**
 * @Description：$
 * @Author： zzazz
 * @Date： 2021/11/8 - 17:03
 */
public class Users {
    private Integer id;
    private String userName;
    private String userPwd;
    private String realName;
    private String nation;
    private Integer cardId;

    public Users() {
    }

    public Users(Integer id,
                 String userName,
                 String userPwd,
                 String realName,
                 String nation,
                 Integer cardId) {
        this.id = id;
        this.userName = userName;
        this.userPwd = userPwd;
        this.realName = realName;
        this.nation = nation;
        this.cardId = cardId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", realName='" + realName + '\'' +
                ", nation='" + nation + '\'' +
                ", cardId=" + cardId +
                '}';
    }
}
