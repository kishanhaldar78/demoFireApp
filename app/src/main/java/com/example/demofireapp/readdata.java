package com.example.demofireapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class readdata extends AppCompatActivity {
TextView a,b,c,d,record_view;
EditText edit_rc;
Button btn,setRec;
DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readdata);
        a=(TextView)findViewById(R.id.name);
        b=(TextView)findViewById(R.id.age);
        c=(TextView)findViewById(R.id.height);
        d=(TextView)findViewById(R.id.mno);
        btn=(Button)findViewById(R.id.view);
        record_view=(TextView)findViewById(R.id.setView);
        setRec =(Button)findViewById(R.id.set);
        edit_rc=(EditText)findViewById(R.id.enter_rc);


        record_view = (TextView)findViewById(R.id.setView);

        setRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     int myrec=Integer.parseInt(edit_rc.getText().toString().trim());
                     String myrec1=String.valueOf(myrec);
                     reff= FirebaseDatabase.getInstance().getReference().child("My_DATA").child(myrec1);
                  record_view.setText("Record number "+myrec1+" Information");
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  reff= FirebaseDatabase.getInstance().getReference().child("My_DATA").child("5");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String name = dataSnapshot.child("name").getValue().toString();
                        String age = dataSnapshot.child("age").getValue().toString();
                        String height = dataSnapshot.child("ht").getValue().toString();
                        String phone = dataSnapshot.child("ph").getValue().toString();
                      //  if (name.isEmpty() || age.isEmpty() || height.isEmpty() || phone.isEmpty()) {
                        //    Toast.makeText(readdata.this,"Empty Field is not allowed",Toast.LENGTH_LONG).show();
                        //} else {
                            a.setText(name);
                            b.setText(age);
                            c.setText(height);
                            d.setText(phone);
                        }
                    //}

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
