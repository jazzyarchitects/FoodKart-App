package phoenix.hackfest.orderit.Dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;

import phoenix.hackfest.orderit.CustomViews.RupeeTextView;
import phoenix.hackfest.orderit.HelperClasses.Constants;
import phoenix.hackfest.orderit.Models.FoodOrder;
import phoenix.hackfest.orderit.Models.Participant;
import phoenix.ism.hackfest.orderit.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrderList extends DialogFragment {


    RecyclerView recyclerView;
    ArrayList<Participant> participants;
    String orderId = "";

    public static MyOrderList newInstance(ArrayList<Participant> participants, String orderId) {
        MyOrderList myOrderList = new MyOrderList();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("participants", participants);
        bundle.putString("orderId", orderId);
        myOrderList.setArguments(bundle);
        return myOrderList;
    }

    public MyOrderList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            participants = bundle.getParcelableArrayList("participants");
            orderId = bundle.getString("orderId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_order_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        RecyclerAdapter adapter = new RecyclerAdapter(getActivity(), participants);
        recyclerView.setAdapter(adapter);

    }

    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        Context mContext;
        ArrayList<Participant> participants;

        public RecyclerAdapter(Context context, ArrayList<Participant> participants) {
            this.mContext = context;
            this.participants = participants;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = View.inflate(mContext, R.layout.user_details_order_item, parent);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            final Participant participant = participants.get(position);
            holder.userName.setText(participant.getUser().getName());
            holder.userAddress.setText(participant.getUser().getAddress());
            holder.rupeeTextView.setAmount(participant.getTotalAmount());
            holder.userPaid.setChecked(participant.isPaid());
            ArrayList<FoodOrder> foodOrders = participant.getFoodOrders();
            String s = "";
            for (FoodOrder order : foodOrders) {
                s += order.getListing();
            }
            holder.userPaid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox self = (CheckBox) v;
                    participants.get(position).setPaid(self.isChecked());
                }
            });
            holder.userOrder.setText(s);
            holder.callUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Confirm call");
                    builder.setMessage("Are you sure you want to call " + participant.getUser().getName() + " ?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            try {
                                intent.setData(Uri.parse("tel:" + participant.getUser().getPhone()));
                                startActivity(intent);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).
                            setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return participants == null ? 0 : participants.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView userName, userAddress, userOrder;
            RupeeTextView rupeeTextView;
            CheckBox userPaid;
            ImageButton callUser;

            public ViewHolder(View itemView) {
                super(itemView);

                userName = (TextView) itemView.findViewById(R.id.userName);
                userAddress = (TextView) itemView.findViewById(R.id.userAddress);
                userOrder = (TextView) itemView.findViewById(R.id.orderDetails);
                rupeeTextView = (RupeeTextView) itemView.findViewById(R.id.amountDue);
                userPaid = (CheckBox) itemView.findViewById(R.id.userPaid);
                callUser = (ImageButton) itemView.findViewById(R.id.callUser);


            }
        }
    }

    @Override
    public void dismiss() {

        JSONArray jsonArray = new JSONArray();
        for (Participant participant : participants) {
            jsonArray.put(participant.toJSONString());
        }
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.USER_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit()
                .putString(String.valueOf(orderId), jsonArray.toString())
                .apply();

        super.dismiss();
    }
}
