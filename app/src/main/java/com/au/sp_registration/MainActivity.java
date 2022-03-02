package com.au.sp_registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] skills = {"Select A Item","Web Designing","PHP","MySQL","Video Editing","Canva"};
    Spinner spinner;
    EditText ed;
    CheckBox vomit,master,tpass,far;
    RadioButton purush,stri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=findViewById(R.id.spinner);
        ed=findViewById(R.id.name);
        ArrayAdapter<String> a = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,skills){
            @Override
            public boolean isEnabled(int position) {
                if(position==0)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
        };
        spinner.setAdapter(a);
        vomit=findViewById(R.id.vomit);
        master=findViewById(R.id.master);
        tpass=findViewById(R.id.tpass);
        far=findViewById(R.id.far);
        purush=findViewById(R.id.purush);
        stri=findViewById(R.id.stri);
    }

    public void insert_data(View view) {
        RadioGroup rg=findViewById(R.id.rbg);
        int a=rg.getCheckedRadioButtonId();
        RadioButton rb=findViewById(a);
        String skill="";
        if(vomit.isChecked())
        {
            skill+=vomit.getText().toString()+",";
        }
        if(master.isChecked())
        {
            skill+=master.getText().toString()+",";
        }
        if(tpass.isChecked())
        {
            skill+=tpass.getText().toString()+",";
        }
        if(far.isChecked())
        {
            skill+=far.getText().toString()+",";
        }
        SharedPreferences sp=getSharedPreferences("demo",MODE_PRIVATE);
        SharedPreferences.Editor demo=sp.edit();
        demo.putString("name",ed.getText().toString());
        demo.putString("gender",rb.getText().toString());
        demo.putString("hobi",skill);
        demo.putString("skill",spinner.getSelectedItem().toString());
        if(demo.commit())
        {
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Sori", Toast.LENGTH_LONG).show();
        }
        rb.setChecked(false);
        ed.setText("");
        vomit.setChecked(false);
        master.setChecked(false);
        tpass.setChecked(false);
        far.setChecked(false);
        spinner.setSelection(0);
    }

    public void select_data(View view) {
        SharedPreferences sel=getSharedPreferences("demo",MODE_PRIVATE);
        ed.setText(sel.getString("name","!"));
        String radio=sel.getString("gender","!");
        if(radio.equals(purush.getText().toString()))
        {
            purush.setChecked(true);
        }
        else
        {
            stri.setChecked(true);
        }
        String check=sel.getString("hobi","!");
        String[] split=check.split(",");
        for (String s:split)
        {
            if(s.equals(master.getText().toString()))
            {
                master.setChecked(true);
            }
            if(s.equals(tpass.getText().toString()))
            {
                tpass.setChecked(true);
            }
            if(s.equals(far.getText().toString()))
            {
                far.setChecked(true);
            }
            if(s.equals(vomit.getText().toString()))
            {
                vomit.setChecked(true);
            }
        }
        String ski=sel.getString("skill","!");
        for (int i=0;i<skills.length;i++)
        {
            if(skills[i].equals(ski))
            {
                ((Spinner)findViewById(R.id.spinner)).setSelection(i);
            }
        }
    }
}