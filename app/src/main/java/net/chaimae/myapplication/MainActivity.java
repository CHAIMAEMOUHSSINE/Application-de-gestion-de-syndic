package net.chaimae.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btnContinuer = findViewById(R.id.btnContinuer);

        btnContinuer.setOnClickListener(view -> {
            Toast.makeText(this, "Bienvenue dans l'application de gestion de syndic", Toast.LENGTH_SHORT).show();

            //  Navigation vers Dashboard
            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
            startActivity(intent);
        });
    }
}