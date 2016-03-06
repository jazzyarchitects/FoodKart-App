package phoenix.hackfest.orderit.HelperClasses;

import android.content.Context;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Jibin_ism on 03-Mar-16.
 */
public class Constants {

    public static boolean isValidEmail(CharSequence target) {
        return (target != null) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static void storeAccessToken(Context context, String accessToken){
        context.getSharedPreferences(Constants.USER_SHARED_PREFERENCES,Context.MODE_PRIVATE)
                .edit()
                .putString(Constants.USER_ACCESS_TOKEN, accessToken)
                .putBoolean(Constants.USER_LOGGED_IN, true)
                .apply();
    }

    public static void logout(Context context){
        context.getSharedPreferences(Constants.USER_SHARED_PREFERENCES, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }


    public static final String USER_SHARED_PREFERENCES = "userShared";
    public static final String USER_ORDER_LIST="userOrder";
    public static final String USER_LOGGED_IN= "loggedIn";
    public static final String USER_DATA= "userData";
    public static final String USER_ACCESS_TOKEN = "access_token";

    public static class QuickstartPreferences {

        public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
        public static final String REGISTRATION_COMPLETE = "registrationComplete";
        public static final String REGISTRATION_ID="registrationID";

    }


    public static JSONObject hashMapToJSON(HashMap<String, String> dataSet, String name){
        JSONObject jsonObject = new JSONObject();
        Set<String> keySet = dataSet.keySet();
        for(String key:keySet){
            try {
                jsonObject.put(key, dataSet.get(key));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        JSONObject data = new JSONObject();
        try {
            data.put(name, jsonObject);
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }


    /**
     * To convert hashmap to post request for sending to server
     *
     * @param params the hashmap
     * @return post request string
     * @throws UnsupportedEncodingException
     */
    public static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }





}