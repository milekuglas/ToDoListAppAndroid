package com.example.micko.todolistapplication.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.micko.todolistapplication.MainActivity;
import com.example.micko.todolistapplication.R;
import com.example.micko.todolistapplication.database.DatabaseHandler;
import com.example.micko.todolistapplication.fragment.PageFragment;

/**
 * Created by Micko on 22-Oct-16.
 */

public class TaskActivity  extends AppCompatActivity {

    private DatabaseHandler db;
    private int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        Intent intent = getIntent();
        ID = intent.getIntExtra(PageFragment.ID, -1);
        String title = intent.getStringExtra(PageFragment.TITLE);
        String description = intent.getStringExtra(PageFragment.DESCRIPTION);

        TextView textViewTitle = (TextView) findViewById(R.id.text_view_title);
        TextView textViewDescription = (TextView) findViewById(R.id.text_view_description);


        textViewTitle.setText("\n" + title);
        textViewDescription.setText("\n" + description);

        db = new DatabaseHandler(this);

    }


    public void removeTask(View view) {

                AlertDialog.Builder altdial = new AlertDialog.Builder(TaskActivity.this);
                altdial.setMessage("Are you sure you want to remove this task? ").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                deleteTask();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = altdial.create();
                alert.setTitle("Remove task!");
                alert.show();
    }



    public void deleteTask()
    {
        db.deleteTask(ID);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
