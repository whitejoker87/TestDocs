package ru.orehovai.testdocs.contract;

import java.util.List;

import ru.orehovai.testdocs.model.Doc;

public interface BaseContract {

    interface Presenter {

        void onDestroy();

        void requestDocsListFromServer();

    }

    interface GetDocInteractor {

        interface OnFinishedListener {
            void onGetAllFinished(List<Doc> allDocsList);
            void onFailure(Throwable t);
        }

        void getDocsArrayList(BaseContract.GetDocInteractor.OnFinishedListener onFinishedListener);

    }

}
