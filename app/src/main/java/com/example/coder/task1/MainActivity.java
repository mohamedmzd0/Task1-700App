package com.example.coder.task1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Spinner day ,year, month ,statue ;
    private UserData user ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         day = (Spinner) findViewById(R.id.day);
        ArrayList<String>days =new ArrayList<>();
        days.add("يوم");
        for (int i = 1; i < 31 ; i++) {
            days.add(""+i);
        }
        day.setAdapter(new spinner_adapter(days,getApplicationContext()));
         month = (Spinner) findViewById(R.id.month);
        ArrayList<String>months =new ArrayList<>();
        months.add("شهر");
        months.add("يناير");
        months.add("فبراير");
        months.add("مارس");
        months.add("ابريل");
        months.add("مايو");
        months.add("يونيو");
        months.add("يوليو");
        months.add("اغسطس");
        months.add("اكتوبر");
        months.add("نوفمبر");
        months.add("ديسمبر");
        month.setAdapter(new spinner_adapter(months,getApplicationContext()));
         year = (Spinner) findViewById(R.id.year);
        ArrayList<String>years =new ArrayList<>();
        years.add("سنه") ;
        for (int i = 2000; i < 2018; i++) {
            years.add(""+i);
        }
        year.setAdapter(new spinner_adapter(years,getApplicationContext()));


         statue = (Spinner) findViewById(R.id.statue);
        ArrayList<String>statues =new ArrayList<>();
       statues.add("اعزب");
        statues.add("متزوج");
        statue.setAdapter(new spinner_adapter(statues,getApplicationContext()));

    }

    public void back(View view) {
        finish();
    }
    public void register(View view) {
        if(day.getFirstVisiblePosition()==0){
            Toast.makeText(this, "select day", Toast.LENGTH_SHORT).show();
            return;
        }
        if(month.getFirstVisiblePosition()==0){
            Toast.makeText(this, "select month", Toast.LENGTH_SHORT).show();
            return;
        }
        if(year.getFirstVisiblePosition()==0){
            Toast.makeText(this, "select year", Toast.LENGTH_SHORT).show();
            return;
        }
        if(((TextView)findViewById(R.id.country_text)).getText().toString().isEmpty() ||((TextView)findViewById(R.id.country_text)).getText().toString().length()<6 )
        {
            Toast.makeText(this, "country error", Toast.LENGTH_SHORT).show();
            return;
        }
        if(((TextView)findViewById(R.id.city_text)).getText().toString().isEmpty() ||((TextView)findViewById(R.id.city_text)).getText().toString().length()<6 )
        {
            Toast.makeText(this, "city error", Toast.LENGTH_SHORT).show();
            return;
        }
        if(((TextView)findViewById(R.id.addresssp)).getText().toString().isEmpty() ||((TextView)findViewById(R.id.city_text)).getText().toString().length()<6 )
        {
            Toast.makeText(this, "city error", Toast.LENGTH_SHORT).show();
            return;
        }
        if(connect_firebase())
            Toast.makeText(this, R.string.toast_success, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, R.string.toast_fail, Toast.LENGTH_SHORT).show();
    }

    private boolean connect_firebase() {
        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("users");
            user = new UserData(statue.getSelectedItem().toString(), day.getSelectedItem().toString(), month.getSelectedItem().toString(),
                    year.getSelectedItem().toString(), ((TextView) findViewById(R.id.country_text)).getText().toString(),
                    ((TextView) findViewById(R.id.city_text)).getText().toString(), ((TextView) findViewById(R.id.addresssp)).getText().toString());
            String temp = myRef.push().getKey();
            myRef.child(temp).setValue(user);
            return true ;
        }catch (Exception e)
        {}
        return false ;
    }

    public void checked(View view) {
        boolean check =((CheckBox)view).isChecked() ;
        if(check) {
            findViewById(R.id.register_btn).setEnabled(check);
            findViewById(R.id.register_btn).setBackgroundResource(R.drawable.register_button_background);
        } else if(!check) {
            findViewById(R.id.register_btn).setEnabled(check);
            findViewById(R.id.register_btn).setBackgroundResource(R.drawable.register_button_background2);
        }
    }
}
