package com.example.somnathyadav.quizlistingapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.SingleLineTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Somnath Yadav on 12-09-2018.
 */

public class Signup_Activity extends AppCompatActivity{

    private EditText eusername,eemail,epassw;
    private Database_Ques database_ques;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        database_ques = new Database_Ques(getApplicationContext());


        eusername=(EditText) findViewById(R.id.etName);
        eemail=(EditText) findViewById(R.id.etEmaill);
        epassw=(EditText) findViewById(R.id.etPass);
        Button button=findViewById(R.id.btnSignup);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=eusername.getText().toString();
                String email=eemail.getText().toString();
                String pass=epassw.getText().toString();

                if(username.equals("")||email.equals("")||pass.equals("")) {
                    Toast.makeText(Signup_Activity.this, "Fill all the details", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Signup_Activity.this, username+"  "+email+"  "+pass, Toast.LENGTH_SHORT).show();
                    database_ques.addUser(username, email, pass);
                    finish();
                }
            }
        });




    }
}
