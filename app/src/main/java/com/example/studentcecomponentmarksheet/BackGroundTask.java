package com.example.studentcecomponentmarksheet;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BackGroundTask extends AsyncTask<Void, UserData,Void> {
    private RecyclerView recyclerView;
    private Context context;
    private RecyclerAdapter adapter;
    private ArrayList<UserData> contacts= new ArrayList<>();

    private String roll,name,classtest,sessional,sem,assignment,total;
    public BackGroundTask(RecyclerView recyclerView, Context context) {
        this.recyclerView= recyclerView;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        int i=0;
        String myrollno="";
        String FILENAME = "/StudentDetails/CEComponent.txt";
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
        for(int j=0;j<i;j++)
        {
            String[] tuple = records[j].split("\\|");
            roll=tuple[0];

            int r=0;
            String myrollnor="";
            String FILENAMEr = "/StudentDetails/StudentDetails.txt";
            String namFiler = Environment.getExternalStorageDirectory() + FILENAMEr;
            try {
                FileInputStream fis = new FileInputStream(namFiler);
                DataInputStream in = new DataInputStream(fis);
                BufferedReader br =
                        new BufferedReader(new InputStreamReader(in));
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    if(myrollnor=="") {
                        myrollnor = myrollnor + strLine;
                    }else {
                        myrollnor = myrollnor + "\n" + strLine;
                    }
                    r=r+1;
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] recordsr = myrollnor.split("\n");
            for(int t=0;t<r;t++)
            {
                String[] tupler = recordsr[t].split("\\|");
                String val=tupler[0];
                if(val.equals(roll))
                {
                    name=tupler[1];
                    sem=tupler[2];
                    break;
                }
            }

            classtest=tuple[2];
            sessional=tuple[4];

            assignment=tuple[6];
            total=String.valueOf(Integer.parseInt(classtest)+Integer.parseInt(sessional)+Integer.parseInt(assignment));
            publishProgress(new UserData(roll,name,classtest,sessional,sem,assignment,total));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        adapter= new RecyclerAdapter(contacts);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(UserData... values) {
        contacts.add(values[0]);
        adapter.notifyDataSetChanged();
    }
}
