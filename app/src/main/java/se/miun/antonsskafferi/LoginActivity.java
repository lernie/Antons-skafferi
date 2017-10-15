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

/**
 * Created by My on 10/12/2017.
 */

public class LoginActivity extends AppCompatActivity {


    EditText mail, password;
    Button log_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginBtn();

    }

    public void LoginBtn(){
        log_button = (Button) findViewById(R.id.login_button);
        mail = (EditText) findViewById(R.id.email_login);
        password = (EditText) findViewById(R.id.password_login);

      log_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (mail.getText().toString().equals("a") &&
                        password.getText().toString().equals("a")) {
                    Intent intent = new Intent(getApplicationContext(), TablesActivity.class);
                    startActivity(intent);

                } else {
                   Toast.makeText(LoginActivity.this, "emailen eller lösenordet är fel",
                           Toast.LENGTH_SHORT).show();
               }
                }
        });
    }


/*
    public void goToTables(View v) {

        Intent intent = new Intent(this, TablesActivity.class);
        startActivity(intent);


    }*/

}


