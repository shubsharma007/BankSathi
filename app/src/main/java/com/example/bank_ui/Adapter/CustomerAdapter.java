package com.example.bank_ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bank_ui.R;

import javax.xml.xpath.XPathFunctionResolver;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

    Context context;

    public CustomerAdapter(Context context) {
        this.context = context;
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
        holder.name.setText("Name - ");
        holder.phone.setText("Contact - ");
    }

    @Override
    public int getItemCount() {
        return 10;
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
