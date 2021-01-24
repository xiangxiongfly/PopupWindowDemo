package com.example.popupwindowdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView btnShare;
    private Button btnQQ;
    private Context context;
    private LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initViews();
    }

    private void initViews() {
        root = findViewById(R.id.root);
        btnShare = findViewById(R.id.btnShare);
        btnQQ = findViewById(R.id.btnQQ);
        btnShare.setOnClickListener(this);
        btnQQ.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnShare:
                initShareWindow();
                break;
            case R.id.btnQQ:
                initQQWindow();
                break;
        }
    }

    private void initShareWindow() {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_share, null);
        TextView tvQQ = view.findViewById(R.id.tvQQ);
        TextView tvWx = view.findViewById(R.id.tvWx);
        TextView tvSina = view.findViewById(R.id.tvSina);
        PopupWindow sharePop = new PopupWindow(view, UIUtils.dp2px(context, 200), ViewGroup.LayoutParams.WRAP_CONTENT, true);
        sharePop.setBackgroundDrawable(new ColorDrawable(0x00000000));
        sharePop.showAsDropDown(btnShare, -20, 50, Gravity.RIGHT);
        tvQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "QQ", Toast.LENGTH_SHORT).show();
                sharePop.dismiss();
            }
        });
        tvWx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "微信", Toast.LENGTH_SHORT).show();
                sharePop.dismiss();
            }
        });
        tvSina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "新浪", Toast.LENGTH_SHORT).show();
                sharePop.dismiss();
            }
        });
    }

    private void initQQWindow() {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_qq, null);
        TextView cancel = view.findViewById(R.id.cancel);
        PopupWindow qqPop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        qqPop.setBackgroundDrawable(new ColorDrawable());
        qqPop.setAnimationStyle(R.style.AnimPop);
        qqPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                isAlpha(false);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qqPop.dismiss();
            }
        });
        qqPop.showAtLocation(root, Gravity.BOTTOM, 0, 0);
        isAlpha(true);
    }

    private void isAlpha(boolean is) {
        if (is) {
            alphaBackground(0.6f);
        } else {
            alphaBackground(1f);
        }
    }

    private void alphaBackground(float alpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = alpha;
        getWindow().setAttributes(lp);
    }

}