package com.example.demo1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityA extends AppCompatActivity {
    public static String TAG = "Activity A";
    public Message mMessage;

    private Button mStartB;
    private Button mStartC;
    private Button mFinish;
    private Button mDialog;
    private TextView mLifecycle;
    private TextView mActivityStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        Log.d(TAG, "onCreate()");

        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }

        mMessage.AddMessage(TAG + ".onCreate()\n");

        mStartB = findViewById(R.id.a_start_b);
        mStartC = findViewById(R.id.a_start_c);
        mFinish = findViewById(R.id.a_finish_a);
        mDialog = findViewById(R.id.a_dialog);
        mLifecycle = findViewById(R.id.a_lifecycle_method_list);
        mActivityStatus = findViewById(R.id.a_activity_status);

        mLifecycle.setText(Message.LifecycleMethodList);
        mLifecycle.setMovementMethod(ScrollingMovementMethod.getInstance());
        mActivityStatus.setText((Message.ActivityStatus + TAG + ": Resumed"));

        setOnclick();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMessage.AddMessage(TAG + ".onStart()\n");
        Log.d(TAG, "onStart()");
        mLifecycle.setText(Message.LifecycleMethodList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMessage.AddMessage(TAG + ".onResume()\n");
        Log.d(TAG, "onResume()");
        mLifecycle.setText(Message.LifecycleMethodList);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMessage.AddMessage(TAG + ".onPause()\n");
        Log.d(TAG, "onPause()");
        mLifecycle.setText(Message.LifecycleMethodList);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMessage.AddMessage(TAG + ".onStop()\n");
        Log.d(TAG, "onStop()");
        mLifecycle.setText(Message.LifecycleMethodList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMessage.AddMessage(TAG + ".onDestory()\n");
        Log.d(TAG, "onDestory()");
        mLifecycle.setText(Message.LifecycleMethodList);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mMessage.AddMessage(TAG + ".onRestart()\n");
        Log.d(TAG, "onRestart()");
        mLifecycle.setText(Message.LifecycleMethodList);
        mActivityStatus.setText((Message.ActivityStatus + TAG + ": Resumed"));
    }

    private void setOnclick(){
        ActivityA.OnClick onClick = new ActivityA.OnClick();

        mStartB.setOnClickListener(onClick);
        mStartC.setOnClickListener(onClick);
        mFinish.setOnClickListener(onClick);
        mDialog.setOnClickListener(onClick);
    }

    @Override
    public void finish() {
        super.finish();
        mMessage.AddMessage(TAG + ".finish()\n");
        Log.d(TAG, "finish()");
        mLifecycle.setText(Message.LifecycleMethodList);
    }

    private class OnClick implements View.OnClickListener {
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()){
                case R.id.a_start_b:
                    intent = new Intent(ActivityA.this, ActivityB.class);
                    Message.AddActivity(TAG, true);
                    startActivity(intent);
                    break;
                case R.id.a_start_c:
                    intent = new Intent(ActivityA.this, ActivityC.class);
                    Message.AddActivity(TAG, true);
                    startActivity(intent);
                    break;
                case R.id.a_finish_a:
                    // TODO: finish A
                    Message.AddActivity(TAG, false);
                    finish();
                    break;
                case R.id.a_dialog:
                    onPause();
                    mActivityStatus.setText((Message.ActivityStatus + TAG + ": Paused"));
                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityA.this);
                    builder.setMessage("Simple Dialog");
                    builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            onResume();
                            mActivityStatus.setText((Message.ActivityStatus + TAG + ": Resumed"));
                        }
                    });
                    builder.show();
                    break;
            }
        }
    }
}
