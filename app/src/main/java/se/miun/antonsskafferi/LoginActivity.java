package se.miun.antonsskafferi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        // LoginBtn();


    /*public void LoginBtn(){
        log_button = (Button) findViewById(R.id.login_button);
        mail = (EditText) findViewById(R.id.email_login);
        password = (EditText) findViewById(R.id.password_login);

      log_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (mail.getText().toString().equals("a") &&
                        password.getText().toString().equals("a")) {
                        Toast.makeText(LoginActivity.this, "Username and password is correct",
                                Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent("se.miun.antonsskafferi.TablesActivity");
                    startActivity(intent);

                }
                }
        });*/
    }

    public void goToTables(View v) {

        Intent intent = new Intent(this, TablesActivity.class);
        startActivity(intent);


    }
}


