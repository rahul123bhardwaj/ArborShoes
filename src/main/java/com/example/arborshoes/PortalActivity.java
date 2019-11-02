package com.example.arborshoes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class PortalActivity extends AppCompatActivity implements View.OnClickListener{

    //Declaring Objects
    Button buttonCheckInventory;
    Button buttonChangeInventory;
    Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal);

        //Linking objects with UI objects
        buttonCheckInventory = findViewById(R.id.buttonCheckInventory);
        buttonChangeInventory = findViewById(R.id.buttonChangeInventory);
        buttonLogout = findViewById(R.id.buttonLogout);

        //Adding click listeners to buttons
        buttonChangeInventory.setOnClickListener(this);
        buttonCheckInventory.setOnClickListener(this);
        buttonLogout.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.itemCheckInventory) {
            Intent intent = new Intent(this, CheckInventoryActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.itemChangeInventory) {
            Intent intent = new Intent(this, ChangeInventoryActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.itemPortal) {
            Intent intent = new Intent(this, PortalActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.itemLogout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        return true;
    }

    @Override
    public void onClick(View v) {

        if (v == buttonCheckInventory){
            Intent intent = new Intent(this, CheckInventoryActivity.class);
            startActivity(intent);
        }
        if (v == buttonChangeInventory){
            Intent intent = new Intent(this, ChangeInventoryActivity.class);
            startActivity(intent);
        }
        else if (v == buttonLogout){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
}
