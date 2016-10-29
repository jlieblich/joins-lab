package com.jonathanlieblich.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jonlieblich on 10/28/16.
 */

public class EmployeeViewHolder extends RecyclerView.ViewHolder {
    public TextView mName;

    public EmployeeViewHolder(View itemView) {
        super(itemView);
        mName = (TextView) itemView.findViewById(R.id.employee_or_company_name);
    }
}
