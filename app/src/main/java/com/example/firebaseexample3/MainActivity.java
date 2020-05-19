package com.example.firebaseexample3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText name, cls, roll, phn;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name = findViewById(R.id.name);
        cls = findViewById(R.id.cls);
        roll = findViewById(R.id.roll);
        phn = findViewById(R.id.phn);

        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<Object, String> hashMap = new HashMap<>();

                hashMap.put("name", name.getText().toString());
                hashMap.put("class", cls.getText().toString());
                hashMap.put("roll", roll.getText().toString());
                hashMap.put("phone", phn.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("ankit").push().setValue(hashMap);

                Intent a = new Intent(MainActivity.this, GetActivity.class);
                startActivity(a);
            }
        });


    }
}
