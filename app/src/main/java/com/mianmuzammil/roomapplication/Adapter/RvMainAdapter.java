package com.mianmuzammil.roomapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mianmuzammil.roomapplication.Activities.UpdateNotesActivity;
import com.mianmuzammil.roomapplication.Entity.Notes;
import com.mianmuzammil.roomapplication.R;

import java.util.List;

public class RvMainAdapter extends RecyclerView.Adapter<RvMainAdapter.viewHolder> {
    Context context;
    List<Notes> list;

    public RvMainAdapter(Context context, List<Notes> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public RvMainAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_main_view,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvMainAdapter.viewHolder holder, int position) {

        Notes note = list.get(position);

        if (note.priority.equals("1") ){
            holder.priority.setBackgroundResource(R.drawable.yellow_shape);
        }else if (note.priority.equals("2")){
            holder.priority.setBackgroundResource(R.drawable.green_shape);
        }else if (note.priority.equals("3")){
            holder.priority.setBackgroundResource(R.drawable.red_shape);
        }



        holder.tittle.setText(note.Tittle);
        holder.subTittle.setText(note.subTittle);
        holder.notes.setText(note.Notes);
        holder.date.setText(note.date);
        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context, UpdateNotesActivity.class);
            intent.putExtra("id",note.id);
            intent.putExtra("tittle",note.Tittle);
            intent.putExtra("subTittle",note.subTittle);
            intent.putExtra("notes",note.Notes);
            intent.putExtra("priority",note.priority);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView tittle,subTittle,notes,date;
        View priority;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tittle=itemView.findViewById(R.id.mtvTittle);
            subTittle= itemView.findViewById(R.id.msubTittle);
            notes = itemView.findViewById(R.id.mtvNotes);
            date = itemView.findViewById(R.id.mdate);
            priority =itemView.findViewById(R.id.priority);

        }
    }
}
