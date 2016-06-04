package com.example.ivan.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Timer currentTimer = new Timer();
    private int timeCounter = 0;

    boolean isRunning = false;

    private TextView timerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = (TextView) findViewById(R.id.timerTextView);

        final Button startButton = (Button) findViewById(R.id.startButton);
        if (startButton != null) {
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isRunning) {
                        startButton.setText(R.string.start);
                        stopTimer();
                    } else {
                        startButton.setText(R.string.pause);
                        startTimer(timeCounter);
                    }

                    isRunning = !isRunning;
                }
            });
        }
        Button resetButton = (Button) findViewById(R.id.stopButton);
        if (resetButton != null) {
            resetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stopTimer();
                    timeCounter = 0;
                    startTimer(timeCounter);
                }
            });
        }
    }

    private void stopTimer() {
        currentTimer.cancel();
        currentTimer.purge();
    }

    private void startTimer(int startTime) {
        currentTimer = new Timer();
        currentTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timerTextView.setText("" + timeCounter);
                        timeCounter++;
                    }
                });
            }
        }, 1000, 1000);
    }
}
