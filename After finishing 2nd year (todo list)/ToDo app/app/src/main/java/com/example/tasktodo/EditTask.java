package com.example.tasktodo;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

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
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.editdialogue);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        long id = intent.getLongExtra("TASK_ID", -1);
        String title = intent.getStringExtra("TASK_TITLE");
        int priority = intent.getIntExtra("TASK_PRI", -1);

        taskTitle = findViewById(R.id.TitleTask);
        taskPriority = findViewById(R.id.editPriorityNum);

        if(id == -1){
            Log.d("INVALID_ID", "ID value is -1");
        } else if(priority == -1){
            Log.d("INVALID_PRIORITY", "priority value is -1");
        }

        taskTitle.setText(title);
        taskPriority.setText(String.valueOf(priority));


    }
}
