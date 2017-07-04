package com.example.khaleda.jobtask.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.khaleda.jobtask.R;
import com.example.khaleda.jobtask.model.UserModel;

public class SignupActivity extends AppCompatActivity {

    private Button signup;
    private EditText email;
    private EditText password;
    private EditText repeatedPassword;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setTitle(getString(R.string.signup));
        initializeUI();
    }

    /// initialize UI components
    public void initializeUI() {
        signup           = (Button)findViewById(R.id.signup);
        email            = (EditText)findViewById(R.id.email);
        password         = (EditText)findViewById(R.id.password);
        repeatedPassword = (EditText)findViewById(R.id.repeated_password);
        relativeLayout   = (RelativeLayout)findViewById(R.id.root_view);
        setSignupButton();
        setLayoutEvent();
    }

    /// hide keyboard when touching outside edittexts
    public void setLayoutEvent() {
        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        });
    }


    /// event handling signup button
    public void setSignupButton() {
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidData()) {
                    UserModel user = new UserModel(getApplicationContext() ,
                            email.getText().toString() , password.getText().toString());
                    int id = user.save();
                    if(id != -1) {
                        SharedPreferences userPreference = getSharedPreferences("user_preference" , MODE_PRIVATE);
                        userPreference.edit().putBoolean("loggedin" , true).apply();
                        userPreference.edit().putInt("id" , id).apply();
                        userPreference.edit().putString("email" , email.getText().toString()).apply();
                        userPreference.edit().putString("password" , password.getText().toString()).apply();
                        Intent intent = new Intent(getApplicationContext() , HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        email.setError(getString(R.string.email_already_exsit));
                    }
                }
            }
        });
    }


    /// validate data before sign up
    public boolean isValidData() {
        boolean valid = true;
        // todo check for email format
        if(email.getText().toString().equals("")) {
            email.setError(getString(R.string.required));
            valid = false;
        }
        if(password.getText().toString().equals("")) {
            password.setError(getString(R.string.required));
            valid = false;
        }

        if(repeatedPassword.getText().toString().equals("")) {
            repeatedPassword.setError(getString(R.string.required));
            valid = false;
        } else if(!repeatedPassword.getText().toString().equals(password.getText().toString())) {
            repeatedPassword.setError(getString(R.string.no_matching));
            valid = false;
        }
        return valid;
    }
}
