package se.miun.antonsskafferi;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;

/**
 * Created by My on 10/19/2017.
 */

public class RegisterActivity extends AppCompatActivity {

    Button button_register;
    EditText email;
    EditText password;
    EditText passwordCheck;
    String mail, pword, pcheck;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void goToLogin(View v) {

      //  if (CheckFieldValidation()) {
            email = (EditText) findViewById(R.id.email_login);
            password = (EditText) findViewById(R.id.password);
            passwordCheck = (EditText) findViewById(R.id.password_check);
            button_register = (Button) findViewById(R.id.reg_btn);

            LoginCache.getInstance().setUser(new User(email.getText().toString(), password.getText().toString()));

            LoginCache.getInstance().update(new LoginCache.UpdateCallback() {
                @Override
                public void onSuccess() {

                    finish();
                    startActivity(getIntent());
                    email.setText("");
                    password.setText("");


                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);

                    finish();
                }

                @Override
                public void onFail() {
                    Toast.makeText(RegisterActivity.this, "Fel lösenord eller mail", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
/*
        private boolean CheckFieldValidation() {
            boolean valid = true;
            if (email.getText().toString().equals("")) {
                email.setError("Can't be Empty");
                valid = false;
            } else if (password.getText().toString().equals("")) {
                password.setError("Can't be Empty");
                valid = false;
            }
                return valid;
        }*/



