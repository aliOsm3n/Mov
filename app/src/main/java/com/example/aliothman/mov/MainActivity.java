package com.example.aliothman.mov;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Adaptor adaptor;
    List<Movie> movieList = new ArrayList<>();
    Button bat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bat = findViewById(R.id.bat);
        bat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
        listView = findViewById(R.id.ListMovies);
    }


    @Override
    protected void onStart() {
        super.onStart();
        adaptor = new Adaptor(setData(), this);
       // listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(adaptor);
    }

    List<Movie> setData() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        return dataBaseHelper.listProducts();
    }
}
