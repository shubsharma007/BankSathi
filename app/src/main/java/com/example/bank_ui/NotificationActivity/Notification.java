
package com.example.bank_ui.NotificationActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.bank_ui.Adapter.NotificationAdapter;
import com.example.bank_ui.R;
import com.example.bank_ui.databinding.ActivityNotificationBinding;

import java.util.ArrayList;
import java.util.List;

public class Notification extends AppCompatActivity {
    ActivityNotificationBinding binding;

    List<String> notificationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        notificationList = new ArrayList<>();
        notificationList.add("payment recieved from mr raj and sons");
        notificationList.add("payment recieved from mr x");
        notificationList.add("payment recieved from mrs kulkarni");
        notificationList.add("payment recieved from mrs pathak");
        notificationList.add("payment recieved from mr solanki");
        notificationList.add("payment recieved from mr chaudhary");
        notificationList.add("payment recieved from mrs joshi");
        notificationList.add("payment recieved from mrs chauhan");
        notificationList.add("payment recieved from mr patel");
        notificationList.add("payment recieved from mr suryawanshi and company pvt ltd shajapur mp india");
        notificationList.add("payment recieved from mrs khanna");


        binding.recyclerView.setAdapter(new NotificationAdapter(Notification.this, notificationList));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(Notification.this));
    }
}