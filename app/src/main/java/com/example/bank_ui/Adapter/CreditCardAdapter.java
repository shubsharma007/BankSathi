package com.example.bank_ui.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bank_ui.Model.CreditCardResponse;
import com.example.bank_ui.ProductActivity;
import com.example.bank_ui.R;

import java.util.List;

public class CreditCardAdapter extends RecyclerView.Adapter<CreditCardAdapter.MyViewHolder> {
    Context context;
    List<CreditCardResponse> singleUnit;

    public CreditCardAdapter(Context context, List<CreditCardResponse> singleUnit) {
        this.context = context;
        this.singleUnit = singleUnit;
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

        CreditCardResponse response = singleUnit.get(position);

        Glide.with(context).load(response.getBanklogo()).into(holder.imgLogo);
        holder.txtBankName.setText(response.getCardname());
        holder.txtDisc.setText(response.getDiscription());
        holder.txtEarnUpTo.setText(response.getEarnupto());
        holder.btnQuickView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductActivity.class);

            intent.putExtra("Id", response.getId());

            context.startActivity(intent);
        });
//        holder.shareBtn.setOnClickListener(v -> {
//
//        });
    }

    @Override
    public int getItemCount() {
        return singleUnit.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatButton btnQuickView;
        TextView shareBtn, txtBankName, txtDisc, txtEarnUpTo;
        ImageView imgLogo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btnQuickView = itemView.findViewById(R.id.btnFirstQV);
            shareBtn = itemView.findViewById(R.id.shareBtn);
            imgLogo = itemView.findViewById(R.id.imgLogo);
            txtBankName = itemView.findViewById(R.id.txtBankName);
            txtDisc = itemView.findViewById(R.id.txtDisc);
            txtEarnUpTo = itemView.findViewById(R.id.txtEarnUpTo);
        }
    }
}
