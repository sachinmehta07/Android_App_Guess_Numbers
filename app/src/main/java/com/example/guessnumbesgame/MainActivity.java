package com.example.guessnumbesgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView levelsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        levelsRecyclerView = findViewById(R.id.levelsRecyclerView);
        levelsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Level> levels = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            levels.add(new Level(i));
        }

        LevelsAdapter levelsAdapter = new LevelsAdapter(levels, new LevelsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int levelNumber) {
                  openSecondActivity(levelNumber);
            }
        });
        levelsRecyclerView.setAdapter(levelsAdapter);
    }

    private void openSecondActivity(int levelNumber) {
        int boxCount = levelNumber * 4;
        int halfBoxCount = boxCount / 2;
        ArrayList<Integer> numbersInBoxes = new ArrayList<>();

        for (int i = 0; i < boxCount; i++) {
            int number = (i % halfBoxCount) + 1;
            numbersInBoxes.add(number);
        }


        Intent intent = new Intent(this, SecondActivity.class);
        intent.putIntegerArrayListExtra("numbersInBoxes", numbersInBoxes);
        startActivity(intent);
    }
}