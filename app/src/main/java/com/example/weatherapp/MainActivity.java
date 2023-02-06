package com.example.weatherapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {private EditText searchEditText;

    private Button searchButton;
    private FrameLayout fragmentContainer;
    private Spinner spinner;
    private boolean searchFormVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        searchEditText = findViewById(R.id.search_edit_text);
        searchButton = findViewById(R.id.search_button);
        fragmentContainer = findViewById(R.id.fragment_container);
        spinner = findViewById(R.id.spinner);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.weather_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = searchEditText.getText().toString();
                if (!city.isEmpty()) {
                    Fragment fragment = null;
                    String selectedOption = spinner.getSelectedItem().toString();
                    if (selectedOption.equals("Today Weather")) {
                        fragment = new TodayWeatherFragment();
                    } else if (selectedOption.equals("Hourly Forecast")) {
                        fragment = new HourlyForecastFragment();
                    } else if (selectedOption.equals("Weekly Forecast")) {
                        fragment = new WeeklyForecastFragment();
                    }
                    if (fragment != null) {

                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.commit();


                        searchEditText.setVisibility(View.GONE);
                        spinner.setVisibility(View.GONE);
                        searchButton.setVisibility(View.GONE);
                    }
                }
            }
        });

    }
}