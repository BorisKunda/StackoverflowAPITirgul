package com.happytrees.stackoverflowapitirgul.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.happytrees.stackoverflowapitirgul.R;
import com.happytrees.stackoverflowapitirgul.model.User;

import java.util.Iterator;
import java.util.List;
import java.util.Map;



public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {

    private List<User> users;
    private Context context;
    private int rowLayout;

    public UsersAdapter(List<User> users, int rowLayout, Context context) {
        this.setUsers(users);
        this.setRowLayout(rowLayout);
        this.setContext(context);
    }

    public List<User> getUsers() {return users;}

    public void setUsers(List<User> users) {this.users = users;}

    public Context getContext() {return context;}

    public void setContext(Context context) {this.context = context;}

    public int getRowLayout() {return rowLayout;}

    public void setRowLayout(int rowLayout) {this.rowLayout = rowLayout;}

    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        LinearLayout usersLayout;
        TextView userName;
        TextView userReputation;
        TextView userLocation;

        TextView goldenBdge;
        TextView goldenValue;
        TextView silverBadge;
        TextView silverValue;
        TextView bronzeBadge;
        TextView bronzeValue;

        public UsersViewHolder(View itemView) {
            super(itemView);
            usersLayout = (LinearLayout) itemView.findViewById(R.id.usersLayout);
            userName = (TextView) itemView.findViewById(R.id.userName);
            userReputation = (TextView) itemView.findViewById(R.id.userReputation);
            userLocation = (TextView) itemView.findViewById(R.id.userLocation);
            goldenBdge = (TextView) itemView.findViewById(R.id.goldenBadge);
            goldenValue = (TextView) itemView.findViewById(R.id.goldenValue);
            silverBadge = (TextView) itemView.findViewById(R.id.silverBadge);
            silverValue = (TextView) itemView.findViewById(R.id.silverValue);
            bronzeBadge = (TextView) itemView.findViewById(R.id.bronzeBadge);
            bronzeValue = (TextView) itemView.findViewById(R.id.bronzeValue);
        }
    }




    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(rowLayout, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, int position) {
        holder.userLocation.setText("Location: "+users.get(position).getLocation());
        holder.userReputation.setText("Reputation: " + users.get(position).getReputation());
        holder.userName.setText("Username: " + users.get(position).getUserName());

        Iterator<Map.Entry<String,Integer>> it  =
                users.get(position).getBadges().entrySet().iterator();

        Map.Entry<String,Integer> pair = it.next();
        holder.goldenBdge.setText(pair.getKey() + " : ");
        holder.goldenValue.setText(pair.getValue().toString());

        pair = it.next();
        holder.silverBadge.setText(pair.getKey() + " : ");
        holder.silverValue.setText(pair.getValue().toString());

        pair = it.next();
        holder.bronzeBadge.setText(pair.getKey() + " : ");
        holder.bronzeValue.setText(pair.getValue().toString());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

}
