package com.example.listcity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    EditText editCity;
    Button button1,button2;
    int selectedIndex = -1; //-1 means nothing is selected

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cityList = findViewById(R.id.city_list);
        editCity = findViewById(R.id.editCity);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);


        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka","New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content,R.id.content_view, dataList);
        cityList.setAdapter(cityAdapter);

        //single selection + storing index
        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedIndex = position;
            cityList.setItemChecked(position, true);
        });
        //Add functionality to button 1(Add)
        button1.setOnClickListener(v -> {
            String newCity = editCity.getText().toString().trim();
            if (newCity.isEmpty()){
                Toast.makeText(this, "Enter City Name", Toast.LENGTH_SHORT).show();
                return;
            }
            dataList.add(newCity);
            cityAdapter.notifyDataSetChanged();
            editCity.setText("");
        });
        //Add Functionality to button 2(delete)
        button2.setOnClickListener(v -> {
           dataList.remove(selectedIndex);
           cityAdapter.notifyDataSetChanged();

           selectedIndex = -1;
           cityList.clearChoices();
        });

    }
}