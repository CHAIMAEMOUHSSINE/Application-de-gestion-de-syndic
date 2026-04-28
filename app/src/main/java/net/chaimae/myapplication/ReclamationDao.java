package net.chaimae.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ReclamationDao {
    @Insert
    void ajouter(Reclamation reclamation);

    @Update
    void modifier(Reclamation reclamation);

    @Delete
    void supprimer(Reclamation reclamation);

    @Query("SELECT * FROM reclamations")
    List<Reclamation> getAll();

    @Query("SELECT * FROM reclamations WHERE id = :id")
    Reclamation getReclamationById(int id);
}
