package com.example.dell.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView countdown, score, equation, announceScore, textView;
    int finalScore, a, b, c, d;
    int numQuestions = 1;
    CountDownTimer counter;
    Button goButton;
    String tag;
    Random rand;
    Button[] buttons;

    public void answerCheck() {

        if(tag.equals(buttons[d].getTag().toString()))
            finalScore++;

        score.setText(Integer.toString(finalScore) + "/" + Integer.toString(numQuestions));

    }

    public void buttonClicked(View view) {

        tag = view.getTag().toString();
        answerCheck();

        a = rand.nextInt(21);
        b = rand.nextInt(21);
        d = rand.nextInt(4);

        equation.setText(Integer.toString(a) + " + " + Integer.toString(b));

        c = a + b;

        for(int j = 0; j < 4; j++) {

            buttons[j].setText(Integer.toString(rand.nextInt(41)));
            while (Integer.parseInt(buttons[j].getText().toString()) == c)
                buttons[j].setText(Integer.toString(rand.nextInt(41)));
        }
        buttons[d].setText(Integer.toString(c));
        numQuestions++;

    }

    public void startMethod() {

        finalScore = 0;
        equation.setText(Integer.toString(a) + " + " + Integer.toString(b));
        counter();

    }

    public void goMethod(View view) {

        numQuestions = 1;

        goButton.setVisibility(View.INVISIBLE);

        countdown.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        equation.setVisibility(View.VISIBLE);

        buttons[0].setVisibility(View.VISIBLE);
        buttons[1].setVisibility(View.VISIBLE);
        buttons[2].setVisibility(View.VISIBLE);
        buttons[3].setVisibility(View.VISIBLE);

        announceScore.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);

        equation.setText(Integer.toString(a) + "+" + Integer.toString(b));

        c = a + b;

        for(int j = 0; j < 4; j++) {
            buttons[j].setText(Integer.toString(rand.nextInt(41)));
            while (Integer.parseInt(buttons[j].getText().toString()) == c)
                buttons[j].setText(Integer.toString(rand.nextInt(41)));
        }

        buttons[d].setText(Integer.toString(c));

        score.setText("0/1");

        startMethod();

    }

    public void counter() {

        counter = new CountDownTimer(31000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                int timeLeft = (int) millisUntilFinished/1000;
                countdown.setText(Integer.toString(timeLeft) + "s");

            }

            @Override
            public void onFinish() {

                countdown.setText("0s");
                announceScore.setText(Integer.toString(finalScore) + "/" + Integer.toString(numQuestions));
                announceScore.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                countdown.setVisibility(View.INVISIBLE);
                score.setVisibility(View.INVISIBLE);
                equation.setVisibility(View.INVISIBLE);
                goButton.setVisibility(View.VISIBLE);
                goButton.setText("Restart");

            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        countdown = (TextView) findViewById(R.id.countdown);
        score = (TextView) findViewById(R.id.score);
        equation = (TextView) findViewById(R.id.equation);
        announceScore = (TextView) findViewById(R.id.finalScore);
        textView = (TextView) findViewById(R.id.textView);

        buttons = new Button[4];
        buttons[0] = (Button) findViewById(R.id.button1);
        buttons[1] = (Button) findViewById(R.id.button2);
        buttons[2] = (Button) findViewById(R.id.button3);
        buttons[3] = (Button) findViewById(R.id.button4);

        goButton = (Button) findViewById(R.id.goButton);

        countdown.setVisibility(View.INVISIBLE);
        score.setVisibility(View.INVISIBLE);
        equation.setVisibility(View.INVISIBLE);
        announceScore.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);

        buttons[0].setVisibility(View.INVISIBLE);
        buttons[1].setVisibility(View.INVISIBLE);
        buttons[2].setVisibility(View.INVISIBLE);
        buttons[3].setVisibility(View.INVISIBLE);

        rand = new Random();

        a = rand.nextInt(21);
        b = rand.nextInt(21);
        d = rand.nextInt(4);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
