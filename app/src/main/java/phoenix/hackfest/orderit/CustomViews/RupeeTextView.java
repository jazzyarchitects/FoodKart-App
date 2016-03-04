package phoenix.hackfest.orderit.CustomViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Jibin_ism on 04-Mar-16.
 */
public class RupeeTextView extends TextView {


    public static String RUPEE = "₹";
    private double amount = 0;

    public RupeeTextView(Context context) {
        this(context, null);
    }

    public RupeeTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RupeeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        String s= this.getText().toString();
        try{
            amount = Double.parseDouble(s);
        }catch (Exception e){
            amount=0.0;
        }
        this.setText(RUPEE+" "+amount+" /-");

    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
        invalidate();
    }
}
