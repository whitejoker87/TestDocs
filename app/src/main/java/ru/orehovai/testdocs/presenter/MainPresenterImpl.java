package ru.orehovai.testdocs.presenter;

import java.util.List;

import ru.orehovai.testdocs.contract.BaseContract;
import ru.orehovai.testdocs.model.Doc;
import ru.orehovai.testdocs.contract.MainContract;

public class MainPresenterImpl implements BaseContract.Presenter, BaseContract.GetDocInteractor.OnFinishedListener {

    private MainContract.MainView mainView;
    private MainContract.GetDocInteractor getDocInteractor;


    public MainPresenterImpl(MainContract.MainView mainView, MainContract.GetDocInteractor getDocInteractor) {
        this.mainView = mainView;
        this.getDocInteractor = getDocInteractor;
    }

    @Override
    public void onDestroy() {

        mainView = null;

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
    public void onGetAllFinished(List<Doc> allDocList) {
        if(mainView != null){
            mainView.setDataToRecyclerView(allDocList);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }
}
