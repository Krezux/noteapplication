package com.example.noteapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapplication.modules.AddNote;
import com.example.noteapplication.modules.Handler;
import com.example.noteapplication.modules.Notes;
import com.google.android.material.button.MaterialButton;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton add_note = findViewById(R.id.add_note);

        add_note.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddNote.class))
        );

        // initialize realm

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Notes> notesList = realm.where(Notes.class).findAll();

        // recyclerview

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Handler handler = new Handler(getApplicationContext(), notesList);
        recyclerView.setAdapter(handler); // setting handler in recyclerview

        notesList.addChangeListener(new RealmChangeListener<RealmResults<Notes>>() {
            @Override
            public void onChange(RealmResults<Notes> notes) {
                handler.notifyDataSetChanged();
            }
        }); // if note list is increased

    }
}