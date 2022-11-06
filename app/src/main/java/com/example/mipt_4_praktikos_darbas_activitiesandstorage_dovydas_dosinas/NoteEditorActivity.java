package com.example.mipt_4_praktikos_darbas_activitiesandstorage_dovydas_dosinas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {

    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        EditText editText = (EditText)findViewById(R.id.editText);
        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);

        if(noteId != -1){
            editText.setText(MainActivity.notes.get(noteId));
        } else{
            MainActivity.notes.add("");
            noteId = MainActivity.notes.size() -1;
            MainActivity.arrayAdapter.notifyDataSetChanged();

            SharedPreferences sharedPreferences = getApplication().getSharedPreferences("com.example.mipt_4_praktikos_darbas_activitiesandstorage_dovydas_dosinas", Context.MODE_PRIVATE);

            HashSet<String> set = new HashSet(MainActivity.notes);

            sharedPreferences.edit().putStringSet("notes", set).apply();

        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                MainActivity.notes.set(noteId, String.valueOf(charSequence));
                MainActivity.arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}