package ru.orehovai.testdocs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

public class DocDialogFragment extends DialogFragment {

    OpenDialogFragment openDialogFragment;

    public DocDialogFragment() {
        // Required empty public constructor
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_doc_dialog, container, false);
//    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_details, null))
                .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNeutralButton("Открыть с помощью", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openDialogFragment = new OpenDialogFragment();
                        openDialogFragment.show(getFragmentManager(), "openDialogFragment");
//                        PopupMenu popupMenu = new PopupMenu(context, btnDetails);
//                        popupMenu.inflate(R.menu.item_all_docs_details_menu);
//                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                            @Override
//                            public boolean onMenuItemClick(MenuItem item) {
//                                switch (item.getItemId()) {
//                                    case R.id.details_menu_item_1:
//                                        break;
//                                    case R.id.details_menu_item_2:
//                                        break;
//                                    case R.id.details_menu_item_3:
//                                        break;
//                                    default:
//                                        break;
//                                }
//                                return false;
//
//                            }
//                        });
//                        popupMenu.show();
                    }
                });


        return builder.create();
    }
}
