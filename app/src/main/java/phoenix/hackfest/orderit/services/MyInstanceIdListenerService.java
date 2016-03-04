package phoenix.hackfest.orderit.Services;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

public class MyInstanceIdListenerService extends InstanceIDListenerService {

    private static final String TAG = "MyInstanceIDLS";

    @Override
    public void onTokenRefresh() {
        // Fetch updated Instance ID token and notify our app's server of any changes (if applicable).
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }

}
