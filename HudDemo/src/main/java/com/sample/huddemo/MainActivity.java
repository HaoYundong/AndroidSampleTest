package com.sample.huddemo;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button indeterminate = (Button) findViewById(R.id.indeterminate);
        indeterminate.setOnClickListener(this);

        Button labelIndeterminate = (Button) findViewById(R.id.label_indeterminate);
        labelIndeterminate.setOnClickListener(this);

        Button detailIndeterminate = (Button) findViewById(R.id.detail_indeterminate);
        detailIndeterminate.setOnClickListener(this);

        Button graceIndeterminate = (Button) findViewById(R.id.grace_indeterminate);
        graceIndeterminate.setOnClickListener(this);

        Button determinate = (Button) findViewById(R.id.determinate);
        determinate.setOnClickListener(this);

        Button annularDeterminate = (Button) findViewById(R.id.annular_determinate);
        annularDeterminate.setOnClickListener(this);

        Button barDeterminate = (Button) findViewById(R.id.bar_determinate);
        barDeterminate.setOnClickListener(this);

        Button customView = (Button) findViewById(R.id.custom_view);
        customView.setOnClickListener(this);

        Button dimBackground = (Button) findViewById(R.id.dim_background);
        dimBackground.setOnClickListener(this);

        Button customColor = (Button) findViewById(R.id.custom_color_animate);
        customColor.setOnClickListener(this);

        Button onlyText = findViewById(R.id.custom_only_text);
        onlyText.setOnClickListener(this);
    }

    private KProgressHUD hud;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.indeterminate:
                hud = KProgressHUD.create(this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
                scheduleDismiss();
                break;
            case R.id.label_indeterminate:
                hud = KProgressHUD.create(this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setCancellable(new DialogInterface.OnCancelListener()
                        {
                            @Override public void onCancel(DialogInterface
                                                                   dialogInterface)
                            {
                                Toast.makeText(MainActivity.this, "You " +
                                        "cancelled manually!", Toast
                                        .LENGTH_SHORT).show();
                            }
                        });

                scheduleDismiss();
                break;
            case R.id.detail_indeterminate:
                hud = KProgressHUD.create(this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setDetailsLabel("Downloading data");
                scheduleDismiss();
                break;
            case R.id.grace_indeterminate:
                hud = KProgressHUD.create(this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setGraceTime(1000);
                scheduleDismiss();
                break;
            case R.id.determinate:
                hud = KProgressHUD.create(MainActivity.this)
                        .setStyle(KProgressHUD.Style.PIE_DETERMINATE)
                        .setLabel("Please wait");
                simulateProgressUpdate();
                break;
            case R.id.annular_determinate:
                hud = KProgressHUD.create(MainActivity.this)
                        .setStyle(KProgressHUD.Style.ANNULAR_DETERMINATE)
                        .setLabel("Please wait")
                        .setDetailsLabel("Downloading data");
                simulateProgressUpdate();
                break;
            case R.id.bar_determinate:
                hud = KProgressHUD.create(MainActivity.this)
                        .setStyle(KProgressHUD.Style.BAR_DETERMINATE)
                        .setLabel("Please wait");
                simulateProgressUpdate();
                break;
            case R.id.custom_view:
                ImageView imageView = new ImageView(this);
                imageView.setBackgroundResource(R.drawable.spin_animation);
                AnimationDrawable drawable = (AnimationDrawable) imageView.getBackground();
                drawable.start();
                hud = KProgressHUD.create(this)
                        .setCustomView(imageView)
                        .setLabel("This is a custom view");
                scheduleDismiss();
                break;
            case R.id.dim_background:
                hud = KProgressHUD.create(this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setDimAmount(0.5f);
                scheduleDismiss();
                break;
            case R.id.custom_color_animate:
                //noinspection deprecation
                hud = KProgressHUD.create(this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setWindowColor(getResources().getColor(R.color.colorPrimary))
                        .setAnimationSpeed(2);
                scheduleDismiss();
                break;
            case R.id.custom_only_text:
                TextView textView = new TextView(this);
                textView.setText("");
                textView.setTextColor(Color.parseColor("#ffffff"));

                View emptyView = new View(this);
                emptyView.setLayoutParams(new LinearLayout.LayoutParams(1, 1));
                hud = KProgressHUD.create(this)
                        .setCustomView(textView)
                        .setLabel("你好")
                        .show();

                scheduleDismiss();
                break;
        }

        hud.show();
    }

    private void simulateProgressUpdate() {
        hud.setMaxProgress(100);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            int currentProgress;
            @Override
            public void run() {
                currentProgress += 1;
                hud.setProgress(currentProgress);
                if (currentProgress == 80) {
                    hud.setLabel("Almost finish...");
                }
                if (currentProgress < 100) {
                    handler.postDelayed(this, 50);
                }
            }
        }, 100);
    }

    private void scheduleDismiss() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hud.dismiss();
            }
        }, 2000);
    }
}
