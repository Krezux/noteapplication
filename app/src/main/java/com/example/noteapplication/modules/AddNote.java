package com.example.noteapplication.modules;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.noteapplication.R;
import com.google.android.material.button.MaterialButton;

import io.realm.Realm;

public class AddNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        // core buttons
        @SuppressLint("CutPasteId") EditText input_title = findViewById(R.id.input_title);
        @SuppressLint("CutPasteId") EditText input_desc = findViewById(R.id.input_title);
        MaterialButton input_create = findViewById(R.id.create_note_button);

        // initialize realms
        Realm.init(getApplicationContext());

        Realm realm = Realm.getDefaultInstance();

        input_create.setOnClickListener(v -> {
                String title = input_title.getText().toString();
                String desc = input_desc.getText().toString();

                // save to database

                realm.beginTransaction();

                Notes notes = realm.createObject(Notes.class);
                notes.setTitle(title);
                notes.setDesc(desc);

                realm.commitTransaction(); // save
                Toast.makeText(getApplicationContext(), "NOTE CREATED", Toast.LENGTH_SHORT).show(); // notification
                finish(); // finish activity
        });

    }
}