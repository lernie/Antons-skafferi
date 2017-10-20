package se.miun.antonsskafferi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        email = (EditText) findViewById(R.id.email_login);
        password = (EditText) findViewById(R.id.password);
        passwordCheck = (EditText) findViewById(R.id.password_check);
        button_register = (Button) findViewById(R.id.reg_btn);

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail = email.getText().toString();
                pword = password.getText().toString();
                pcheck = passwordCheck.getText().toString();

                if(mail.equals("") || pword.equals(" ") || pcheck.equals(" ")) {
                    Toast.makeText(RegisterActivity.this, "u fked up", Toast.LENGTH_LONG).show();
                return;
                }

                if(pword != pcheck) {
                    Toast.makeText(RegisterActivity.this, "not same password", Toast.LENGTH_LONG).show();
                return;
                }

                if(mail.length() <= 1  || pword.length() <= 1 || pcheck.length() <= 1){
                    Toast.makeText(RegisterActivity.this, "Email or password is to short", Toast.LENGTH_LONG).show();
               return;
                }

            }
        });
    }
    public void goToLogin(View v) {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void register() {

        mail = email.getText().toString();
        pword = password.getText().toString();
        pcheck = passwordCheck.getText().toString();

    }
}
