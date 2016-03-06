package phoenix.hackfest.orderit.Fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import phoenix.hackfest.orderit.Backend.BackendUrls;
import phoenix.hackfest.orderit.Models.FoodOrder;
import phoenix.hackfest.orderit.Models.Order;
import phoenix.hackfest.orderit.Services.BackendInterfacer;
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


        BackendInterfacer backendInterfacer=new BackendInterfacer(mContext,url,"GET",null);
        backendInterfacer.setSimpleBackendListener(new BackendInterfacer.SimpleBackendListener() {

            ProgressDialog progressDialog;
            @Override
            public void onPreExecute() {
                progressDialog=new ProgressDialog(mContext);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Searching sharers");
                progressDialog.setCancelable(false);
                progressDialog.show();

            }

            @Override
            public void onResult(String result) {
                if(progressDialog!=null && progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    ArrayList<Order> orders=new ArrayList<Order>();
                    ArrayList<FoodOrder> food=new ArrayList<FoodOrder>();

                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        Order mOrders=new Order();
                        FoodOrder mfood=new FoodOrder();
                        mOrders.setId(jsonObject.getString(TAG_ID));
                        mOrders.setCurrentAmount(Double.parseDouble(jsonObject.getString(TAG_CURTOT)));
                        mOrders.setOrderingTime(jsonObject.getString(TAG_ORDER_TIME));
                        mOrders.setTargetAmount(Double.parseDouble(jsonObject.getString(TAG_TARAMT)));
                        mOrders.setRestaurent(jsonObject.getString(TAG_REST));
                        mOrders.setAdminName(jsonObject.getString(TAG_ADMIN_NAME));
                        mOrders.setAdminAddress(jsonObject.getString(TAG_ADMIN_ADD));
                        mfood.setItemName(jsonObject.getString(TAG_ITEM));
                        mfood.setQty(Integer.parseInt(jsonObject.getString(TAG_QUANT)));
                        mfood.setCost(Double.parseDouble(jsonObject.getString(TAG_PRICE)));

                        orders.set(i,mOrders);
                        food.set(i,mfood);
                    }








                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        backendInterfacer.execute();


    }
}
