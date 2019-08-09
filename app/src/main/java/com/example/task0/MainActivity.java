package com.example.task0;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText email_valid, password_valid, mobile_valid,seek_no;
    Button submit_button;
    String pattern = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3}) [-. )]*(\\d{3})[-.]*(\\d{4})(?: *x(\\d+))?\\s*$";
    Matcher m;
    SeekBar seekBar;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +
                    "(?=.*[a-z])" +
                    "(?=.*[A-Z])" +
                    "(?=.*[a-zA-Z])" +
                    "(?=.*[@#$%^&+=])" +
                    "(?=\\S+$)" +
                    ".{6,20}" +
                    "$");

    int currentMax =100 , currentStep = 20;
    int currrentProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email_valid = findViewById(R.id.editText7);
        password_valid = findViewById(R.id.editText9);
        mobile_valid = findViewById(R.id.editText6);
        seekBar = findViewById(R.id.seekbar);
        seek_no = findViewById(R.id.seekbar_point);

        seekBar.setMax(currentMax / currentStep);
        seekBar.setProgress(0);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currrentProgress = progress *currentStep;
                seek_no.setText("" + currrentProgress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        submit_button = findViewById(R.id.submit_button);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validatePassword();

                String validEmail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+";

                String button = email_valid.getText().toString();
                Matcher matcher = Pattern.compile(validEmail).matcher(button);
                if (matcher.matches()) {
                    Toast.makeText(getApplicationContext(), "All Good", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Enter valid email id", Toast.LENGTH_LONG).show();
                }
                if (password_valid.getText().toString().equals("")) {
                    password_valid.setError("Enter Password");
                }

                Pattern r =Pattern.compile(pattern);
                if(!mobile_valid.getText().toString().isEmpty())
                {
                    m = r.matcher(mobile_valid.getText().toString().trim());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"enter number",Toast.LENGTH_LONG).show();
                }
                if (m.find())
                {
                    Toast.makeText(MainActivity.this, "MATCH", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "NOT MATCH", Toast.LENGTH_SHORT).show();
                }

            }

        });

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.occupation, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    private boolean validatePassword() {
        String password = password_valid.getText().toString().trim();
        if (password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Field can't be empty", Toast.LENGTH_LONG).show();
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            Toast.makeText(getApplicationContext(), "Password too weak", Toast.LENGTH_LONG).show();
            return false;
        } else {
            Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_LONG).show();
            return true;
        }
    }
}
