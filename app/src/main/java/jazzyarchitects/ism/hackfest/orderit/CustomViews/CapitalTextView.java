package jazzyarchitects.ism.hackfest.orderit.CustomViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

import jazzyarchitects.ism.hackfest.orderit.R;

/**
 * Created by Jibin_ism on 03-Mar-16.
 */
public class CapitalTextView extends TextView {

    String thisText = "";
    Context mContext;
    int scaleFactor = 1;
    boolean scaleAllWords = false;

    public CapitalTextView(Context context) {
        this(context, null);
    }

    public CapitalTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CapitalTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init(attrs);
    }


    public void init(AttributeSet attributeSet) {
        TypedArray a = mContext.obtainStyledAttributes(attributeSet, R.styleable.CapitalTextView);
        if (attributeSet != null) {
            scaleFactor = a.getInt(R.styleable.CapitalTextView_letterScaleFactor, 1) - 1;
            scaleAllWords = a.getBoolean(R.styleable.CapitalTextView_focusAllWords, false);
            thisText = this.getText().toString();
            thisText = thisText.toUpperCase();
            String[] strings = thisText.split(" ");
            String finalString = "";
            for (int i = 0; i < strings.length; i++) {
                String string = strings[i];
                int k = 0;
                int protection = 0;
                char c;
                do {
                    c = string.charAt(k);
                    int asciiIndex = (int) c;
                    if ((asciiIndex <= (int) 'Z' && asciiIndex >= (int) 'A') || (asciiIndex >= (int) '0' && asciiIndex <= (int) '9')) {
                        break;
                    }
                    k++;
                    protection++;
                } while (protection <= 100);
                if (i == 0 || (i > 0 && scaleAllWords)) {
                    String startTag = "";
                    String endTag = "";
                    for (int j = 0; j < scaleFactor; j++) {
                        startTag += "<big>";
                        endTag += "</big>";
                    }
                    finalString += startTag + string.toCharArray()[0] + endTag;
//                finalString += "<font size=\"" + scaleFactor * this.getTextSize() + "px\">" + string.toCharArray()[0] + "</font>";
                    for (int j = 1; j < string.length(); j++) {
                        finalString += string.charAt(j);
                    }
                } else {
                    finalString += string;
                }
                finalString += " ";
            }
            this.setText(Html.fromHtml(finalString));
            invalidate();
        }
        a.recycle();
    }
}
