package com.example.firebaseexample3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class GetActivity extends AppCompatActivity {

    TextView name,clas,rol,phone;

    Button edit,del;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        name = findViewById(R.id.name);
        clas = findViewById(R.id.clas);
        rol = findViewById(R.id.rol);
        phone = findViewById(R.id.phone);
        del=findViewById(R.id.del);
        edit=findViewById(R.id.edit);

        FirebaseDatabase.getInstance().getReference().child("ankit").child("-M7lFY5aUIWxEvHfzKOd")

                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String nam = dataSnapshot.child("name").getValue(String.class);
                        String cls = dataSnapshot.child("class").getValue(String.class);
                        String roll = dataSnapshot.child("roll").getValue(String.class);
                        String phn = dataSnapshot.child("phone").getValue(String.class);


                        name.setText(nam);
                        clas.setText(cls);
                        rol.setText(roll);
                        phone.setText(phn);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {


                    }
                });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert=new AlertDialog.Builder(GetActivity.this);

                final View dialogView= LayoutInflater.from(GetActivity.this).inflate(R.layout.btn,null,false);
                alert.setView(dialogView);

                final AlertDialog alertDialog=alert.create();
                alertDialog.show();

                Button yes=dialogView.findViewById(R.id.yes);

                Button no=dialogView.findViewById(R.id.no);


                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                      FirebaseDatabase.getInstance().getReference().child("ankit").child("-M7lFY5aUIWxEvHfzKOd")
                              .removeValue();
                        alertDialog.cancel();
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                    }
                });
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert=new AlertDialog.Builder(GetActivity.this);

                final View dialogView= LayoutInflater.from(GetActivity.this).inflate(R.layout.form,null,false);
                alert.setView(dialogView);

                final EditText name_edit,cls_edit,roll_edit,phn_edit;
                Button btn;

                name_edit =dialogView.findViewById(R.id.name_edit);
                cls_edit = dialogView.findViewById(R.id.cls_edit);
                roll_edit = dialogView.findViewById(R.id.roll_edit);
                phn_edit = dialogView.findViewById(R.id.phn_edit);
                btn = dialogView.findViewById(R.id.btn);

                name_edit.setText(name.getText());
                cls_edit.setText(clas.getText());
                roll_edit.setText(rol.getText());
                phn_edit.setText(phone.getText());

                final AlertDialog alertDialog=alert.create();
                alertDialog.show();

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("name",name_edit.getText().toString());
                        hashMap.put("class",cls_edit.getText().toString());
                        hashMap.put("phone",phn_edit.getText().toString());
                        hashMap.put("roll",roll_edit.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("ankit").child("-M7lFY5aUIWxEvHfzKOd")
                                .updateChildren(hashMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        alertDialog.cancel();
                                    }
                                });
                    }
                });

            }
        });

    }
}
