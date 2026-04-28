package net.chaimae.myapplication;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ReclamationAdapter extends RecyclerView.Adapter<ReclamationAdapter.ViewHolder> {

    private ArrayList<Reclamation> listReclamations;

    public ReclamationAdapter(ArrayList<Reclamation> listReclamations) {
        this.listReclamations = listReclamations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reclamation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reclamation reclamation = listReclamations.get(position);
        holder.txtTitre.setText(reclamation.getTitre());  // ← Affiche le vrai titre
        // holder.txtDescription.setText(reclamation.getDescription()); // Décommentez si besoin
    }

    @Override
    public int getItemCount() {
        return listReclamations.size();
    }

    public void ajouterReclamation(Reclamation reclamation) {
        listReclamations.add(reclamation);
        notifyItemInserted(listReclamations.size() - 1);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitre, txtDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitre = itemView.findViewById(R.id.txtTitre);
            txtDescription = itemView.findViewById(R.id.txtDescription);
        }
    }
}