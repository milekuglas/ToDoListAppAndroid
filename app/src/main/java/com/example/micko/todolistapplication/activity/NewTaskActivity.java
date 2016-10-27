package com.example.micko.todolistapplication.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.micko.todolistapplication.MainActivity;
import com.example.micko.todolistapplication.R;
import com.example.micko.todolistapplication.database.DatabaseHandler;
import com.example.micko.todolistapplication.model.Task;

/**
 * Created by Micko on 22-Oct-16.
 */

public class NewTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
    }

    public void addNewTask(View view) {
        EditText title = (EditText) findViewById(R.id.edit_text_title);
        EditText desciption = (EditText) findViewById(R.id.edit_text_description);

        if(title.length() == 0 || title.equals("") || title == null)
        {
            AlertDialog.Builder altdial = new AlertDialog.Builder(NewTaskActivity.this);
            altdial.setMessage("Please enter title.").setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            //deleteTask();
                        }
                    });
                    /*.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });*/

            AlertDialog alert = altdial.create();
            alert.setTitle("Add task!");
            alert.show();
        } else {

            DatabaseHandler db = new DatabaseHandler(this);
            db.addTask(new Task(title.getText().toString(), desciption.getText().toString(), false));

            Intent intent = new Intent(view.getContext(), MainActivity.class);

            startActivity(intent);
        }
    }
}
