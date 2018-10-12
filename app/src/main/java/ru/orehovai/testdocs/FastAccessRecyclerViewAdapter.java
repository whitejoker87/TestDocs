package ru.orehovai.testdocs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FastAccessRecyclerViewAdapter extends RecyclerView.Adapter<FastAccessRecyclerViewAdapter.FastAccessHolder> {

    Context context;
    List<Doc> fastAcessDocs;

    public FastAccessRecyclerViewAdapter(Context context, List<Doc> fastAccessDocs) {
        this.context = context;
        this.fastAcessDocs = fastAccessDocs;
    }

    @NonNull
    @Override
    public FastAccessHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FastAccessHolder(LayoutInflater.from(context).inflate(R.layout.item_list_fast_access, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FastAccessHolder holder, int position) {
        holder.tvFileName.setText(fastAcessDocs.get(position).getTitle());
        holder.tvDate.setText(fastAcessDocs.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return fastAcessDocs.size();
    }

    class FastAccessHolder extends RecyclerView.ViewHolder {

        private TextView tvFileName, tvDate;

        public FastAccessHolder(View itemView) {
            super(itemView);
            tvFileName = itemView.findViewById(R.id.tv_card_file_name);
            tvDate = itemView.findViewById(R.id.tv_card_date);
        }


    }
}
