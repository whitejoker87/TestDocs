package ru.orehovai.testdocs;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AllDocsListAdapter extends RecyclerView.Adapter<AllDocsListAdapter.DocHolder> {

    private List<Doc> allDocs;

    public AllDocsListAdapter(List<Doc> allDocs) {
        this.allDocs = allDocs;
    }

    @NonNull
    @Override
    public DocHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_all_docs_list, parent, false);
        return new DocHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocHolder holder, int position) {

        holder.tvTitle.setText(allDocs.get(position).getTitle());
        holder.tvDate.setText(allDocs.get(position).getDate());
        holder.tvSize.setText(allDocs.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return allDocs.size();
    }

    class DocHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvDate;
        TextView tvSize;

        public DocHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title_item_all_docs_list);
            tvDate = itemView.findViewById(R.id.tv_date_item_all_docs_list);
            tvSize = itemView.findViewById(R.id.tv_size_item_all_docs_list);
        }
    }

}
