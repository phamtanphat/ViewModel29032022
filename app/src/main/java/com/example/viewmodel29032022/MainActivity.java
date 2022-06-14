package com.example.viewmodel29032022;

import android.Manifest;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    TextView tvOutput;
    EditText edtInput;
    Button btnClick;
    MyViewModel myViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvOutput = findViewById(R.id.text_view_output);
        edtInput = findViewById(R.id.edit_text_input);
        btnClick = findViewById(R.id.button_click);

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        myViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvOutput.setText(s);
            }
        });
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edtInput.getText().toString();
                myViewModel.updateText(text);
            }
        });
    }
}
