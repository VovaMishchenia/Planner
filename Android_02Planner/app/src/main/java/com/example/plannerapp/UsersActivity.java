package com.example.plannerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.plannerapp.myrecycler.CustomAdapter;
import com.example.plannerapp.myrecycler.Model;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity {

    private CustomAdapter customAdapter;
    private RecyclerView recyclerView;
    private List<Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        recyclerView = findViewById(R.id.rcv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1,
                GridLayoutManager.VERTICAL, false));
        list = new ArrayList<Model>();
        String url = "https://www.treehugger.com/thmb/4POnzxta535OuxqNIRBWCUzK39M=/768x0/filters:no_upscale():max_bytes(150000):strip_icc()/horse.primary-e9a47e1c486c4fb7bf729e05b59cf0df.jpg";
        list.add(new Model("Slavik", "23.6", url));
        list.add(new Model("Petro", "2.6", url));
        customAdapter = new CustomAdapter(list, this);
        recyclerView.setAdapter(customAdapter);


    }

    public void OnClick(View view) {

        final TextInputEditText name = findViewById(R.id.textInputName);
        final TextInputLayout nameLayout = findViewById(R.id.textFieldName);
        final TextInputEditText version = findViewById(R.id.textInputVersion);
        final TextInputLayout versionLayout = findViewById(R.id.textFieldVersion);
        final TextInputEditText image = findViewById(R.id.textInputImage);
        final TextInputLayout imageLayout = findViewById(R.id.textFieldImage);
        nameLayout.setError("");
        imageLayout.setError("");
        versionLayout.setError("");
        if (name.getText().toString().equals("") || version.getText().toString().equals("") || image.getText().toString().equals("")) {
            if (name.getText().toString().equals(""))
                nameLayout.setError("fill this field");
            if (version.getText().toString().equals(""))
                versionLayout.setError("fill this field");
            if (image.getText().toString().equals(""))
                imageLayout.setError("fill this field");
        } else {

            list.add(new Model(name.getText().toString(), version.getText().toString(), image.getText().toString()));
            customAdapter.notifyDataSetChanged();
            name.setText("");
            version.setText("");
            image.setText("");
        }


    }
}