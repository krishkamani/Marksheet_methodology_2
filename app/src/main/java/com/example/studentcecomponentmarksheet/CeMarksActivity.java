package com.example.studentcecomponentmarksheet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class CeMarksActivity extends AppCompatActivity {

    private EditText rollno,classtest,sessional,assignment;
    private Button submit,previous,show;

    private List<String> rollnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ce_marks);

        rollnumber=new ArrayList<String>();

        rollno=findViewById(R.id.ce_rollno);
        classtest=findViewById(R.id.ce_classtest);
        sessional=findViewById(R.id.ce_sessional);
        assignment=findViewById(R.id.ce_assignment);
        submit=findViewById(R.id.submit_ce_marks);
        previous=findViewById(R.id.previous_button);
        show=findViewById(R.id.show_marks);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showintent=new Intent(CeMarksActivity.this,showActivity.class);
                startActivity(showintent);
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent previousintent=new Intent(CeMarksActivity.this,MainActivity.class);
                startActivity(previousintent);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    finishAffinity();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roll=rollno.getText().toString();
                String classtestmarks=classtest.getText().toString();
                String sessionalmarks=sessional.getText().toString();
                String assignmentmarks=assignment.getText().toString();

                if(TextUtils.isEmpty(roll) || TextUtils.isEmpty(classtestmarks) || TextUtils.isEmpty(sessionalmarks)
                || TextUtils.isEmpty(assignmentmarks))
                {
                    Toast.makeText(CeMarksActivity.this,"Please enter all fields..",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int k=0;
                    try {
                        int checkroll=Integer.parseInt(roll.substring(roll.length()-3));
                        String checkmiddle=roll.substring(roll.length()-6,roll.length()-3);

                    }catch (Exception e)
                    {
                        k=1;
                        Toast.makeText(CeMarksActivity.this,"Please enter valid roll number",Toast.LENGTH_SHORT).show();
                    }
                    if(k==0)
                    {
                        int i=0;
                        String myrollno="";
                        String FILENAME = "/StudentDetails/StudentDetails.txt";
                        String namFile = Environment.getExternalStorageDirectory() + FILENAME;
                        try {
                            FileInputStream fis = new FileInputStream(namFile);
                            DataInputStream in = new DataInputStream(fis);
                            BufferedReader br =
                                    new BufferedReader(new InputStreamReader(in));
                            String strLine;
                            while ((strLine = br.readLine()) != null) {
                                if(myrollno=="") {
                                    myrollno = myrollno + strLine;
                                }else {
                                    myrollno = myrollno + "\n" + strLine;
                                }
                                i=i+1;
                            }
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        String[] records = myrollno.split("\n");
                        int check=0;
                        for(int j=0;j<i;j++)
                        {
                            String[] tuple = records[j].split("\\|");
                            String val=tuple[0];
                            if(val.equals(roll))
                            {
                                check=1;
                                break;
                            }

                        }

                        if(check==0)
                        {
                            Toast.makeText(CeMarksActivity.this,"Please first enter data in student details...",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            try {
                                String FILENAME1 = "/StudentDetails/";
                                String entry=roll+"|"+"ClassTest|"+classtestmarks+"|"+"SessionalExam|"+sessionalmarks+"|"+
                                        "Assignment|"+assignmentmarks+"\n";
                                String namFile1 = Environment.getExternalStorageDirectory() + FILENAME1;
                                File datfile1 = new File(namFile1);
                                datfile1.mkdirs();
                                File file = new File(datfile1, "CEComponent.txt");

                                try {

                                    FileOutputStream fOut = new FileOutputStream(file, true);
                                    OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                                    myOutWriter.append(entry);
                                    myOutWriter.close();
                                    fOut.close();

                                    rollno.setText("");classtest.setText("");assignment.setText("");
                                    sessional.setText("");
                                    Toast.makeText(CeMarksActivity.this,"Data hass been saved",Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    Toast.makeText(CeMarksActivity.this,"Error :"+e,Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Error :"+e, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });
    }
}
