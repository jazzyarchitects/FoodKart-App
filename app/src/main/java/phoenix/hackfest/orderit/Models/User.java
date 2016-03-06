package phoenix.hackfest.orderit.Models;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import phoenix.hackfest.orderit.HelperClasses.Constants;

/**
 * Created by Jibin_ism on 03-Mar-16.
 */
public class User {
    private String name = "", id = "", email = "", phone="", gender="", dob="", address="";


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String toJSONString(){
        Gson gson=new Gson();
        return gson.toJson(this);
    }

    public static User parseJSONString(String s){
        Gson gson=new Gson();
        return gson.fromJson(s, User.class);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static User getUser(Context context){
        SharedPreferences sharedPreferences= context.getSharedPreferences(Constants.USER_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        Gson gson=new Gson();
        return gson.fromJson(sharedPreferences.getString(Constants.USER_DATA,""), User.class);
    }

    public static void saveUser(Context context, User user){
        SharedPreferences sharedPreferences= context.getSharedPreferences(Constants.USER_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        Gson gson=new Gson();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.USER_DATA, gson.toJson(user));
        editor.apply();
    }


    public static User parseUserResponse(Context context,JSONObject jsonObject){
        User user=new User();

        try {
            user.setName(jsonObject.getString("name"));
            user.setEmail(jsonObject.getString("email"));
            user.setPhone(jsonObject.getString("mobile"));
            user.setId(jsonObject.getString("_id"));


            SharedPreferences sharedPreferences=context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
            sharedPreferences.edit().putBoolean("loggedIn",true)
                    .apply();

        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }

        return user;
    }


    public static boolean isUserLoggedIn(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("loggedIn", false);
    }
}
