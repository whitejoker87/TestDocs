package ru.orehovai.testdocs.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import ru.orehovai.testdocs.model.Doc;
import ru.orehovai.testdocs.R;
import ru.orehovai.testdocs.view.RecyclerItemClickListener;

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

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM HH:mm");
        String date = "Открыто " + sdf.format(Calendar.getInstance().getTime());

        holder.tvFileName.setText(fastAcessDocs.get(position).getName());
        holder.tvDate.setText(date);
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
