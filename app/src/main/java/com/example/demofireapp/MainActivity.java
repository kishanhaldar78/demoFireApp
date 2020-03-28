package com.example.demofireapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button mSendData,dataRead;
    TextView view_rc;
    EditText myname,myage,mymno,myheight;
    DatabaseReference reff;
    Member member;
    long maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myname = (EditText) findViewById(R.id.name);
        myage = (EditText) findViewById(R.id.age);
        mymno = (EditText) findViewById(R.id.mno);
        myheight = (EditText) findViewById(R.id.height);
        mSendData = (Button)findViewById(R.id.mybutton);
        view_rc=(TextView)findViewById(R.id.record);
        dataRead = (Button)findViewById(R.id.read_data);
        member= new Member();

        reff= FirebaseDatabase.getInstance().getReference().child("My_DATA");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(myname.getText().toString())){
                    Toast.makeText(MainActivity.this,"Empty Name Field is not allowed",Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(myage.getText().toString())){
                    Toast.makeText(MainActivity.this,"Empty Age Field is not allowed",Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(myheight.getText().toString())){
                    Toast.makeText(MainActivity.this,"Empty Height Field is not allowed",Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(mymno.getText().toString())){
                    Toast.makeText(MainActivity.this,"Empty Phone no. Field is not allowed",Toast.LENGTH_LONG).show();
                }
                else {
                    int myage1 = Integer.parseInt(myage.getText().toString().trim());
                    Long mymno1 = Long.parseLong(mymno.getText().toString().trim());
                    Float myheight1 = Float.parseFloat(myheight.getText().toString().trim());

                    member.setName(myname.getText().toString().trim());

                    member.setAge(myage1);
                    member.setPh(mymno1);
                    member.setHt(myheight1);
                    reff.child(String.valueOf(maxid + 1)).setValue(member);
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    maxid = maxid + 1;
                    String mymax = String.valueOf(maxid);
                    String final_rc = "Total Available Record : " + mymax;
                    view_rc.setText(final_rc);
                }

            }
        });


        dataRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,readdata.class);
                startActivity(intent);
            }
        });



    }

}
