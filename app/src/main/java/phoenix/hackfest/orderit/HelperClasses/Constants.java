package phoenix.hackfest.orderit.HelperClasses;

/**
 * Created by Jibin_ism on 03-Mar-16.
 */
public class Constants {

    public static boolean isValidEmail(CharSequence target) {
        return (target != null) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

}
