package phoenix.hackfest.orderit.HelperClasses;

/**
 * Created by Jibin_ism on 05-Mar-16.
 */
import android.content.Context;
import android.content.Intent;

public final class Utilities {

    static final String SERVER_URL = "server url";

     static final String SENDER_ID = "434106954057";


    static final String TAG = "AndroidHive GCM";

    static final String DISPLAY_MESSAGE_ACTION =
            "phoenix.heckfest.orderit.DISPLAY_MESSAGE";

    static final String EXTRA_MESSAGE = "message";


    public static void displayMessage(Context context, String message) {
        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
        intent.putExtra(EXTRA_MESSAGE, message);
        context.sendBroadcast(intent);
    }
}
