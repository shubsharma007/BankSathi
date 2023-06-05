package com.example.bank_ui.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.example.bank_ui.Adapter.CustomerAdapter;
import com.example.bank_ui.ApiInterface.ApiInterface;
import com.example.bank_ui.Model.GetCustomers;
import com.example.bank_ui.R;

import com.example.bank_ui.AddCustomerActivity;

import com.example.bank_ui.Retrofit.RetrofitServices;
import com.example.bank_ui.databinding.FragmentMyStatsBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyStatsFragment extends Fragment {
    FragmentMyStatsBinding binding;
    private static final String TAG = "TAG_MyStatsFragment";

    int cardId, Id;
    SharedPreferences sharedPreferences;
    String from;
    ApiInterface apiInterface;
    Call<List<GetCustomers>> call;

    List<GetCustomers> allCustomers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyStatsBinding.inflate(inflater, container, false);
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        allCustomers = new ArrayList<>();

        binding.loadingCard.setVisibility(View.GONE);
        binding.noDataFound.setVisibility(View.INVISIBLE);
        binding.rvAddCustomer.setVisibility(View.INVISIBLE);

        sharedPreferences = getActivity().getSharedPreferences("bank", Context.MODE_PRIVATE);
        Id = sharedPreferences.getInt("id", 0);
        cardId = this.getArguments().getInt("Id", 0);
        from = getActivity().getIntent().getStringExtra("from");


        Log.d("IDDDDDDD", String.valueOf(cardId));

        binding.addNewCustomer.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddCustomerActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("Id", Id);
            bundle.putString("from", from);
            bundle.putInt("cardId", cardId);
            intent.putExtras(bundle);
            startActivity(intent);

        });


        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getRecyclerView(from, Id, cardId);

    }

    private void getRecyclerView(String from, int Id, int cardId) {

        if (from.equals("CC")) {
            call = apiInterface.getCreditCardCustomers();
        } else if (from.equals("DA")) {
            call = apiInterface.getDematAccountCustomers();
        } else if (from.equals("BA")) {
            call = apiInterface.getBusinessCustomers();
        } else if (from.equals("GL")) {
            call = apiInterface.getGoldLoanCustomers();
        } else if (from.equals("PL")) {
            call = apiInterface.getPersonalLoanCustomers();
        } else if (from.equals("IN")) {
            call = apiInterface.getInsuranceCustomers();
        }

        binding.loadingCard.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<List<GetCustomers>>() {
            @Override
            public void onResponse(Call<List<GetCustomers>> call, Response<List<GetCustomers>> response) {
                if (response.isSuccessful()) {
                    binding.loadingCard.setVisibility(View.GONE);

                    allCustomers.clear();

                    for (GetCustomers customer : response.body()) {
                        if (customer.getCreate_by() == Id && customer.getCardid() == cardId) {
                            allCustomers.add(customer);
                        }
                    }

                    if(allCustomers.size()>0)
                    {
                        binding.noDataFound.setVisibility(View.INVISIBLE);
                        binding.rvAddCustomer.setVisibility(View.VISIBLE);
                        binding.rvAddCustomer.setLayoutManager(new LinearLayoutManager(getContext()));
                        binding.rvAddCustomer.setAdapter(new CustomerAdapter(getContext(),allCustomers));
                    }else {
                        binding.noDataFound.setVisibility(View.VISIBLE);
                        binding.rvAddCustomer.setVisibility(View.INVISIBLE);
                    }


                } else {
                    binding.loadingCard.setVisibility(View.GONE);
                    binding.noDataFound.setVisibility(View.VISIBLE);
                    binding.rvAddCustomer.setVisibility(View.INVISIBLE);
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, response.message());
                }
            }

            @Override
            public void onFailure(Call<List<GetCustomers>> call, Throwable t) {
                binding.loadingCard.setVisibility(View.GONE);
                binding.noDataFound.setVisibility(View.VISIBLE);
                binding.rvAddCustomer.setVisibility(View.INVISIBLE);

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, t.getMessage());
            }
        });


    }
}