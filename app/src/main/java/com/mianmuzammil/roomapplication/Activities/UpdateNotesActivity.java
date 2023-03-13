package com.mianmuzammil.roomapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.mianmuzammil.roomapplication.Entity.Notes;
import com.mianmuzammil.roomapplication.R;
import com.mianmuzammil.roomapplication.ViewModel.NotesViewModel;
import com.mianmuzammil.roomapplication.databinding.ActivityUpdateNotesBinding;

import java.util.Date;
import java.util.Objects;

public class UpdateNotesActivity extends AppCompatActivity {

    ActivityUpdateNotesBinding binding;
    String Priority = "1";
    String iTittle, iSubTittle, iNotes, iPriority;
    int iId;
    NotesViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_200)));

        viewModel = ViewModelProviders.of(this).get(NotesViewModel.class);


//         getItentData(iId,iTittle,iSubTittle,iNotes,iPriority);
        iId = getIntent().getIntExtra("id", 0);
        iTittle = getIntent().getStringExtra("tittle");
        iSubTittle = getIntent().getStringExtra("subTittle");
        iNotes = getIntent().getStringExtra("notes");
        iPriority = getIntent().getStringExtra("priority");

        binding.upTittle.setText(iTittle);
        binding.upSubTittle.setText(iSubTittle);
        binding.upNotes.setText(iNotes);
//         binding.upTittle.setText(iTittle);

        if (iPriority.equals("1")) {
            binding.upYellowPriority.setImageResource(R.drawable.icon_done);
            binding.upGreenPriority.setImageResource(0);
            binding.upRedPriority.setImageResource(0);
        } else if (iPriority.equals("2")) {
            binding.upGreenPriority.setImageResource(R.drawable.icon_done);
            binding.upRedPriority.setImageResource(0);
            binding.upYellowPriority.setImageResource(0);
        } else if (iPriority.equals("3")) {
            binding.upRedPriority.setImageResource(R.drawable.icon_done);
            binding.upYellowPriority.setImageResource(0);
            binding.upGreenPriority.setImageResource(0);

        }

        binding.btnDoneNotes.setOnClickListener(v -> {

            String tittle = binding.upTittle.getText().toString();
            String subTittle = binding.upSubTittle.getText().toString();
            String notes = binding.upNotes.getText().toString();

            if (TextUtils.isEmpty(tittle)) {
                binding.upTittle.setError("Please enter a Tittle");
            }
            if (TextUtils.isEmpty(subTittle)) {
                binding.upSubTittle.setError("Please enter subTitle");
            }
            if (TextUtils.isEmpty(notes)) {
                binding.upNotes.setError("Please enter Notes");
            } else {
                addData(tittle, subTittle, notes);
            }
            finish();

        });


        binding.upYellowPriority.setOnClickListener(v -> {
            binding.upGreenPriority.setImageResource(0);
            binding.upRedPriority.setImageResource(0);
            binding.upYellowPriority.setImageResource(R.drawable.icon_done);
            Priority = "1";

        });
        binding.upGreenPriority.setOnClickListener(v -> {
            binding.upGreenPriority.setImageResource(R.drawable.icon_done);
            binding.upRedPriority.setImageResource(0);
            binding.upYellowPriority.setImageResource(0);
            Priority = "2";
        });
        binding.upRedPriority.setOnClickListener(v -> {
            binding.upGreenPriority.setImageResource(0);
            binding.upRedPriority.setImageResource(R.drawable.icon_done);
            binding.upYellowPriority.setImageResource(0);
            Priority = "3";

        });


    }

    private void addData(String tittle, String subTittle, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("d,MM,yyyy", date.getTime());
        Notes notes1 = new Notes();
        notes1.id = iId;
        notes1.Tittle = tittle;
        notes1.subTittle = subTittle;
        notes1.Notes = notes;
        notes1.date = (String) sequence;
        notes1.priority = Priority;

        viewModel.UpdateNOte(notes1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.delete_menu, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(UpdateNotesActivity.this);
            View view = LayoutInflater.from(UpdateNotesActivity.this).inflate(R.layout.delete_layout, findViewById(R.id.bottomSheet));
            bottomSheetDialog.setContentView(view);

            TextView Yes, No;
            Yes = view.findViewById(R.id.btnYes);
            No = view.findViewById(R.id.btnNo);
            No.setOnClickListener(v -> {
                bottomSheetDialog.dismiss();
            });
            Yes.setOnClickListener(v -> {
                viewModel.deleteNote(iId);
                finish();

            });


            bottomSheetDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
    //    private void getItentData(String Id, String Tittle, String SubTittle, String Notes, String Priority) {
//
//        Id = getIntent().getStringExtra("id");
//        Tittle = getIntent().getStringExtra("tittle");
//        SubTittle = getIntent().getStringExtra("subTittle");
//        Notes = getIntent().getStringExtra("notes");
//        Priority = getIntent().getStringExtra("priority");
//
//    }


}