package ru.orehovai.testdocs.presenter;

import java.util.List;

import ru.orehovai.testdocs.model.Doc;
import ru.orehovai.testdocs.contract.DocsListContract;

public class DocsListPresenterImpl implements DocsListContract.Presenter, DocsListContract.GetDocInteractor.OnFinishedListener {

    private DocsListContract.docsListView docsListView;
    private DocsListContract.GetDocInteractor getDocInteractor;

    public DocsListPresenterImpl(DocsListContract.docsListView docsListView, DocsListContract.GetDocInteractor getDocInteractor) {
        this.getDocInteractor = getDocInteractor;
        this.docsListView = docsListView;
    }


    @Override
    public void onDestroy() {
        docsListView = null;
    }

    @Override
    public void requestDocsListFromServer() {
        getDocInteractor.getDocsArrayList(this);
    }

    @Override
    public void requestDocFromServer(String docId) {
        getDocInteractor.getDoc(this, docId);
    }

    @Override
    public void requestFavDocsListFromServer() {
        getDocInteractor.getFavDocsList(this);
    }


    @Override
    public void onGetAllFinished(List<Doc> allDocList) {
        if(docsListView != null){
            docsListView.saveDocsListToFragment(allDocList);
        }
    }

    @Override
    public void onGetDocFinished(Doc doc) {
        if(docsListView != null){
            docsListView.openDocInBrowser(doc);
        }
    }

    @Override
    public void onGetFavFinished(List<String> favDocList) {
        if(docsListView != null){
            docsListView.compareIdForFav(favDocList);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (docsListView != null) {
            docsListView.onResponseFailure(t);
        }
    }
}
