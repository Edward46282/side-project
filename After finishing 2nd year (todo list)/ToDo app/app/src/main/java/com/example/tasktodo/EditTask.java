package com.example.tasktodo;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditTask extends AppCompatActivity {
    EditText taskTitle;
    EditText taskPriority;

    Button backButton;
    Button editButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.editdialogue);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        DBHandler db = new DBHandler(this);

        Intent intent = getIntent();
        long id = intent.getLongExtra("TASK_ID", -1);
        String title = intent.getStringExtra("TASK_TITLE");
        int priority = intent.getIntExtra("TASK_PRI", -1);
        int[] date = intent.getIntArrayExtra("TASK_DATE");

        taskTitle = findViewById(R.id.TitleTask);
        taskPriority = findViewById(R.id.editPriorityNum);

        if(id == -1){
            Log.d("INVALID_ID", "ID value is -1");
        } else if(priority == -1){
            Log.d("INVALID_PRIORITY", "priority value is -1");
        }

        taskTitle.setText(title);
        taskPriority.setText(String.valueOf(priority));

        backButton = findViewById(R.id.Backbutton);
        editButton = findViewById(R.id.EditButton);

        backButton.setOnClickListener(V->{
            Intent intent1 = new Intent(EditTask.this, MainActivity.class);
            startActivity(intent1);
        });

        editButton.setOnClickListener(V->{
            String titleEdited = taskTitle.getText().toString().trim();
            String tmpPriority = taskPriority.getText().toString().trim();

            int priorityEdited = Integer.parseInt(tmpPriority);

            if(!(isValid(titleEdited, priorityEdited))){
                Toast.makeText(this, "Invalid input",
                        Toast.LENGTH_LONG).show();
                return;
            } else{
                Task newTask = new Task(titleEdited,date,priorityEdited);
                db.updateTask(id, newTask);
            }
        });


    }


    private boolean isValid(String name, int priority){
        if (!(name.isBlank()) &&((priority < 11) && (priority > 0))){
            return true;
        }
        return false;
    }
}
