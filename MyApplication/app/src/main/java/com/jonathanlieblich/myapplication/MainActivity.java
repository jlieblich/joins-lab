package com.jonathanlieblich.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mEmployeeView;
    private EmployeeViewAdapter mAdapter;
    private Button mByCompany, mByCity, mHighestPaid;
    public List<String> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Helper helper = Helper.getInstance(this);


//        helper.addEmployee(new Employee("John", "Smith", "NY", "123-04-5678", 1973));
//        helper.addEmployee(new Employee("David", "McWill", "Seattle", "123-04-5679", 1982));
//        helper.addEmployee(new Employee("Katerina", "Wise", "Boston", "123-04-5680", 1973));
//        helper.addEmployee(new Employee("Donald", "Lee", "London", "123-04-5681", 1992));
//        helper.addEmployee(new Employee("Gary", "Henwood", "Las Vegas", "123-04-5682", 1987));
//        helper.addEmployee(new Employee("Anthony", "Bright", "Seattle", "123-04-5683", 1963));
//        helper.addEmployee(new Employee("Willliam", "Newey", "Boston", "123-04-5684", 1995));
//        helper.addEmployee(new Employee("Melony", "Smith", "Chicago", "123-04-5685", 1970));
//
//        helper.addJob(new Job("123-04-5678", "Fuzz", 60, 1));
//        helper.addJob(new Job("123-04-5679", "GA", 70, 2));
//        helper.addJob(new Job("123-04-5680", "Little Place", 120, 5));
//        helper.addJob(new Job("123-04-5681", "Macys", 78, 3));
//        helper.addJob(new Job("123-04-5682", "New Life", 65, 1));
//        helper.addJob(new Job("123-04-5683", "Believe", 158, 6));
//        helper.addJob(new Job("123-04-5684", "Macys", 200, 8));
//        helper.addJob(new Job("123-04-5685", "Stop", 299, 12));

        mByCompany = (Button)findViewById(R.id.by_company);
        mByCity = (Button)findViewById(R.id.by_city);
        mHighestPaid = (Button)findViewById(R.id.highest_salary);

        nameList = helper.getAllNames();
        mAdapter = new EmployeeViewAdapter(nameList);

        mEmployeeView = (RecyclerView)findViewById(R.id.data_recycler_view);
        mEmployeeView.setAdapter(mAdapter);
        mEmployeeView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()) {
                    case R.id.by_company:
                        nameList.clear();
                        nameList.addAll(helper.nameByCompany());
                        mAdapter.notifyDataSetChanged();
                        break;
                    case R.id.by_city:
                        nameList.clear();
                        nameList.addAll(helper.companyByCity());
                        mAdapter.notifyDataSetChanged();
                        break;
                    case R.id.highest_salary:
                        nameList.clear();
                        nameList.addAll(helper.highestPaidEmployee());
                        mAdapter.notifyDataSetChanged();
                        break;
                }
            }
        };

        mByCompany.setOnClickListener(onClickListener);
        mByCity.setOnClickListener(onClickListener);
        mHighestPaid.setOnClickListener(onClickListener);
    }
}
