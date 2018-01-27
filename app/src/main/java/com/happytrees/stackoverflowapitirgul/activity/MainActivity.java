package com.happytrees.stackoverflowapitirgul.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.happytrees.stackoverflowapitirgul.R;
import com.happytrees.stackoverflowapitirgul.adapter.UsersAdapter;
import com.happytrees.stackoverflowapitirgul.model.User;
import com.happytrees.stackoverflowapitirgul.model.UsersReceived;
import com.happytrees.stackoverflowapitirgul.rest.APIClient;
import com.happytrees.stackoverflowapitirgul.rest.UserEndPoints;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    // -> https://api.stackexchange.com/2.2/users?page=1&pagesize=5&order=desc&site=stackoverflow  --> FIVE USERS WITH HIGHEST REPUTATION IN STACKOVERFLOW
    RecyclerView mRecyclerView;
    List<User> myDataSource = new ArrayList<>();
    RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.usersRecyslerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new UsersAdapter(myDataSource, R.layout.list_item_user,
                getApplicationContext());

        mRecyclerView.setAdapter(myAdapter);

        loadUsers();
    }

    private void loadUsers() {

        UserEndPoints apiService =
                APIClient.getClient().create(UserEndPoints.class);

        Call<UsersReceived> call = apiService.getUsers("reputation");
        call.enqueue(new Callback<UsersReceived>() {
            @Override
            public void onResponse(Call<UsersReceived> call, Response<UsersReceived> response) {


                myDataSource.clear();
                myDataSource.addAll(response.body().getUsers());
                myAdapter.notifyDataSetChanged();

                List<User> users = response.body().getUsers();
                for (User user : users) {
                    System.out.println(
                            "Name: " + user.getUserName() +
                                    "; Location: " + user.getLocation() +
                                    "; Reputation:  " + user.getReputation()
                    );

                    System.out.println("Badges: ");

                    for (Map.Entry<String, Integer> entry : user.getBadges().entrySet()) {
                        String key = entry.getKey().toString();
                        Integer value = entry.getValue();
                        System.out.println(key + " : " + value);
                    }
                }

            }

            @Override
            public void onFailure(Call<UsersReceived> call, Throwable t) {
                System.out.println("FAILURE");

            }
        });

    }
}





























































