package ru.orehovai.testdocs;

import java.util.List;

public class MainPresenterImpl implements MainContract.Presenter, MainContract.GetDocInteractor.OnFinishedListener {

    private MainContract.MainView mainView;
    private MainContract.ItemListView itemListView;
    private MainContract.GetDocInteractor getDocInteractor;

    public MainPresenterImpl(MainContract.MainView mainView, MainContract.ItemListView itemListView, MainContract.GetDocInteractor getDocInteractor) {
        this.mainView = mainView;
        this.getDocInteractor = getDocInteractor;
        this.itemListView = itemListView;
    }

    public MainPresenterImpl(MainContract.MainView mainView, MainContract.GetDocInteractor getDocInteractor) {
        this.mainView = mainView;
        this.getDocInteractor = getDocInteractor;
    }

    @Override
    public void onDestroy() {

        mainView = null;
        itemListView = null;

    }

//    @Override
//    public void onRefreshButtonClick() {
//
//        if(mainView != null){
//            mainView.showProgress();
//        }
//        getDocInteractor.getDocsArrayList(this);
//
//    }

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
        if(mainView != null){
            mainView.setDataToRecyclerView(allDocList);
            mainView.hideProgress();
        }
    }

    @Override
    public void onGetDocFinished(Doc doc) {
        if(itemListView != null){
            itemListView.openDocInBrowser(doc);
        }
    }

    @Override
    public void onGetFavFinished(List<String> favDocList) {
        if(itemListView != null){
            itemListView.compareIdForFav(favDocList);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
        if (itemListView != null) {
            itemListView.onResponseFailure(t);
        }
    }
}
