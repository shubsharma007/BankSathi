package com.example.bank_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.example.bank_ui.databinding.ActivityTeamDashboardBinding;
import com.ms.square.android.expandabletextview.ExpandableTextView;

public class TeamDashboardActivity extends AppCompatActivity {

    ActivityTeamDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeamDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        settext();
        setexpandcollapse();
        setonclick();

    }

    private void setonclick() {
        binding.btnExpandCollapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandCollapseTextView();
            }
        });
        binding.btnExpandCollapse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandCollapseTextView1();
            }
        });
        binding.btnExpandCollapse2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandCollapseTextView2();
            }
        });
        binding.btnExpandCollapse3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandCollapseTextView3();
            }
        });
        binding.btnExpandCollapse4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandCollapseTextView4();
            }
        });
        binding.btnExpandCollapse5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandCollapseTextView5();
            }
        });


        binding.buttonBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void setexpandcollapse() {

        expandCollapseTextView();
        expandCollapseTextView1();
        expandCollapseTextView2();
        expandCollapseTextView3();
        expandCollapseTextView4();
        expandCollapseTextView5();

    }

    private void settext() {

        binding.tvExpandable.setText("30% special discount\n" +
                "TechPanda Company ");
        binding.tvExpandable1.setText("Top to E-wallet successful\n" +
                "TechPanda Company ");
        binding.tvExpandable2.setText("Credit Card Connected\n" +
                "TechPanda Company ");
        binding.tvExpandable3.setText("Account setup successful\n" +
                "TechPanda Company ");
        binding.tvExpandable4.setText("Top to E-wallet successful\n" +
                "TechPanda Company ");
        binding.tvExpandable5.setText("Credit Card Connected\n" +
                "TechPanda Company ");


    }

    private void expandCollapseTextView() {
        if (binding.tvExpandable.getMaxLines() == 1) {
            binding.tvExpandable.setMaxLines(Integer.MAX_VALUE);
            binding.btnExpandCollapse.setBackgroundResource(R.drawable.ic_minus);
//            binding.btnExpandCollapse.setText("Collapse");
        } else {
            binding.tvExpandable.setMaxLines(1);
            binding.btnExpandCollapse.setBackgroundResource(R.drawable.ic_plus_black);
//            binding.btnExpandCollapse.setText("Expand");
        }
    }

    private void expandCollapseTextView1() {
        if (binding.tvExpandable1.getMaxLines() == 1) {
            binding.tvExpandable1.setMaxLines(Integer.MAX_VALUE);
            binding.btnExpandCollapse1.setBackgroundResource(R.drawable.ic_minus);
        } else {
            binding.tvExpandable1.setMaxLines(1);
            binding.btnExpandCollapse1.setBackgroundResource(R.drawable.ic_plus_black);

        }
    }

    private void expandCollapseTextView2() {
        if (binding.tvExpandable2.getMaxLines() == 1) {
            binding.tvExpandable2.setMaxLines(Integer.MAX_VALUE);
            binding.btnExpandCollapse2.setBackgroundResource(R.drawable.ic_minus);
        } else {
            binding.tvExpandable2.setMaxLines(1);
            binding.btnExpandCollapse2.setBackgroundResource(R.drawable.ic_plus_black);

        }
    }

    private void expandCollapseTextView3() {
        if (binding.tvExpandable3.getMaxLines() == 1) {
            binding.tvExpandable3.setMaxLines(Integer.MAX_VALUE);
            binding.btnExpandCollapse3.setBackgroundResource(R.drawable.ic_minus);
        } else {
            binding.tvExpandable3.setMaxLines(1);
            binding.btnExpandCollapse3.setBackgroundResource(R.drawable.ic_plus_black);

        }
    }

    private void expandCollapseTextView4() {
        if (binding.tvExpandable4.getMaxLines() == 1) {
            binding.tvExpandable4.setMaxLines(Integer.MAX_VALUE);
            binding.btnExpandCollapse4.setBackgroundResource(R.drawable.ic_minus);
        } else {
            binding.tvExpandable4.setMaxLines(1);
            binding.btnExpandCollapse4.setBackgroundResource(R.drawable.ic_plus_black);

        }
    }

    private void expandCollapseTextView5() {
        if (binding.tvExpandable5.getMaxLines() == 1) {
            binding.tvExpandable5.setMaxLines(Integer.MAX_VALUE);
            binding.btnExpandCollapse5.setBackgroundResource(R.drawable.ic_minus);
        } else {
            binding.tvExpandable5.setMaxLines(1);
            binding.btnExpandCollapse5.setBackgroundResource(R.drawable.ic_plus_black);

        }
    }
}