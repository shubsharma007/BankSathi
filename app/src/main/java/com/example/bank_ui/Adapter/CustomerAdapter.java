package com.example.bank_ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bank_ui.Model.GetCustomers;
import com.example.bank_ui.R;

import java.util.List;

import javax.xml.xpath.XPathFunctionResolver;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

    Context context;
    List<GetCustomers> customersList;

    public CustomerAdapter(Context context, List<GetCustomers> customersList) {
        this.context = context;
        this.customersList = customersList;
    }

    @NonNull
    @Override
    public CustomerAdapter.CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_customers, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.CustomerViewHolder holder, int position) {
        GetCustomers singleUnit = customersList.get(position);

        holder.name.setText("Name - " + singleUnit.getFullname());
        holder.phone.setText("Contact - " + singleUnit.getContactno());
    }

    @Override
    public int getItemCount() {
        return customersList.size();
    }

    public static class CustomerViewHolder extends RecyclerView.ViewHolder {
        TextView name, phone;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.rvCustomerName);
            phone = itemView.findViewById(R.id.rvCustomerPhone);
        }
    }
}
