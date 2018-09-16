package com.delaroystudios.sqlitelogin.Systemusers;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.delaroystudios.sqlitelogin.R;

public class QueueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);

    }

    public void onStart(){
        super.onStart();
        EditText txtdate=(EditText)findViewById(R.id.txtdate);
        txtdate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    DateDialog dialog=new DateDialog(v);
                    FragmentTransaction ft=getFragmentManager().beginTransaction();
                    dialog.show(ft,"DatePicker");
                }
            }
        });
        final ImageView next2 = (ImageView) findViewById(R.id.backf);
        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView btn_next2 = (ImageView) findViewById(R.id.backf);
                Intent intent = new Intent(QueueActivity.this, FirstActivity.class);
                startActivities(new Intent[]{intent});


            }
        });



        // btnSave (Save)
        final Button save = (Button) findViewById(R.id.conq);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // If Save Complete
                if (SaveData()) {
                    // Open Form Main
                    QueueActivity.this.finish();
                }
            }
        });




    }

    public boolean SaveData() {
        //addName,addSyndrome,addRemedy

        final EditText tVegdisName = (EditText) findViewById(R.id.name);
        final EditText tVegdisArea = (EditText) findViewById(R.id.phone);
        final EditText tVegdisType = (EditText) findViewById(R.id.txtdate);
        final Spinner category = (Spinner) findViewById(R.id.spinnercategory);
        final Spinner time = (Spinner) findViewById(R.id.spinnertime);


        // Dialog
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        AlertDialog ad = adb.create();


        // new Class DB
        final MyDBClass myDb = new MyDBClass(this);


        // Save Data
        long saveStatus = myDb.InsertData(
                tVegdisName.getText().toString(),
                tVegdisArea.getText().toString(),
                tVegdisType.getText().toString(),
                category.getSelectedItem().toString(),
                time.getSelectedItem().toString());
        if (saveStatus <= 0) {
            ad.setMessage("การจองสำเร็จ");
            ad.show();
            return false;
        }
        myDb.close();
        Toast.makeText(QueueActivity.this, "จองสำเร็จแล้ว",
                Toast.LENGTH_SHORT).show();


        return true;

    }

}



