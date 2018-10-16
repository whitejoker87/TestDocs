package ru.orehovai.testdocs.view;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import ru.orehovai.testdocs.model.Doc;
import ru.orehovai.testdocs.contract.DocsListContract;
import ru.orehovai.testdocs.presenter.DocsListPresenterImpl;
import ru.orehovai.testdocs.model.interactor.GetDocsInteractorImpl;
import ru.orehovai.testdocs.R;


public class AllDocsListFragment extends Fragment implements DocsListContract.ItemListView {

    public static final String ARG_PAGE = "ARG_PAGE";

    private RecyclerView recyclerAllDocs;

    private List<Doc> allDocList;

    private int numOfTab;

    private DocsListContract.Presenter presenter;

    public static AllDocsListFragment newInstance(int page) {
        AllDocsListFragment fragment = new AllDocsListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            numOfTab = getArguments().getInt(ARG_PAGE);
        }
        presenter = new DocsListPresenterImpl(this, new GetDocsInteractorImpl());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_docs_list, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.onDestroy();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerAllDocs = Objects.requireNonNull(getActivity()).findViewById(R.id.recycler_docs);
        recyclerAllDocs.setLayoutManager(new LinearLayoutManager(getActivity()));

        presenter.requestDocsListFromServer();
    }

    /**
     * RecyclerItem click event listener
     * */
    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(Doc doc) {

            presenter.requestDocFromServer(doc.getId());

        }
    };

    @Override
    public void setDataToRecyclerView(List<Doc> allDocsList) {
        if (numOfTab == 2) {
            allDocList = allDocsList;
            presenter.requestFavDocsListFromServer();
            return;
        }

        AllDocsListAdapter adapter = new AllDocsListAdapter(allDocsList , Objects.requireNonNull(getActivity()).getFragmentManager(), recyclerItemClickListener);
        recyclerAllDocs.setAdapter(adapter);

    }

    @Override
    public void openDocInBrowser(Doc doc) {
        Uri url;
        String[] forOpenInBrowser = {"link", "html", "jpg", "png"};
        if(Arrays.asList(forOpenInBrowser).contains(doc.getType())) {
            url = Uri.parse(doc.getLink());
        } else url = Uri.parse("https://docs.google.com/viewerng/viewer?url=" + doc.getLink());
        Intent urlIntent = new Intent(Intent.ACTION_VIEW, url);
        // Проверка на споссобность обработать intent
        PackageManager packageManager = Objects.requireNonNull(getActivity()).getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(urlIntent, 0);
        boolean isIntentSafe = activities.size() > 0;
        if (isIntentSafe) {
            startActivity(urlIntent);
        }
    }

    @Override
    public void compareIdForFav(List<String> favDocsId) {
        List<Doc> favDocsList = new ArrayList<>();
        for (String favDocId: favDocsId){
            for (Doc doc:allDocList){
                if (favDocId.equals(doc.getId()))favDocsList.add(doc);
            }
        }
        numOfTab = 0;
        setDataToRecyclerView(allDocList);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }

}
