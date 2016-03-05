package phoenix.hackfest.orderit.CustomViews;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Siddhant Choudhary on 05-03-2016.
 */
public class RupeeEditText extends EditText{

    private EditText self;
    public RupeeEditText(Context context) {
        this(context,null);
    }

    public RupeeEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RupeeEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        self=this;
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                self.setText(RupeeTextView.RUPEE+" "+s+" /-");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
