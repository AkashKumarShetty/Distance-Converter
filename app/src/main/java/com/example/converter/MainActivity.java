package com.example.converter;

import android.os.Bundle;
import java.text.DecimalFormat;
import android.support.design.widget.FloatingActionButton;
import android.text.method.ScrollingMovementMethod;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


//Distance Converter
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private boolean check;
    private TextView tv, tv1, output;
    private EditText inputValue;
    private RadioButton r3, r4;
    private TextView scrollingTextView;
    StringBuilder sb = new StringBuilder();
    static String s = "", sa = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//code
        tv = (TextView) findViewById(R.id.textView2);
        tv1 = (TextView) findViewById(R.id.textView4);
        inputValue = findViewById(R.id.editText);
        output = findViewById(R.id.textView5);

        r3 = (RadioButton) findViewById(R.id.radioButton3);
        r4 = (RadioButton) findViewById(R.id.radioButton4);
        scrollingTextView = (TextView) findViewById(R.id.textView7);
        scrollingTextView.setMovementMethod(new ScrollingMovementMethod());

        if (savedInstanceState != null)
        {
            sa = savedInstanceState.getString( "HISTORY");
        }
    }

    public void onRadioButtonClicked(View view) {
        check = ((RadioButton) view).isChecked();
        switch(view.getId())
        {
            case R.id.radioButton3:
                if (check)
                    Log.d(TAG, "onRadioButtonClicked: This is in onRadioButtonClicked3!");
                    tv.setText("Miles Value:");
                    tv1.setText("Kilometers Value:");
                break;
            case R.id.radioButton4:
                if(check)
                    Log.d(TAG, "onRadioButtonClicked: This is in onRadioButtonClicked4!");
                    tv.setText("Kilometers Value:");
                    tv1.setText("Miles Value:");
                break;

        }
    }

    public void buttonClicked(View v)
    {
        DecimalFormat df = new DecimalFormat("#.##");
        String text = inputValue.getText().toString().trim();
        inputValue.setText("");
        double val = 0.0;
        double answerVal = 0.0;
        String text1;
        Log.d(TAG, "buttonClicked: This is in buttonClicked!");
        if(r3.isChecked())
        {
            if(text.trim().isEmpty())
            {
                Toast.makeText(getApplicationContext(), "Please Enter distance in Miles", Toast.LENGTH_SHORT).show();
            }
            else {
                val = Double.parseDouble(text);
                answerVal = val * 1.60934;
                answerVal = (double) Math.round(answerVal * 10) / (double) 10;
                Log.d(TAG, "buttonClicked: This is in buttonClicked!");
                text1 = Double.toString(answerVal);
                if (!text1.trim().isEmpty())
                    output.setText(text1);
                if (r3.isChecked()) {
                    s = text + " Mi ==> " + text1 + " Km\n" + s;
                    scrollingTextView.setText(s);
                }
            }
        }
        if(r4.isChecked())
        {
            if(text.trim().isEmpty())
            {
                Toast.makeText(getApplicationContext(), "Please Enter distance in kilometers", Toast.LENGTH_SHORT).show();
            }
            else{
                val = Double.parseDouble(text);
                answerVal = val * 0.621371;
                answerVal = (double)Math.round(answerVal * 10)/(double)10;
                text1 = Double.toString(answerVal);
                if(!text1.trim().isEmpty())
                    output.setText(text1);
                if(r4.isChecked())
                {
                    s = text + " Km ==> " + text1 + " Mi\n" + s;
                    scrollingTextView.setText(s);
                }
            }
        }

    }

    public void clearButton(View v1)
    {
        s = "";
        scrollingTextView.setText("");
    }

    protected void onSaveInstanceState(Bundle os) {

        os.putString("inputValue", inputValue.getText().toString());
        os.putString("output", output.getText().toString());
        os.putString("history", scrollingTextView.getText().toString());
        os.putString("r3",r3.getText().toString()) ;
        os.putString("r4",r4.getText().toString()) ;
        os.putString("tv",tv.getText().toString()) ;
        os.putString("tv1",tv1.getText().toString());
        super.onSaveInstanceState(os);
    }

    @Override
    protected void onRestoreInstanceState(Bundle si) {

        super.onRestoreInstanceState(si);

        inputValue.setText(si.getString("inputValue"));
        output.setText(si.getString("output"));
        scrollingTextView.setText(si.getString("history"));
        r3.setText(si.getString("r3"));
        r4.setText(si.getString("r4"));
        tv.setText(si.getString("tv"));
        tv1.setText(si.getString("tv1"));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}