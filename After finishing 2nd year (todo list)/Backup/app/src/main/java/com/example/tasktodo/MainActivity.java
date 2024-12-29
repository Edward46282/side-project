package com.example.tasktodo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button addButton;
    private RecyclerView taskView;
    DBHandler db;
    List<Task> tasks;
    TaskAdapter adapterTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        db = new DBHandler(this);

        addButton = findViewById(R.id.AddButton);
        taskView = (RecyclerView) findViewById(R.id.TaskView);
        taskView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addButton.setOnClickListener(V ->{
            addTask();
                });

        taskView.setLongClickable(true);


    }

    private void fetchData(){
        tasks = db.fetchAllTasks();
        adapterTask = new TaskAdapter(tasks);
        taskView.setAdapter(adapterTask);

        // make line between the contents
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        taskView.addItemDecoration(divider);

        adapterTask.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = taskView.getChildLayoutPosition(v);
                Task task = tasks.get(position);
                showEditDialogue(task);
                return false; //dummy
            }
        });

    }

    private void showEditDialogue(Task task){

    }



    public void addTask(){
        Intent intent = new Intent(MainActivity.this, AddTask.class);
        startActivity(intent);
        adapterTask.notifyItemInserted(tasks.size()-1);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}