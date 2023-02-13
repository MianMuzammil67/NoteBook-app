package com.mianmuzammil.roomapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mianmuzammil.roomapplication.Activities.AddNotesActivity;
import com.mianmuzammil.roomapplication.Activities.SplashActivity;
import com.mianmuzammil.roomapplication.Adapter.RvMainAdapter;
import com.mianmuzammil.roomapplication.ViewModel.NotesViewModel;
import com.mianmuzammil.roomapplication.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
NotesViewModel viewModel;
RvMainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_200)));


        viewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        viewModel.getAllNotes.observe(this,notes -> {
            adapter= new RvMainAdapter(MainActivity.this,notes);
            binding.mainRecyclerView.setAdapter(adapter);
            binding.mainRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

//            binding.mainRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        });
        binding.btnNewNotes.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddNotesActivity.class));
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.app_bar_search){

        }
        return super.onOptionsItemSelected(item);
    }
}