package com.mianmuzammil.roomapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mianmuzammil.roomapplication.Activities.AddNotesActivity;
import com.mianmuzammil.roomapplication.Adapter.RvMainAdapter;
import com.mianmuzammil.roomapplication.Entity.Notes;
import com.mianmuzammil.roomapplication.ViewModel.NotesViewModel;
import com.mianmuzammil.roomapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    NotesViewModel viewModel;
    RvMainAdapter adapter;
    List<Notes> filterdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_200)));
        viewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        binding.tvDefault.setOnClickListener(view -> {
            loadDataToCat(1);
            binding.tvDefault.setBackgroundResource(R.drawable.cat_bg_slected);
                binding.tvDefault.setTextColor(getResources().getColor(R.color.white));
                binding.tvLtoH.setBackgroundResource(0);
                binding.tvLtoH.setTextColor(getResources().getColor(R.color.tv_colour));
                binding.tvHtoL.setBackgroundResource(0);
                binding.tvHtoL.setTextColor(getResources().getColor(R.color.tv_colour));
        });
        binding.tvLtoH.setOnClickListener(view -> {
            loadDataToCat(2);
            binding.tvLtoH.setBackgroundResource(R.drawable.cat_bg_slected);
            binding.tvLtoH.setTextColor(getResources().getColor(R.color.white));
            binding.tvDefault.setBackgroundResource(0);
            binding.tvDefault.setTextColor(getResources().getColor(R.color.tv_colour));
            binding.tvHtoL.setBackgroundResource(0);
            binding.tvHtoL.setTextColor(getResources().getColor(R.color.tv_colour));

        });
        binding.tvHtoL.setOnClickListener(view -> {
            loadDataToCat(3);
            binding.tvHtoL.setBackgroundResource(R.drawable.cat_bg_slected);
            binding.tvHtoL.setTextColor(getResources().getColor(R.color.white));
            binding.tvLtoH.setBackgroundResource(0);
            binding.tvLtoH.setTextColor(getResources().getColor(R.color.tv_colour));
            binding.tvDefault.setBackgroundResource(0);
            binding.tvDefault.setTextColor(getResources().getColor(R.color.tv_colour));

        });
        binding.btnNewNotes.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddNotesActivity.class));
        });
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                filterNotes(newText);

                return false;
            }
        });

        viewModel.getAllNotes.observe(this, notes -> {
            setAdapter(notes);
            filterdata = notes;
//            adapter = new RvMainAdapter(MainActivity.this, notes);
//            binding.mainRecyclerView.setAdapter(adapter);
//            binding.mainRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

//            binding.mainRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        });
    }
    private void filterNotes(String Text) {
        ArrayList<Notes> filteredList = new ArrayList<>();
        for (Notes notes : filterdata){
            if(notes.Tittle.contains(Text)){
                filteredList.add(notes);
            }
        }
        adapter.filteredData(filteredList);
    }

    public void loadDataToCat(int i) {
        if (i == 1){
            viewModel.getAllNotes.observe(this, notes -> {
                setAdapter(notes);
                filterdata = notes;
            });
        }else if (i== 2){
            viewModel.lowToHigh.observe(this, notes -> {
                setAdapter(notes);
                filterdata = notes;
            });
        }else if (i== 3){
            viewModel.highToLow.observe(this, notes -> {
                setAdapter(notes);
                filterdata = notes;
            });
        }
    }
    void setAdapter(List<Notes> notes) {
        adapter = new RvMainAdapter(MainActivity.this, notes);
        binding.mainRecyclerView.setAdapter(adapter);
        binding.mainRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.search_menu,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.app_bar_search){
//
//        }
//        return super.onOptionsItemSelected(item);
//    }

}