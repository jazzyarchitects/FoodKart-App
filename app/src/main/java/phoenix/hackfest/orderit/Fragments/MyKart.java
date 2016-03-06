package phoenix.hackfest.orderit.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import phoenix.hackfest.orderit.Backend.BackendUrls;
import phoenix.ism.hackfest.orderit.R;

/**
 * Created by Jibin_ism on 05-Mar-16.
 */
public class MyKart extends Fragment {
    public MyKart() {
        // Required empty public constructor
    }
    String TAG_ID="user_id";
    String TAG_REST="restaurant";
    String TAG_ORDER_TIME="order_date";
    String TAG_TARAMT="min_cose";
    String TAG_CURTOT="tot_cost";
    String TAG_ITEM="item";
    String TAG_ADMIN_NAME="admin_name";
    String TAG_ADMIN_ADD="admin_address";
    String TAG_QUANT="quantity";
    String TAG_PRICE="price";


    Context mContext;
    String url = "";
    RecyclerView recyclerView;

    public static MyKart newInstance(String type){
        MyKart myKart = new MyKart();
        Bundle bundle = new Bundle();
        bundle.putString("type",type);
        myKart.setArguments(bundle);
        return myKart;
    }

    public static MyKart newInstance(){
        MyKart myKart = new MyKart();
        Bundle bundle = new Bundle();
        bundle.putString("type","feed");
        myKart.setArguments(bundle);
        return myKart;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext = getActivity();
        String type="";
        try {
            type = getArguments().getString("type");
        }catch (Exception e){
            e.printStackTrace();
        }
        url=type.equalsIgnoreCase("feed")? BackendUrls.ORDER_PREFIX: BackendUrls.ORDER_PREFIX;
        return inflater.inflate(R.layout.fragment_my_kart2, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);


    }
}
