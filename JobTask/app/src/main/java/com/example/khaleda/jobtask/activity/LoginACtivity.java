package com.example.khaleda.jobtask.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
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

import java.util.Locale;

public class LoginACtivity extends AppCompatActivity {

    private Button login;
    private Button signup;
    private EditText email;
    private EditText password;
    private RelativeLayout relativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        setLanguage();
        setTitle(getString(R.string.login));
        checkIfLoggedIn();
        initializeUI();
    }


    /// set language for application
    public void setLanguage() {
        SharedPreferences userPreference = getSharedPreferences("user_preference" , MODE_PRIVATE);
        String lang = userPreference.getString("lang" , "en");
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = getBaseContext().getResources().getConfiguration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }

    /// checking if user logged in
    public  void checkIfLoggedIn() {
        SharedPreferences userPreference = getSharedPreferences("user_preference" , MODE_PRIVATE);
        if(userPreference.getBoolean("loggedin" , false)) {
            intentToHome();
        }
    }


    /// intent to home activity
    public void intentToHome() {
        Intent intent = new Intent(getApplicationContext() , HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    /// initializing UI components
    public void initializeUI() {
        login    = (Button)findViewById(R.id.login);
        signup   = (Button)findViewById(R.id.signup);
        email    = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        relativeLayout = (RelativeLayout)findViewById(R.id.root_view);
        setLoginButton();
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


    /// event handling login button
    public void setLoginButton() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidData()) {
                    UserModel user = new UserModel(getApplicationContext() ,
                            email.getText().toString() , password.getText().toString());
                    int id = user.search();
                    if(id != -1) {
                        SharedPreferences userPreference = getSharedPreferences("user_preference" , MODE_PRIVATE);
                        userPreference.edit().putBoolean("loggedin" , true).apply();
                        userPreference.edit().putString("email" , email.getText().toString()).apply();
                        userPreference.edit().putString("password" , password.getText().toString()).apply();
                        intentToHome();
                    } else {
                        email.setError(getString(R.string.wrong_field));
                        password.setError(getString(R.string.wrong_field));

                    }
                }
            }
        });
    }

    /// event handling siguup button
    public void setSignupButton() {
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , SignupActivity.class);
                startActivity(intent);
            }
        });
    }


    /// validate data
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
        return valid;
    }
}
