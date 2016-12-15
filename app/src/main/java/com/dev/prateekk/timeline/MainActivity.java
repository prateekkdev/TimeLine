package com.dev.prateekk.timeline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class Customer {
    private String action, name, number;

    public Customer() {
    }

    public Customer(String action, String name, String number) {
        this.action = action;
        this.name = name;
        this.number = number;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String name) {
        this.action = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class MainActivity extends AppCompatActivity {

    private List<Customer> customerList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CustomerListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new CustomerListAdapter(customerList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareCustomerData();

    }

    private void prepareCustomerData() {
        Customer customer = new Customer("Pick Up", "Prateek1", "11111111");
        customerList.add(customer);

        customer = new Customer("Pick Up", "Prateek2", "2222222");
        customerList.add(customer);

        customer = new Customer("Drop", "Prateek2", "22222222");
        customerList.add(customer);

        customer = new Customer("Drop", "Prateek1", "1111111");
        customerList.add(customer);

        mAdapter.notifyDataSetChanged();
    }
}

class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.MyViewHolder> {

    private List<Customer> customerList;

    public CustomerListAdapter(List<Customer> customerList) {
        this.customerList = customerList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_view_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Customer customer = customerList.get(position);
        holder.action.setText(customer.getAction());
        holder.name.setText(customer.getName());
        holder.number.setText(customer.getNumber());
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView action, name, number;

        public MyViewHolder(View view) {
            super(view);
            action = (TextView) view.findViewById(R.id.action);
            name = (TextView) view.findViewById(R.id.name);
            number = (TextView) view.findViewById(R.id.number);
        }
    }
}