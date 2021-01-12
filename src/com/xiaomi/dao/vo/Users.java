package com.xiaomi.dao.vo;

public class Users {
    private Integer uid;

    private String username;

    private String password;

    private String phonenumber;

    private String address;

    private String hobby;

    private String sign;

    
    public Users() {
		super();
	}

    public Users( String username, String password, String phonenumber, String address, String hobby,
			String sign) {
		super();
		this.username = username;
		this.password = password;
		this.phonenumber = phonenumber;
		this.address = address;
		this.hobby = hobby;
		this.sign = sign;
	}
	public Users(Integer uid, String username, String password, String phonenumber, String address, String hobby,
			String sign) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.phonenumber = phonenumber;
		this.address = address;
		this.hobby = hobby;
		this.sign = sign;
	}

	public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber == null ? null : phonenumber.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby == null ? null : hobby.trim();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

	@Override
	public String toString() {
		return "Users [uid=" + uid + ", username=" + username + ", password=" + password + ", phonenumber="
				+ phonenumber + ", address=" + address + ", hobby=" + hobby + ", sign=" + sign + "]";
	}
    
    
}