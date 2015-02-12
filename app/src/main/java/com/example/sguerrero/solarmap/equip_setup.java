package com.example.sguerrero.solarmap;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by sguerrero on 12/3/14.
 */
public class equip_setup extends DialogFragment implements AdapterView.OnItemSelectedListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.abc_activity_chooser_view_list_item, null));
        builder.setMessage(R.string.dialog_what_mine)
                .setPositiveButton(R.string.set, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Postitive button was pressed " + which + " ", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(),"Negative button was pressed "+which+" ",Toast.LENGTH_SHORT).show();

                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
