package ru.orehovai.testdocs.contract;

import java.util.List;

import ru.orehovai.testdocs.model.Doc;

public interface MainContract extends BaseContract {

//    /**
//     * Call when user interact with the view and other when view OnDestroy()
//     * */
//    interface Presenter {
//
//        void onDestroy();
//
////        void onRefreshButtonClick();
//
//        void requestDocsListFromServer();
//
//    }

    /**
     * showProgress() and hideProgress() would be used for displaying and hiding the progressBar
     * while the setDataToRecyclerView and onResponseFailure is fetched from the GetNoticeInteractorImpl class
     **/
    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<Doc> docsArrayList);

        void onResponseFailure(Throwable throwable);

    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
//    @Override
//    interface GetDocInteractor {
//
//        interface OnFinishedListener {
//            void onGetAllFinished(List<Doc> allDocsList);
//            void onFailure(Throwable t);
//        }
//
//        void getMainDocsArrayList(OnMainFinishedListener onFinishedListener);
//
//    }
}