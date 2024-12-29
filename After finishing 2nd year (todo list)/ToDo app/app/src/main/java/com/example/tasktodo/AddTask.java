package com.example.tasktodo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddTask extends AppCompatActivity {
    private EditText getName;
    private EditText getPriority;
    private Button addTask;
    private CalendarView getDate;

    private int priority;
    private String title;
    private int year, month, day;
    private int[] date;

    DBHandler db;

    Task newTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_task);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getName = findViewById(R.id.taskNameGet);
        getDate = findViewById(R.id.dateTaskGet);
        getPriority = findViewById(R.id.priorityGet);

        addTask = findViewById(R.id.addButton);

        db = new DBHandler(this);
        listenForDate();

        addTask.setOnClickListener(V -> {
            /*
            String value= getDate.getText().toString();
            int finalValue = Integer.parseInt(value);
            */
            title = getName.getText().toString().trim();

            String tmpPriority = getPriority.getText().toString();
            priority = Integer.parseInt(tmpPriority);

            //listenForDate();

            if (!isValid(title, priority)) {
                showToast("invalid input");
                return;
            }

            newTask = new Task(title, date, priority);

            long result = db.addTask(newTask);

            if (result == -1){
                showToast("Failed to save the task");
                return;
            }

            showToast("Successfully added the task");
            //thinking whether to go back to main or not
        });
    }
    private boolean isValid(String name, int priority){
        if (!(name.isBlank()) &&((priority < 11) && (priority > 0))){
            return true;
        }
        return false;
    }

    private void listenForDate(){
        date = new int[3];

        getDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView getDate, int year, int month, int day) {
                date[0] = year;
                date[1] = month + 1;
                date[2] = day;
            }
        });
    }

    private void showToast(String input){
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this, input, duration);
        toast.show();
    }
}