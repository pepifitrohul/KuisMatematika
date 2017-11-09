package com.example.pep.kuismatematika;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String opr;
    EditText angka1,angka2,operator,isi;
    TextView txtjwb,txtjb,txtjs,txt7,txt8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        angka1=(EditText)findViewById(R.id.edtangk1);
        operator=(EditText)findViewById(R.id.edtopr);
        angka2=(EditText)findViewById(R.id.edtangk2);
        isi=(EditText)findViewById(R.id.edtisi);
        txtjwb=(TextView)findViewById(R.id.txt4);
        txtjb=(TextView)findViewById(R.id.txt5);
        txtjs=(TextView)findViewById(R.id.txt6);
        txt7=(TextView)findViewById(R.id.txt7);
        txt8=(TextView)findViewById(R.id.txt8);
    }
    public void newpr(View view) {
        int a,b;
        opr=operator();
        operator.setText(opr);
        a=(int)(Math.random()*100+1);
        b=(int)(Math.random()*a+1);
        angka1.setText(Integer.toString(a));
        angka2.setText(Integer.toString(b));
        txtjwb.setText("Your Answer is ");
    }
    public void cekans(View view) {
        int a,b,c,win=0,lose=0,hasil=0;

        a=Integer.parseInt(angka1.getText().toString());
        b=Integer.parseInt(angka2.getText().toString());
        c=Integer.parseInt(isi.getText().toString());
        win=Integer.parseInt(txtjb.getText().toString());
        lose=Integer.parseInt(txtjs.getText().toString());
        if(opr=="+"){
            hasil=a+b;
        }else if(opr=="-"){
            hasil=a-b;
        }else if(opr=="x"){
            hasil=a*b;
        }else if(opr=="/"){
            hasil=a/b;
        }

        if(c==hasil){
            win=win+1;
            txtjb.setText(Integer.toString(win));
            Toast.makeText(this,Integer.toString(win),Toast.LENGTH_SHORT).show();
            txtjwb.setText("Your Answer Is Correct");
        }else {
            lose=lose+1;
            txtjs.setText(Integer.toString(lose));
            Toast.makeText(this,Integer.toString(lose),Toast.LENGTH_SHORT).show();
            txtjwb.setText("Your Answer is Wrong");
        }


        SharedPreferences sh=getSharedPreferences("MyOwnShared",MODE_PRIVATE);
        SharedPreferences.Editor myshedt=sh.edit();
        myshedt.putString("win",txtjb.getText().toString());
        myshedt.putString("lose",txtjs.getText().toString());
        myshedt.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences shd=getSharedPreferences("MyOwnShared",MODE_APPEND);
        String s1=shd.getString("win","");
        String i1=shd.getString("lose",""); //mendapatkan nilai yang telah disave
        txtjb.setText(s1);
        txtjs.setText(i1);
    }

    public String operator(){
        String opr="s";
        int a=(int)(Math.random()*4+1);
        switch (a){
            case 1:opr="+"; break;
            case 2:opr="-"; break;
            case 3:opr="x"; break;
            case 4:opr="/"; break;
        }
        return opr;
    }
}
