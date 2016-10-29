package com.jonathanlieblich.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by jonlieblich on 10/28/16.
 */

public class EmployeeViewAdapter extends RecyclerView.Adapter<EmployeeViewHolder> {
    private List<String> mEmployeeList;

    public EmployeeViewAdapter(List<String> list) {
        mEmployeeList = list;
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.employee_display, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
        String currentText = mEmployeeList.get(holder.getAdapterPosition());
        holder.mName.setText(currentText);
    }

    @Override
    public int getItemCount() {
        return mEmployeeList.size();
    }
}
