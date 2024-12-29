package com.example.tasktodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "taskManager";

    private static final String TABLE_TASKS = "tasks"; //it is important because I need to aware how it is
    //stored
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_PRIORITY = "priority";
    private static final String KEY_DATE = "date";

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_TASKS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_TITLE + " TEXT, "
                + KEY_PRIORITY + " INTEGER, "
                + KEY_DATE + " TEXT "
                + ")";
        sqLiteDatabase.execSQL(CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //delete the existing table when it is updated
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        // Recreating the table
        onCreate(sqLiteDatabase);
    }

    public long addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String title = task.getTitle();
        int priority = task.getPriority();
        int[] date = task.getDate();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < date.length; i++) {
            sb.append(date[i]);
            if(i != date.length-1){
                sb.append(",");
            }
        }
        String toAdd = sb.toString();

        values.put(KEY_TITLE, title);
        values.put(KEY_PRIORITY, priority);
        values.put(KEY_DATE, toAdd);

        long result = db.insert(TABLE_TASKS, null, values); //creating new row
        db.close();

        return result;
    }

    public void deleteTask(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKS, KEY_ID + " = ?", new String[]{String.valueOf(id)} //the last parameter
                //is just changing id into string representation and put it in the array
        );
        db.close();
    }

    public void updateTask(int id, Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String title = task.getTitle();
        int priority = task.getPriority();
        int[] date = task.getDate();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < date.length; i++) {
            sb.append(date[i]);
            if (i != (date.length - 1)) {
                sb.append(",");
            }
        }
        String toAdd = sb.toString();

        values.put(KEY_TITLE, title);
        values.put(KEY_PRIORITY, priority);
        values.put(KEY_DATE, toAdd);

        db.update(TABLE_TASKS, values, KEY_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Task> fetchAllTasks() {
        List<Task> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Query the database
        Cursor cursor = db.query(
                TABLE_TASKS,           // Table name
                null,                  // Columns (null = all columns)
                null,                  // Selection (null = no WHERE clause)
                null,                  // Selection arguments
                null,                  // Group by
                null,                  // Having
                null                   // Order by
        );

        // Check if the cursor has data and iterate through it
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Use getTaskFromCursor to convert each row into a Task object
                Task task = getTaskFromCursor(cursor);
                taskList.add(task); // Add the Task to the list
            } while (cursor.moveToNext());

            cursor.close(); // Close the cursor
        }

        db.close(); // Close the database
        return taskList;
    }

    // Convert a cursor row into a Task object
    private Task getTaskFromCursor(Cursor cursor) {
        String title = cursor.getString(cursor.getColumnIndexOrThrow(KEY_TITLE));
        int priority = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_PRIORITY));
        String date = cursor.getString(cursor.getColumnIndexOrThrow(KEY_DATE));

        int[] arrDate = new int[3];
        StringBuilder accumulate = new StringBuilder();
        int counter = 0;
        for(int i=0;i<date.length();i++){
            if(date.charAt(i) == ','){
                arrDate[counter] = Integer.parseInt(accumulate.toString());
                accumulate.setLength(0); //reset
                counter++;
            } else {
                accumulate.append(date.charAt(i));
            }
        }

        return new Task(title, arrDate, priority);
    }
}
