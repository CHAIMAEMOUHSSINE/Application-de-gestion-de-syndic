package net.chaimae.myapplication;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ReclamationActivity extends AppCompatActivity {

    private EditText etTitre, etDescription;
    private Button btnAjouter;
    private RecyclerView recyclerReclamations;
    private ArrayList<Reclamation> listReclamations;
    private ReclamationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamation);

        etTitre = findViewById(R.id.etTitre);
        etDescription = findViewById(R.id.etDescription);
        btnAjouter = findViewById(R.id.btnAjouter);
        recyclerReclamations = findViewById(R.id.recyclerReclamations);

        // Initialisation avec de VRAIES réclamations
        listReclamations = new ArrayList<>();
        listReclamations.add(new Reclamation("Fuite d'eau", "Fuite au niveau de la cuisine"));
        listReclamations.add(new Reclamation("Ascenseur en panne", "Ascenseur bloqué au 3ème étage"));
        listReclamations.add(new Reclamation("Nettoyage parking", "Parking non nettoyé depuis 3 jours"));
        listReclamations.add(new Reclamation("Éclairage public", "Lampadaire cassé devant l'entrée"));
        listReclamations.add(new Reclamation("Déchets non collectés", "Poubelles pleines depuis une semaine"));

        adapter = new ReclamationAdapter(listReclamations);
        recyclerReclamations.setLayoutManager(new LinearLayoutManager(this));
        recyclerReclamations.setAdapter(adapter);

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

            Reclamation nouvelleReclamation = new Reclamation(titre, description);
            adapter.ajouterReclamation(nouvelleReclamation);

            etTitre.setText("");
            etDescription.setText("");

            Toast.makeText(this, "Réclamation ajoutée", Toast.LENGTH_SHORT).show();
        });
    }
}