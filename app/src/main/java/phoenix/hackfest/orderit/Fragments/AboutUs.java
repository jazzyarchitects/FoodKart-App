package phoenix.hackfest.orderit.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import phoenix.ism.hackfest.orderit.R;

/**
 * Created by Jibin_ism on 05-Mar-16.
 */
public class AboutUs extends Fragment {
    public AboutUs() {
        // Required empty public constructor
    }

    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button=(Button)view.findViewById(R.id.sendFeedback);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent=new Intent(Intent.ACTION_VIEW);
                sendIntent.setType("plain/text");
                sendIntent.setData(Uri.parse("siddhant07324@gmail.com"));
                sendIntent.setClassName("com.google.android.gm","com.google.android.gm.ComposeActivityGmail");
                sendIntent.putExtra(Intent.EXTRA_SUBJECT,"feedback");
                sendIntent.putExtra(Intent.EXTRA_EMAIL,new String[] {"siddhant07324@gmail.com"});
                startActivity(sendIntent);
            }
        });
    }
}
