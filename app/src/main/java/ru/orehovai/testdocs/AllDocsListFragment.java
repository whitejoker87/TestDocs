package ru.orehovai.testdocs;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



public class AllDocsListFragment extends Fragment implements MainContract.MainView {

    public static final String ARG_PAGE = "ARG_PAGE";

    private List<Doc> allDocs;
    private RecyclerView listAllDocs;

    private int mDoc;

    private MainContract.Presenter presenter;

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
            mDoc = getArguments().getInt(ARG_PAGE);
        }
        presenter = new MainPresenterImpl(this, new GetNoticeIntractorImpl());
//        presenter.requestDataFromServer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_docs_list, container, false);
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
    @Override
    public void onDetach() {
        super.onDetach();
        presenter.onDestroy();
        //mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //allDocs = ((MainActivity)getActivity()).getAllDocs();

        listAllDocs = Objects.requireNonNull(getActivity()).findViewById(R.id.recycler_docs);
        listAllDocs.setLayoutManager(new LinearLayoutManager(getActivity()));
        //listAllDocs.setAdapter(new AllDocsListAdapter(allDocs, getActivity().getFragmentManager(), recyclerItemClickListener));
        presenter.requestDataFromServer();

    }

    /**
     * RecyclerItem click event listener
     * */
    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(Doc doc) {

            Toast.makeText(getActivity(),
                    "List title:  " + doc.getName(),
                    Toast.LENGTH_LONG).show();

        }
    };

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setDataToRecyclerView(List<Doc> noticeArrayList) {

        AllDocsListAdapter adapter = new AllDocsListAdapter(noticeArrayList , getActivity().getFragmentManager(), recyclerItemClickListener);
        listAllDocs.setAdapter(adapter);

    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }

}
