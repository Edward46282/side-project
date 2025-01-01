package com.example.tasktodo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

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
                showAlertDialogue(task);
                return true;
            }
        });

    }

    private void showAlertDialogue(Task task){
        long id = task.getId();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Referring: " + task.getTitle());
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.deleteTask(id);
            }
        });

        builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, EditTask.class);
                intent.putExtra("TASK_ID", task.getId());
                intent.putExtra("TASK_TITLE", task.getTitle());
                intent.putExtra("TASK_PRI", task.getPriority());
                intent.putExtra("TASK_DATE", task.getDate());

                startActivity(intent);

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setTextColor(Color.RED);

        Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negativeButton.setTextColor(Color.GREEN);
    }


    private void showEditDialogue(Task task){
        LayoutInflater inflater = getLayoutInflater();
        //View dialogView = inflater.inflate(R.layout.editordelete_layout, null);
        /*
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.editdialogue, null);

        EditText titleEdit = findViewById(R.id.TitleTask);
        EditText priorityEdit = findViewById(R.id.editPriority);

        titleEdit.setText(task.getTitle());
        priorityEdit.setText(task.getPriority());
        */

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