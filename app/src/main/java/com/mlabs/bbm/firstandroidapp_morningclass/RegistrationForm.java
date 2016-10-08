package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationForm extends AppCompatActivity {
    //private MainActivity RegisterForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        final Database sqlDB = new Database(getApplicationContext());

        final EditText email = (EditText) findViewById(R.id.temail);
        final EditText password = (EditText) findViewById(R.id.tpass);
        final EditText confirmPassword = (EditText) findViewById(R.id.tcpass);
        final EditText username = (EditText) findViewById(R.id.Usertext);
        final EditText firstname = (EditText) findViewById(R.id.fnametext);
        final EditText lastname = (EditText) findViewById(R.id.editlname);
        Button btnRegister = (Button) findViewById(R.id.button4);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAdd = email.getText().toString();
                String pword = password.getText().toString();
                String confPassword = confirmPassword.getText().toString();
                String uname = username.getText().toString();
                String fname = firstname.getText().toString();
                String lname = lastname.getText().toString();

                if (!emailAdd.equalsIgnoreCase("") && !pword.equalsIgnoreCase("") && !confPassword.equalsIgnoreCase("") && !uname.equalsIgnoreCase("")  && !fname.equalsIgnoreCase("")  && !lname.equalsIgnoreCase("") ) {
                    if (validateName(fname) == true && validateName(lname) == true) {
                        if (validateEmail(emailAdd) == true && validatePassword(pword) == true)
                            if (sqlDB.validateEmail(emailAdd) == true) {
                                if (sqlDB.validateUserName(uname) == true) {
                                    if (pword.equals(confPassword)) {
                                        sqlDB.registerUser(emailAdd, pword, uname, fname, lname);
                                        Toast.makeText(getApplicationContext(), "Account created successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RegistrationForm.this, MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Password did not match", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "Username already exists.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Email Address already exists", Toast.LENGTH_SHORT).show();
                            }
                        else if (validateEmail(emailAdd) == false && validatePassword(pword) == true) {
                            Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                        } else if (validateEmail(emailAdd) == true && validatePassword(pword) == false) {
                            Toast.makeText(getApplicationContext(), "Password must be more than 8 characters", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Names must not contain numbers", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill up required fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public boolean validateEmail(String emailAdd) {
        String regexEmail = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";

        Pattern p = Pattern.compile(regexEmail);
        Matcher m = p.matcher(emailAdd);

        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validatePassword(String pword) {
        if (pword.length() >= 8) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateName(String name) {
        String regexEmail = "^[a-zA-Z]+[\\-'\\s]?[a-zA-Z ]+$";
        Pattern p = Pattern.compile(regexEmail);
        Matcher m = p.matcher(name);

        if (m.matches()) {
            return true;
        } else {
            return false;
        }

    }
}
