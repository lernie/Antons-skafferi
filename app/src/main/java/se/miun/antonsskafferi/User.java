package se.miun.antonsskafferi;

import com.google.gson.annotations.SerializedName;

/**
 * Created by My on 2017-10-14.
 */

public class User {
    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public User(String username, String password){
        this.username = username;
        this. password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
