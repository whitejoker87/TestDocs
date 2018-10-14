package ru.orehovai.testdocs;

import android.annotation.SuppressLint;
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

    private RecyclerItemClickListener recyclerItemClickListener;

    public FastAccessRecyclerViewAdapter(Context context, List<Doc> fastAccessDocs, RecyclerItemClickListener recyclerItemClickListener) {
        this.context = context;
        this.fastAcessDocs = fastAccessDocs;
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    @NonNull
    @Override
    public FastAccessHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FastAccessHolder(LayoutInflater.from(context).inflate(R.layout.item_list_fast_access, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FastAccessHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tvFileName.setText(fastAcessDocs.get(position).getName());
        holder.tvDate.setText(fastAcessDocs.get(position).getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(fastAcessDocs.get(position));
            }
        });
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
