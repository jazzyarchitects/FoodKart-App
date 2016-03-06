package phoenix.hackfest.orderit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import phoenix.hackfest.orderit.Adapters.MyAdapter;
import phoenix.hackfest.orderit.Models.Order;
import phoenix.ism.hackfest.orderit.R;

/**
 * Created by Siddhant Choudhary on 05-03-2016.
 */
public class RecyclerViewActivity extends AppCompatActivity {

    RecyclerView rw;
    MyAdapter myAdapter;
    ArrayList<Order> morders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        morders.get(0).setAdminAddress("Amber Hostel");
        morders.get(0).setAdminName("siddhant choudhary");
        morders.get(0).setCurrentAmount(70.0);
        morders.get(0).setOrderingTime("6:23:44");
        morders.get(0).setRestaurent("Blackberry");
        morders.get(0).setTargetAmount(150.0);
        morders.get(0).setId("11ad");

        morders.get(1).setAdminAddress("Ruby Hostel");
        morders.get(1).setAdminName("Medha Bajpai");
        morders.get(1).setCurrentAmount(65.0);
        morders.get(1).setOrderingTime("8:40:07");
        morders.get(1).setRestaurent("eatsome");
        morders.get(1).setTargetAmount(120.0);
        morders.get(1).setId("1asd");

        morders.get(2).setAdminAddress("Jasper Hostel");
        morders.get(2).setAdminName("Jibin mathew");
        morders.get(2).setCurrentAmount(85.0);
        morders.get(2).setOrderingTime("9:13:43");
        morders.get(2).setRestaurent("Sonotel");
        morders.get(2).setTargetAmount(225.0);
        morders.get(2).setId("ew2d");


        setContentView(R.layout.recycler_view);
        rw=(RecyclerView)findViewById(R.id.rw);
        rw.setLayoutManager(new LinearLayoutManager(this));
        myAdapter=new MyAdapter(this,morders);
        rw.setAdapter(myAdapter);
    }
}
