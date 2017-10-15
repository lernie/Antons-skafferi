package se.miun.antonsskafferi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.password;

/**
 * Created by My on 10/12/2017.
 */

public class LoginActivity extends AppCompatActivity {

    EditText mail, pword;
    Button log_button;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    public void doLogin(View v){
        log_button = (Button) findViewById(R.id.login_button);
        mail = (EditText) findViewById(R.id.email_login);
        pword = (EditText) findViewById(R.id.password_login);

        LoginCache.getInstance().setUser(new User(mail.getText().toString(), pword.getText().toString()));

        LoginCache.getInstance().update(new LoginCache.UpdateCallback() {
            @Override
            public void onSuccess() {
                Intent intent = new Intent(LoginActivity.this, TablesActivity.class);
                startActivity(intent);

                finish();
            }

            @Override
            public void onFail() {
                Toast.makeText(LoginActivity.this, "Fel lösenord eller mail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}



/*
    public void goToTables(View v) {

        Intent intent = new Intent(this, TablesActivity.class);
        startActivity(intent);


    }*/




