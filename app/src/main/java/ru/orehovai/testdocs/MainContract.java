package ru.orehovai.testdocs;

import java.util.List;

public interface MainContract {

    /**
     * Call when user interact with the view and other when view OnDestroy()
     * */
    interface Presenter {

        void onDestroy();

//        void onRefreshButtonClick();

        void requestDocsListFromServer();

        void requestDocFromServer(String docId);

        void requestFavDocsListFromServer();

    }

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

    interface ItemListView {

        void openDocInBrowser(Doc doc);

        void onResponseFailure(Throwable throwable);

        void compareIdForFav(List<String> favDocsId);

    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface GetDocInteractor {

        interface OnFinishedListener {
            void onGetAllFinished(List<Doc> allDocsList);
            void onGetDocFinished(Doc doc);
            void onGetFavFinished(List<String> favDocList);
            void onFailure(Throwable t);
        }

        void getDocsArrayList(OnFinishedListener onFinishedListener);
        void getDoc(OnFinishedListener onFinishedListener, String docId);
        void getFavDocsList(OnFinishedListener onFinishedListener);

    }
}