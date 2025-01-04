package com.example.tasktodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<Task> tasks;
    private View.OnLongClickListener longClickListener;

    public TaskAdapter(List<Task> tasks){ this.tasks = tasks;}

    public void setOnLongClickListener(View.OnLongClickListener listener) {
        this.longClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = tasks.get(position);

        if (longClickListener != null) {
            holder.itemView.setOnLongClickListener(longClickListener);
        }

        holder.title.setText(task.getTitle());
        holder.priority.setText("Priority: " + String.valueOf(task.getPriority()));

        int[] date = task.getDate();
        if (date.length != 3) {
            throw new IllegalArgumentException("Expected array of length 3, but got " + date.length);
        }

        String monthWord = "";

        switch (date[1]) {
            case 1:
                monthWord = "January";
                break;
            case 2:
                monthWord = "February";
                break;
            case 3:
                monthWord = "March";
                break;
            case 4:
                monthWord = "April";
                break;
            case 5:
                monthWord = "May";
                break;
            case 6:
                monthWord = "June";
                break;
            case 7:
                monthWord = "July";
                break;
            case 8:
                monthWord = "August";
                break;
            case 9:
                monthWord = "September";
                break;
            case 10:
                monthWord = "October";
                break;
            case 11:
                monthWord = "November";
                break;
            case 12:
                monthWord = "December";
                break;
        }


        String toPrint = monthWord + "  " + String.valueOf(date[2]) + ", " + String.valueOf(date[0]);
        holder.date.setText(toPrint);

        int year = date[0];
        int month = date[1];
        int day = date[2];

        long calculatedDay = calculateDayDifference(year, month, day);

        if(calculatedDay > 0){
            String tmp = " D - " + calculatedDay;
            holder.dMinus.setText(tmp);
        } else if (calculatedDay < 0 ){
            String tmp = " D + " + Math.abs(calculatedDay);
            holder.dMinus.setText(tmp);
        } else{
            holder.dMinus.setText("Today");
        }


    }

    public long calculateDayDifference(int year, int month, int day) {
        Calendar currentDate = Calendar.getInstance();

        Calendar taskDeadline = Calendar.getInstance();
        taskDeadline.set(year, month - 1, day);

        long differenceInMillis = taskDeadline.getTimeInMillis() - currentDate.getTimeInMillis();
        long daysDifference = differenceInMillis / (1000 * 60 * 60 * 24);

        return daysDifference;
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, priority, dMinus, date;

        public ViewHolder(@NonNull View taskView) {
            super(taskView);
            title = taskView.findViewById(R.id.TaskTitle);
            priority = taskView.findViewById(R.id.taskPriority);
            dMinus = taskView.findViewById(R.id.DMinusCount);
            date = taskView.findViewById(R.id.DateTask);

        }


    }


}
