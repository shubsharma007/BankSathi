package com.example.bank_ui.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bank_ui.ProductActivity;
import com.example.bank_ui.R;

public class CreditCardAdapter extends RecyclerView.Adapter<CreditCardAdapter.MyViewHolder> {
    Context context;

    public CreditCardAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CreditCardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_credit_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreditCardAdapter.MyViewHolder holder, int position) {
        holder.btnQuickView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductActivity.class);
            context.startActivity(intent);
        });
//        holder.shareBtn.setOnClickListener(v -> {
//
//        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatButton btnQuickView;
//        TextView shareBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btnQuickView = itemView.findViewById(R.id.btnFirstQV);

            shareBtn = itemView.findViewById(R.id.shareBtn);
        }
    }
}
