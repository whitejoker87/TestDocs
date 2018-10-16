package ru.orehovai.testdocs.contract;

import java.util.List;

import ru.orehovai.testdocs.model.Doc;

public interface DocsListContract extends BaseContract {

    /**
     * Call when user interact with the view and other when view OnDestroy()
     * */

    interface Presenter extends BaseContract.Presenter{

        void requestDocFromServer(String docId);

        void requestFavDocsListFromServer();

    }

    /**
     * showProgress() and hideProgress() would be used for displaying and hiding the progressBar
     * while the setDataToRecyclerView and onResponseFailure is fetched from the GetNoticeInteractorImpl class
     **/

    interface ItemListView {

        void setDataToRecyclerView(List<Doc> docsArrayList);

        void openDocInBrowser(Doc doc);

        void onResponseFailure(Throwable throwable);

        void compareIdForFav(List<String> favDocsId);

    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface GetDocInteractor extends BaseContract.GetDocInteractor {

        interface OnFinishedListener extends BaseContract.GetDocInteractor.OnFinishedListener {
            void onGetDocFinished(Doc doc);
            void onGetFavFinished(List<String> favDocList);
        }



        void getDoc(OnFinishedListener onFinishedListener, String docId);
        void getFavDocsList(OnFinishedListener onFinishedListener);

    }
}