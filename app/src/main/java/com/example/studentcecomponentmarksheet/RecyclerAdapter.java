package com.example.studentcecomponentmarksheet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<UserData> contacts = new ArrayList<>();

    public RecyclerAdapter(ArrayList<UserData> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_layout, parent,false);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.roll.setText(contacts.get(position).getRoll());
        holder.name.setText(contacts.get(position).getName());
        holder.classtest.setText(contacts.get(position).getClasstest());
        holder.sessional.setText(contacts.get(position).getSessional());
        holder.sem.setText(contacts.get(position).getSem());
        holder.assignment.setText(contacts.get(position).getAssignment());
        holder.total.setText(contacts.get(position).getTotal());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView roll,name,classtest,sessional,sem,assignment,total;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roll=itemView.findViewById(R.id.rollnot1);
            name=itemView.findViewById(R.id.namet2);
            classtest=itemView.findViewById(R.id.classtestt4);
            sessional=itemView.findViewById(R.id.sessionalt5);
            sem=itemView.findViewById(R.id.semestert6);
            assignment=itemView.findViewById(R.id.assignmentt7);
            total=itemView.findViewById(R.id.Totalt8);

        }
    }
}
