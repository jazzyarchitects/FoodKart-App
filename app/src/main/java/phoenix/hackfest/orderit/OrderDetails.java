package phoenix.hackfest.orderit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import phoenix.hackfest.orderit.CustomViews.CheckoutList;
import phoenix.hackfest.orderit.Dialogs.MyOrderList;
import phoenix.hackfest.orderit.Models.FoodOrder;
import phoenix.hackfest.orderit.Models.Order;
import phoenix.ism.hackfest.orderit.R;

/**
 * Created by Siddhant Choudhary on 05-03-2016.
 */
public class OrderDetails extends AppCompatActivity {

    TextView restName, orderTime, orderby, address, curTotal, tarAmt;
    EditText item, qty, cost;
    Button details;
    FoodOrder foodOrder;
    Toolbar toolbar;
    ImageButton add;
    CheckoutList checkoutList;
    Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details);

        Intent intent = getIntent();
        order=intent.getParcelableExtra("order");


        checkoutList = (CheckoutList) findViewById(R.id.checkoutList);

        toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("" );

        restName = (TextView) findViewById(R.id.restName);
        orderTime = (TextView) findViewById(R.id.orderTime);
        orderby = (TextView) findViewById(R.id.orderedBy);
        address = (TextView) findViewById(R.id.address);
//        curTotal=(TextView)findViewById(R.id.curTotal);
//        tarAmt=(TextView)findViewById(R.id.tarAmt);

        item = (EditText) findViewById(R.id.item);
        qty = (EditText) findViewById(R.id.qty);
        cost = (EditText) findViewById(R.id.cost);

        add = (ImageButton) findViewById(R.id.add);
        details = (Button) findViewById(R.id.details);


        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOrderList myOrderList=MyOrderList.newInstance(order.getParticipants(),order.getId());
                myOrderList.show(getFragmentManager(),"");
            }
        });

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(item.getText().toString().isEmpty() || qty.getText().toString().isEmpty() || cost.getText().toString().isEmpty()){
                    showInvalid();
                }else {
                    if(Integer.parseInt(qty.getText().toString())==0 || Double.parseDouble(cost.getText().toString())==0){
                        showInvalid();
                    }
                    foodOrder = new FoodOrder(item.getText().toString(),
                            Double.parseDouble(cost.getText().toString().isEmpty() ? "0" : cost.getText().toString()), Integer.parseInt(qty.getText().toString().isEmpty() ? "0" : qty.getText().toString()));
                    cost.setText("");
                    item.setText("");
                    qty.setText("");
                    checkoutList.addFoodOrder(foodOrder);
                }
            }
        });
    }

    public void showInvalid(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Invalid Order")
                .setMessage("Not a valid item or quantity or cost")
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if(id==android.R.id.home){
//            NavUtils.navigateUpFromSameTask(this);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
