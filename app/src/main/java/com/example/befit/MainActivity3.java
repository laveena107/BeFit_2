package com.example.befit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity3 extends AppCompatActivity {
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000 ;
    private ImageButton date;
    TextView tv,t1;
    Button b;
    DatePickerDialog datepicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        date = findViewById(R.id.date);
        tv = findViewById(R.id.tv);
        t1 = findViewById(R.id.t1);
        b = findViewById(R.id.button);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak();
            }
        });
    }
    private void speak() {
        // intent to show speech to text dialog

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hi speak something");

        //start intent
        try {
            //there was no error
            // show dialog
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);

        }
        catch(Exception e) {
            //if there was error
            //get message of error and show
            Toast.makeText(this,""+e.getMessage(), Toast.LENGTH_SHORT).show();


        }

    }

    //recieve voice input and handle it


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case REQUEST_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    //get text array from voice input

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    // set to text view
                    tv.setText(result.get(0));

                    Calendar calendar = Calendar.getInstance();
                    final int day = calendar.get(Calendar.DAY_OF_MONTH);
                    final int month = calendar.get(Calendar.MONTH);
                    final int year = calendar.get(Calendar.YEAR);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    try {
                        Date d = sdf.parse("20130526160000");
                    } catch (ParseException ex) {
                        Log.v("Exception", ex.getLocalizedMessage());
                    }

                    /*
                    datepicker = new DatePickerDialog(MainActivity3.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            t1.setText(day + "/" + (month + 1) + "/" + year);
                        }
                    }, year, month, day);
                    datepicker.show();
                }
            }}
    }
} */ }}}}}