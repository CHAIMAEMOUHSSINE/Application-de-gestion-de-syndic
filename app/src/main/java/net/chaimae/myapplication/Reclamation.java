package net.chaimae.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reclamations")
public class Reclamation {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String titre;
    private String description;

    public Reclamation(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    // Getters
    public int getId() { return id; }
    public String getTitre() { return titre; }
    public String getDescription() { return description; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setTitre(String titre) { this.titre = titre; }
    public void setDescription(String description) { this.description = description; }
}
