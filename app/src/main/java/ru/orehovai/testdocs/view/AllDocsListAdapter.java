package ru.orehovai.testdocs.view;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import ru.orehovai.testdocs.model.Doc;
import ru.orehovai.testdocs.R;

public class AllDocsListAdapter extends RecyclerView.Adapter<AllDocsListAdapter.DocHolder> {

    private DocDialogFragment dialogFragment;
    private FragmentManager fragmentManager;

    private List<Doc> allDocs;
    private Context context;

    private RecyclerItemClickListener recyclerItemClickListener;

    public AllDocsListAdapter(List<Doc> allDocs, FragmentManager fragmentManager, RecyclerItemClickListener recyclerItemClickListener) {
        this.allDocs = allDocs;
        this.fragmentManager = fragmentManager;
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    @NonNull
    @Override
    public DocHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_all_docs_list, parent, false);
        return new DocHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocHolder holder, @SuppressLint("RecyclerView") final int position) {

        Doc doc = allDocs.get(position);
        String fullFileName = doc.getName() + "." + doc.getType();
        String size = "100 Гб";

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String date = sdf.format(Calendar.getInstance().getTime());

        holder.tvTitle.setText(fullFileName);
        holder.tvDate.setText(date);
        holder.tvSize.setText(size);
        final Button btnDetails = holder.btnDetails;
        holder.btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogFragment = new DocDialogFragment();
                dialogFragment.show(fragmentManager, "DocDialogFragment");

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(allDocs.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return allDocs.size();
    }

    class DocHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvDate;
        TextView tvSize;
        Button btnDetails;

        ImageView ivDescription;
        ImageView ivDate;
        TextView tvDot;

        public DocHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title_item_all_docs_list);
            tvDate = itemView.findViewById(R.id.tv_date_item_all_docs_list);
            tvSize = itemView.findViewById(R.id.tv_size_item_all_docs_list);
            btnDetails = itemView.findViewById(R.id.btn_details_item_all_docs_list);

            ivDescription = itemView.findViewById(R.id.iv_decription_item_all_docs_list);
            ivDate = itemView.findViewById(R.id.iv_date_item_all_docs_list);
            tvDot = itemView.findViewById(R.id.tv_dot_item_all_docs_list);



        }
    }

}