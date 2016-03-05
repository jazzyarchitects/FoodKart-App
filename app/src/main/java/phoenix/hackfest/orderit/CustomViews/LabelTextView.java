package phoenix.hackfest.orderit.CustomViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Siddhant Choudhary on 05-03-2016.
 */
public class LabelTextView extends TextView {

    public LabelTextView(Context context) {
        this(context,null);
    }

    public LabelTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LabelTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){

    }
}
