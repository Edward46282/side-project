package com.example.tasktodo;

import android.util.Log;

public class Task {
    private int[] date = new int[3]; //task index 0 = year & month, day
    private int priority;
    private String title;
    private long id;
    private int position;

    public Task(String title, int[] data, int priority){
        this.priority = priority;
        this.title = title;

        try{
            for (int i=0; i<3; i++){
                date[i] = data[i];
            }
        } catch (ArrayIndexOutOfBoundsException e){
            Log.d("Task", "data array index exception");
        }

    }
    public String getTitle(){
        return this.title;
    }

    public int getPriority(){
        return this.priority;
    }

    public int[] getDate(){
        return date;
    }
    public void setId( long id ){
        this.id = id;
    }
    public long getId(){
        return this.id;
    }



}
