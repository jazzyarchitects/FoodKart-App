package phoenix.hackfest.orderit;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import phoenix.ism.hackfest.orderit.R;

/**
 * Created by Jibin_ism on 05-Mar-16.
 */
public class MyKart extends Fragment {
    public MyKart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_kart2, container, false);
    }
}
