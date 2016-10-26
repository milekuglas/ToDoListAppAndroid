package com.example.micko.todolistapplication.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.micko.todolistapplication.R;
import com.example.micko.todolistapplication.model.Task;
import com.example.micko.todolistapplication.database.DatabaseHandler;

import java.util.List;

/**
 * Created by Micko on 22-Oct-16.
 */

public class ListViewAdapter extends ArrayAdapter<Task> {

    private List<Task> myTasks;
    private Activity activity;
    private DatabaseHandler db;

    public ListViewAdapter(Activity context, int resource, List<Task> objects) {
        super(context, resource, objects);

        this.activity = context;
        this.myTasks = objects;
        db = new DatabaseHandler(getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.item_listview, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

        holder.checkBox.setTag(position);

        holder.taskTitle.setText(getItem(position).getTitle());
        holder.checkBox.setChecked(getItem(position).isDone());

        holder.checkBox.setOnClickListener(onStateChangedListener(holder.checkBox, position));

        return convertView;
    }

    private View.OnClickListener onStateChangedListener(final CheckBox checkBox, final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    myTasks.get(position).setDone(true);
                } else {
                    myTasks.get(position).setDone(false);
                }

                db.updateTask(myTasks.get(position));
                notifyDataSetChanged();
            }
        };
    }

    private static class ViewHolder {
        private TextView taskTitle;
        private CheckBox checkBox;

        public ViewHolder(View v) {
            checkBox = (CheckBox) v.findViewById(R.id.check);
            taskTitle = (TextView) v.findViewById(R.id.name);
        }
    }
}