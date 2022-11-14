package com.example.noteapplication.modules;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapplication.R;

import io.realm.Realm;
import io.realm.RealmResults;

public class Handler extends RecyclerView.Adapter<Handler.noteList>
{
    // taking objects from constructor

    Context context;
    RealmResults<Notes> notesList;

    public Handler(Context context, RealmResults<Notes> notesList) { // constructor
        this.context = context;
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public noteList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new noteList(LayoutInflater.from(context).inflate(R.layout.notes_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull noteList holder, int position) {
        Notes notes = notesList.get(position); // passing pos so we get note

        holder.output_title.setText(notes.getTitle()); // setting titles n desc
        holder.output_desc.setText(notes.getDesc());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu menu = new PopupMenu(context, v);
                menu.getMenu().add("DELETE NOTE");
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        if(item.getTitle().equals("DELETE NOTE"))
                        {
                            Realm realm = Realm.getDefaultInstance();
                            realm.beginTransaction();
                            notes.deleteFromRealm();
                            realm.commitTransaction();
                            Toast.makeText(context, "DELETED NOTE", Toast.LENGTH_SHORT).show();
                        }

                        return true;
                    }
                });


            }
        });

    }

    @Override
    public int getItemCount() {
        return notesList.size(); // return size of notes
    }

    public  class noteList extends RecyclerView.ViewHolder{

        TextView output_title;
        TextView output_desc;

        public noteList(@NonNull View itemView) {
            super(itemView);
            output_title = itemView.findViewById(R.id.output_title);
            output_desc = itemView.findViewById(R.id.output_desc);
        }
    }
}
