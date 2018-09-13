package com.example.somnathyadav.quizlistingapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button take_quiz,signup,create_quiz;
    private Context context;
    private AlertDialog dialog;
    List<Questions_Obj> questionList;
    int score;
    int quid;
    Questions_Obj currentQuestion;

    private EditText equestion,eanswer,eoptionA,eoptionB,eoptionC,eoptionD,eoptionE;
    private Button submit_question;
    private Database_Ques dbHelper;
    TextView question;
    RadioButton radioButtonA, radioButtonB, radioButtonC, radioButtonD, radioButtonE;
    Button butNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new Database_Ques(getApplicationContext());


        context = this;
        take_quiz =  findViewById(R.id.takequuiz);
        create_quiz=findViewById(R.id.createquiz);
        signup=findViewById(R.id.signup);



        take_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = 0;
                quid = 0;
                questionList = dbHelper.getAllQuestions_Objs();
                Collections.shuffle(questionList);
                currentQuestion = questionList.get(quid);

                CreateAlertDialog();

            }
        });

        create_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createQuiz();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,Signup_Activity.class));
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createQuiz();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String name=dbHelper.returnName();
        if(!name.isEmpty())
        {
            TextView textView=findViewById(R.id.nameof);
            textView.setVisibility(View.VISIBLE);
            textView.setText("Hello "+name);
            signup.setVisibility(View.GONE);


        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void CreateAlertDialog(){
        LayoutInflater inflater = getLayoutInflater();
        final View alertLayout = inflater.inflate(R.layout.quizbox, null);
        question = alertLayout.findViewById(R.id.question);
        radioButtonA = alertLayout.findViewById(R.id.radioA);
        radioButtonB = alertLayout.findViewById(R.id.radioB);
        radioButtonC = alertLayout.findViewById(R.id.radioC);
        radioButtonD = alertLayout.findViewById(R.id.radioD);
        radioButtonE = alertLayout.findViewById(R.id.radioE);

        butNext = alertLayout.findViewById(R.id.button1);

        setQuestionView();
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion(alertLayout);
            }
        });
        //Alertbuilder
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        //create dialog
        dialog = alert.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();


    }

    private void setQuestionView() {
        question.setText(quid + 1 + ". " + currentQuestion.getQue());
        radioButtonA.setText(currentQuestion.getOptA());
        radioButtonB.setText(currentQuestion.getOptB());
        radioButtonC.setText(currentQuestion.getOptC());
        radioButtonD.setText(currentQuestion.getOptD());
        radioButtonE.setText(currentQuestion.getOptE());

        quid++;
    }

    public void nextQuestion(View view) {
        RadioGroup grp = view.findViewById(R.id.radioGroup1);
        RadioButton answer = view.findViewById(grp.getCheckedRadioButtonId());
        if (currentQuestion.getAnswer().equals(answer.getText())) {
            score++;
        }

        if (quid <dbHelper.getRowCount()) {
            currentQuestion = questionList.get(quid);
            setQuestionView();
        } else {
            showresult(view);
        }


    }

    private void showresult(View view) {
        TextView scoreof = view.findViewById(R.id.score);
        scoreof.setText(String.valueOf(score));

        question.setVisibility(View.GONE);
        radioButtonA.setVisibility(View.GONE);
        radioButtonB.setVisibility(View.GONE);
        radioButtonC.setVisibility(View.GONE);
        radioButtonD.setVisibility(View.GONE);
        radioButtonE.setVisibility(View.GONE);
        Button click = view.findViewById(R.id.button1);
        click.setVisibility(View.GONE);
        TextView textView = view.findViewById(R.id.textView2);
        Button closebutton = view.findViewById(R.id.close);
        textView.setVisibility(View.VISIBLE);
        closebutton.setVisibility(View.VISIBLE);
        closebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


    private void createQuiz() {

        LayoutInflater inflater = getLayoutInflater();
        final View alertLayout = inflater.inflate(R.layout.addquizques, null);
        equestion = alertLayout.findViewById(R.id.addquestion);
        eanswer = alertLayout.findViewById(R.id.addanswer);
        eoptionA = alertLayout.findViewById(R.id.optionA);
        eoptionB = alertLayout.findViewById(R.id.optionB);
        eoptionC = alertLayout.findViewById(R.id.optionC);
        eoptionD= alertLayout.findViewById(R.id.optionD);
        eoptionE=alertLayout.findViewById(R.id.optionE);
        submit_question=alertLayout.findViewById(R.id.addtodb);
        Button cancel=alertLayout.findViewById(R.id.closeadd);

        //alert builder
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Add New Question for Quiz");
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);

        final AlertDialog dialog = alert.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();

        //submit question
        submit_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question=equestion.getText().toString();
                String answer=eanswer.getText().toString();
                String optiona=eoptionA.getText().toString();
                String optionb=eoptionB.getText().toString();
                String optionc=eoptionC.getText().toString();
                String optiond=eoptionD.getText().toString();
                String optione=eoptionE.getText().toString();

                if(question.equals("")||answer.equals("")||optiona.equals("")||optionb.equals("")||optionc.equals("")||optiond.equals("")||optione.equals("")) {
                    Toast.makeText(context, "Please fill out all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    dbHelper.addQuestions_ObjToDB(new Questions_Obj(question,optiona,optionb,optionc,optiond,optione,answer));
                    dialog.dismiss();
                }
            }
        });
        //cancel button
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}
