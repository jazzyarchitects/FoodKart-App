package phoenix.hackfest.orderit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import phoenix.hackfest.orderit.HelperClasses.Constants;
import phoenix.hackfest.orderit.Services.RegistrationIntentService;
import phoenix.ism.hackfest.orderit.R;

/**
 * Created by Jibin_ism on 05-Mar-16.
 */
public class SignUp extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        String token = sharedPreferences.getString(Constants.QuickstartPreferences.REGISTRATION_ID,"");
        Boolean regToServer = sharedPreferences.getBoolean(Constants.QuickstartPreferences.SENT_TOKEN_TO_SERVER,false);

        if(token.isEmpty() || !regToServer)
        {
            Intent i=new Intent(this, RegistrationIntentService.class);
            startService(i);
        }



    }
}
