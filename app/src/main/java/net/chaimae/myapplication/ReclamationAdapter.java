package net.chaimae.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ReclamationAdapter extends RecyclerView.Adapter<ReclamationAdapter.ViewHolder> {

    private List<Reclamation> listReclamations;
    private OnReclamationListener listener;

    public void ajouterReclamation(Reclamation nouvelleReclamation) {
    }

    public interface OnReclamationListener {
        void onModifier(Reclamation reclamation);
        void onSupprimer(Reclamation reclamation);
    }

    public ReclamationAdapter(OnReclamationListener listener) {
        this.listReclamations = new ArrayList<>();
        this.listener = listener;
    }

    public void setReclamations(List<Reclamation> reclamations) {
        this.listReclamations = reclamations;
        notifyDataSetChanged();
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
        holder.txtTitre.setText(reclamation.getTitre());
        holder.txtDescription.setText(reclamation.getDescription());

        holder.btnModifier.setOnClickListener(v -> {
            if (listener != null) {
                listener.onModifier(reclamation);
            }
        });

        holder.btnSupprimer.setOnClickListener(v -> {
            if (listener != null) {
                listener.onSupprimer(reclamation);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listReclamations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitre, txtDescription;
        ImageView btnModifier, btnSupprimer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitre = itemView.findViewById(R.id.txtTitre);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            btnModifier = itemView.findViewById(R.id.btnModifier);
            btnSupprimer = itemView.findViewById(R.id.btnSupprimer);
        }
    }
}