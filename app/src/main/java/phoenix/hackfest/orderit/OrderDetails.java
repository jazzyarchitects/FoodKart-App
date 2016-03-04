package phoenix.hackfest.orderit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import phoenix.hackfest.orderit.CustomViews.CheckoutList;
import phoenix.hackfest.orderit.Models.FoodOrder;
import phoenix.ism.hackfest.orderit.R;

/**
 * Created by Siddhant Choudhary on 05-03-2016.
 */
public class OrderDetails extends AppCompatActivity{

    TextView restName, orderTime, orderby, address, curTotal, tarAmt;
    EditText item, qty, cost;
    Button add, details;
    FoodOrder foodOrder;
    CheckoutList checkoutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details);

        checkoutList = (CheckoutList) findViewById(R.id.checkoutList);

        restName=(TextView)findViewById(R.id.restName);
        orderTime=(TextView)findViewById(R.id.orderTime);
        orderby=(TextView)findViewById(R.id.orderedBy);
        address=(TextView)findViewById(R.id.address);
        curTotal=(TextView)findViewById(R.id.curTotal);
        tarAmt=(TextView)findViewById(R.id.tarAmt);

        item=(EditText)findViewById(R.id.item);
        qty=(EditText)findViewById(R.id.qty);
        cost=(EditText)findViewById(R.id.cost);

        add=(Button)findViewById(R.id.add);
        details=(Button)findViewById(R.id.details);

        add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                foodOrder=new FoodOrder(item.getText().toString(),
                        Double.parseDouble(cost.getText().toString().isEmpty()?"0":cost.getText().toString()), Integer.parseInt(qty.getText().toString().isEmpty()?"0":qty.getText().toString()));
                checkoutList.addFoodOrder(foodOrder);
            }
        });

    }
}
