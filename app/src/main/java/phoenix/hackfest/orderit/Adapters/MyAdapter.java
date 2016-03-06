package phoenix.hackfest.orderit.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import phoenix.hackfest.orderit.Models.Order;
import phoenix.hackfest.orderit.OrderDetails;
import phoenix.ism.hackfest.orderit.R;

/**
 * Created by Siddhant Choudhary on 05-03-2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    ArrayList<Order> orders;
    Context mContext;
    String TAG_POS="pos";


    public MyAdapter(Context context, ArrayList<Order> orders) {
        super();
        this.mContext = context;
        this.orders = orders;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView RestName, ordertime1,orderBy, curAmount, tarAmount;
        View v;


        public ViewHolder(View itemView) {
            super(itemView);
            RestName=(TextView)itemView.findViewById(R.id.name);
            ordertime1=(TextView)itemView.findViewById(R.id.orderTime);
            orderBy=(TextView)itemView.findViewById(R.id.orderedBy);
            curAmount=(TextView)itemView.findViewById(R.id.curVal);
            tarAmount=(TextView)itemView.findViewById(R.id.tarVal);

            v=itemView;

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.front_screen,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Order order=orders.get(position);
        String rest=order.getRestaurent();
        String orderTime=order.getOrderingTime();
        Double curAmt=order.getCurrentAmount();
        Double TarAmt=order.getTargetAmount();

        String adminadd=order.getAdminAddress();
        String adminame=order.getAdminName();


        holder.orderBy.setText(adminame.concat(", "+adminadd));
        holder.RestName.setText(rest);
        holder.ordertime1.setText(orderTime);
        holder.curAmount.setText(curAmt.toString());
        holder.tarAmount.setText(TarAmt.toString());

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, OrderDetails.class);
                i.putExtra("order", order);
                i.putExtra("pos", position);
                mContext.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

}
