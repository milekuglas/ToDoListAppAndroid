package com.example.micko.todolistapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.micko.todolistapplication.R;
import com.example.micko.todolistapplication.model.Task;
import com.example.micko.todolistapplication.activity.TaskActivity;
import com.example.micko.todolistapplication.adapter.ListViewAdapter;
import com.example.micko.todolistapplication.database.DatabaseHandler;

import java.util.List;

/**
 * Created by Micko on 23-Oct-16.
 */

public class PageFragment extends Fragment {
    private static final String ARG_PAGE_NUMBER = "page_number";
    private int pageNumber;

    private List<Task> taskArrayList;
    public final static String ID = "ID";
    public final static String TITLE = "TITLE";
    public final static String DESCRIPTION = "DESCRIPTION";
    public final static String ISDONE = "ISDONE";

    private DatabaseHandler db;
    private ListViewAdapter dataAdapter;


    public PageFragment() {
    }

    public static PageFragment newInstance(int page) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, page);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page_layout, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.list_items);

        pageNumber = this.getArguments().getInt(ARG_PAGE_NUMBER);
        if(pageNumber == 1)
        {
            View header = inflater.inflate(R.layout.header_listview, listView, false);
            listView.addHeaderView(header, null, false);
        }


        db = new DatabaseHandler(getContext());
        readDataFromDatabase();

        dataAdapter = new ListViewAdapter(getActivity(), R.layout.item_listview, taskArrayList);
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(onItemClickListener());
        return rootView;
    }

    private void readDataFromDatabase() {

        //done or not done tasks
        boolean done = false;
        if(this.getArguments().getInt(ARG_PAGE_NUMBER) == 2){
            done = true;
        }
        taskArrayList = db.getAllTasks(done);

    }

    private AdapterView.OnItemClickListener onItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //in first fragment on first position is header view and because of that
                // we need to decrement index
                int move = 0;
                if(pageNumber == 1) {
                    move = 1;
                }
                Intent intent = new Intent(view.getContext(), TaskActivity.class);
                intent.putExtra(ID, taskArrayList.get(position - move).getID());
                intent.putExtra(TITLE, taskArrayList.get(position - move).getTitle());
                intent.putExtra(DESCRIPTION, taskArrayList.get(position - move).getDescription());
                intent.putExtra(ISDONE, taskArrayList.get(position - move).isDone());

                startActivity(intent);
            }
        };
    }

    public void resetData(){
        readDataFromDatabase();
        ListView listView = (ListView) getView().findViewById(R.id.list_items);
        dataAdapter = new ListViewAdapter(getActivity(), R.layout.item_listview, taskArrayList);
        listView.setAdapter(dataAdapter);
    }
}