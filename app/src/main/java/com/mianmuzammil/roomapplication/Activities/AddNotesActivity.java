package com.mianmuzammil.roomapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;

import com.mianmuzammil.roomapplication.Entity.Notes;
import com.mianmuzammil.roomapplication.R;
import com.mianmuzammil.roomapplication.ViewModel.NotesViewModel;
import com.mianmuzammil.roomapplication.databinding.ActivityAddNotesBinding;

import java.util.Date;
import java.util.Objects;

public class AddNotesActivity extends AppCompatActivity {
ActivityAddNotesBinding binding;
String tittle,subTittle,notes;
NotesViewModel viewModel;
String Priority = "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_200)));
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#03DAC5")));

        viewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        binding.btnDoneNotes.setOnClickListener(v -> {

           tittle= binding.tvTittle.getText().toString();
           subTittle=binding.tvSubtitle.getText().toString();
           notes=binding.tvNotes.getText().toString();

            if (TextUtils.isEmpty(tittle)) {
                binding.tvTittle.setError("Please enter a Tittle");
            }
            if (TextUtils.isEmpty(subTittle)) {
                binding.tvSubtitle.setError("Please enter subTitle");
            }
            if (TextUtils.isEmpty(notes)) {
                binding.tvNotes.setError("Please enter Notes");
            }
            else {
                addData(tittle, subTittle, notes);
                finish();
            }


        });

        binding.yellowPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(R.drawable.icon_done);
            Priority = "1";

        });
        binding.greenPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(R.drawable.icon_done);
            binding.redPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
            Priority = "2";
        });
         binding.redPriority.setOnClickListener(v -> {
             binding.greenPriority.setImageResource(0);
             binding.redPriority.setImageResource(R.drawable.icon_done);
             binding.yellowPriority.setImageResource(0);
             Priority = "3";

         });


    }

    private void addData(String tittle, String subTittle, String notes) {

        Date date= new Date();
        CharSequence sequence= DateFormat.format("d,MM,yyyy",date.getTime());

        Notes notes1 = new Notes();
        notes1.Tittle= tittle;
        notes1.subTittle = subTittle;
        notes1.Notes= notes;
        notes1.date = (String) sequence;
        notes1.priority = Priority;


        viewModel.insertNote(notes1);
    }
}