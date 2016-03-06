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
        setContentView(R.layout.recycler_view);
        rw=(RecyclerView)findViewById(R.id.rw);
        rw.setLayoutManager(new LinearLayoutManager(this));
        myAdapter=new MyAdapter(this,morders);
        rw.setAdapter(myAdapter);
    }
}
