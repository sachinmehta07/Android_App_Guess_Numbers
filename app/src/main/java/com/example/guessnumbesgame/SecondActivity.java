package com.example.guessnumbesgame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private GridLayout boxesGridLayout;
    private ArrayList<Integer> numbersInBoxes;
    private int firstClickedNumber = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        boxesGridLayout = findViewById(R.id.boxesGridLayout);

        numbersInBoxes = getIntent().getIntegerArrayListExtra("numbersInBoxes");
        int rowCount = (int) Math.ceil(numbersInBoxes.size() / 4.0);

        boxesGridLayout.setRowCount(rowCount);

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < 4; j++) {
                int index = i * 4 + j;
                if (index < numbersInBoxes.size()) {

                    int number = numbersInBoxes.get(index);
                    View boxView = createBoxView(number, index);
                    GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                    layoutParams.width = getResources().getDimensionPixelSize(R.dimen.box_size);
                    layoutParams.height = getResources().getDimensionPixelSize(R.dimen.box_size);
                    layoutParams.rowSpec = GridLayout.spec(i);
                    layoutParams.columnSpec = GridLayout.spec(j);
                    boxView.setLayoutParams(layoutParams);

                    boxesGridLayout.addView(boxView);

                }
            }
        }
    }

    private View createBoxView(int number, int index) {
        TextView boxView = new TextView(this);
        boxView.setText(String.valueOf(number));
        boxView.setClickable(true);
        boxView.setGravity(Gravity.CENTER);

        // Set background drawable with blue border and transparent fill color
        int borderColor = Color.BLUE;
        int borderWidth = getResources().getDimensionPixelSize(R.dimen.box_border_width);
        GradientDrawable borderDrawable = new GradientDrawable();
        borderDrawable.setStroke(borderWidth, borderColor);
        boxView.setBackground(borderDrawable);

        boxView.setTextColor(Color.WHITE); // Set text color to make it visible

        boxView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondActivity.this.onBoxClicked(number);
            }
        });

        return boxView;
    }
    private void onBoxClicked(int number) {
        if (firstClickedNumber == -1) {
            // First box clicked
            firstClickedNumber = number;
        } else {
            // Second box clicked
            if (firstClickedNumber == number) {
                showToast("Numbers are matched!");
            } else {
                showToast("Number is not matched!");
            }
            firstClickedNumber = -1; // Reset for the next pair of clicks
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}