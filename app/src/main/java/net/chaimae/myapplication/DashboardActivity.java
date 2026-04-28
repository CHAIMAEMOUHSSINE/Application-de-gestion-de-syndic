package net.chaimae.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.card.MaterialCardView;

public class DashboardActivity extends AppCompatActivity {

    private MaterialCardView cardSolde, cardTravaux, cardReclamation, cardProximite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialisation des cartes
        cardSolde = findViewById(R.id.cardSolde);
        cardTravaux = findViewById(R.id.cardTravaux);
        cardReclamation = findViewById(R.id.cardReclamation);
        cardProximite = findViewById(R.id.cardProximite);

        // Gestion du clic sur la carte Réclamation
        cardReclamation.setOnClickListener(view -> {
            // Navigation vers ReclamationActivity
            Intent intent = new Intent(DashboardActivity.this, ReclamationActivity.class);
            startActivity(intent);
        });

        // Optionnel : Ajouter d'autres clics pour les autres cartes
        cardSolde.setOnClickListener(view -> {
            Toast.makeText(DashboardActivity.this, "Solde de trésorerie", Toast.LENGTH_SHORT).show();
        });

        cardTravaux.setOnClickListener(view -> {
            Toast.makeText(DashboardActivity.this, "Travaux en cours", Toast.LENGTH_SHORT).show();
        });

        cardProximite.setOnClickListener(view -> {
            Toast.makeText(DashboardActivity.this, "À proximité", Toast.LENGTH_SHORT).show();
        });

        // Gestion des insets pour l'affichage EdgeToEdge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}