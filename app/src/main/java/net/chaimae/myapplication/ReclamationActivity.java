package net.chaimae.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ReclamationActivity extends AppCompatActivity {

    private EditText etTitre, etDescription;
    private Button btnAjouter;
    private RecyclerView recyclerReclamations;
    private AppDatabase db;
    private ReclamationAdapter adapter;
    private Reclamation reclamationEnModification = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamation);

        // Initialisation de la base de données
        db = AppDatabase.getInstance(this);

        etTitre = findViewById(R.id.etTitre);
        etDescription = findViewById(R.id.etDescription);
        btnAjouter = findViewById(R.id.btnAjouter);
        recyclerReclamations = findViewById(R.id.recyclerReclamations);

        // Configuration du RecyclerView
        recyclerReclamations.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ReclamationAdapter(new ReclamationAdapter.OnReclamationListener() {
            @Override
            public void onModifier(Reclamation reclamation) {
                // Afficher la réclamation dans le formulaire pour modification
                reclamationEnModification = reclamation;
                etTitre.setText(reclamation.getTitre());
                etDescription.setText(reclamation.getDescription());
                btnAjouter.setText("Modifier la réclamation");
            }

            @Override
            public void onSupprimer(Reclamation reclamation) {
                // Supprimer la réclamation de la base de données
                new Thread(() -> {
                    db.reclamationDao().supprimer(reclamation);
                    runOnUiThread(() -> {
                        chargerReclamations();
                        Toast.makeText(ReclamationActivity.this, "Réclamation supprimée", Toast.LENGTH_SHORT).show();
                    });
                }).start();
            }
        });

        recyclerReclamations.setAdapter(adapter);

        // Charger les réclamations
        chargerReclamations();

        // Gestion du clic sur le bouton
        btnAjouter.setOnClickListener(v -> {
            String titre = etTitre.getText().toString().trim();
            String description = etDescription.getText().toString().trim();

            if (titre.isEmpty()) {
                etTitre.setError("Veuillez saisir un titre");
                return;
            }

            if (description.isEmpty()) {
                etDescription.setError("Veuillez saisir une description");
                return;
            }

            if (reclamationEnModification != null) {
                // Mode Modification
                reclamationEnModification.setTitre(titre);
                reclamationEnModification.setDescription(description);

                new Thread(() -> {
                    db.reclamationDao().modifier(reclamationEnModification);
                    runOnUiThread(() -> {
                        chargerReclamations();
                        reinitialiserFormulaire();
                        Toast.makeText(ReclamationActivity.this, "Réclamation modifiée", Toast.LENGTH_SHORT).show();
                    });
                }).start();
            } else {
                // Mode Ajout
                Reclamation nouvelleReclamation = new Reclamation(titre, description);

                new Thread(() -> {
                    db.reclamationDao().ajouter(nouvelleReclamation);
                    runOnUiThread(() -> {
                        chargerReclamations();
                        reinitialiserFormulaire();
                        recyclerReclamations.scrollToPosition(adapter.getItemCount() - 1);
                        Toast.makeText(ReclamationActivity.this, "Réclamation ajoutée", Toast.LENGTH_SHORT).show();
                    });
                }).start();
            }
        });
    }

    private void chargerReclamations() {
        new Thread(() -> {
            List<Reclamation> reclamations = db.reclamationDao().getAll();
            runOnUiThread(() -> {
                adapter.setReclamations(reclamations);
            });
        }).start();
    }

    private void reinitialiserFormulaire() {
        etTitre.setText("");
        etDescription.setText("");
        btnAjouter.setText("Ajouter la réclamation");
        reclamationEnModification = null;
    }
}