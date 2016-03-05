package phoenix.hackfest.orderit.Services;


/**
 * Created by Jibin_ism on 05-Mar-16.
 */

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import phoenix.hackfest.orderit.HelperClasses.Constants;
import phoenix.ism.hackfest.orderit.R;

public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        try {
            if(sharedPreferences.getBoolean(Constants.QuickstartPreferences.SENT_TOKEN_TO_SERVER,false)) {
                InstanceID instanceID = InstanceID.getInstance(this);
                String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                        GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

                sharedPreferences.edit().putString(Constants.QuickstartPreferences.REGISTRATION_ID, token);

                Log.i(TAG, "GCM Registration Token: " + token);

                //  sendRegistrationToServer(token);

                sharedPreferences.edit().putBoolean(Constants.QuickstartPreferences.SENT_TOKEN_TO_SERVER, true).apply();
            }
        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
            sharedPreferences.edit().putBoolean(Constants.QuickstartPreferences.SENT_TOKEN_TO_SERVER, false).apply();
        }
    }

}
