package com.asu.ser.klapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.asu.ser.klapp.utilities.AppUtility;

import androidx.appcompat.app.AppCompatDialogFragment;
/**
 * @author Rohit Singh(rsingh92)
 * @version 1.0
 *
 */
public class ExampleDialog extends AppCompatDialogFragment {

    AlertDialog.Builder builder;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.layout_dialog,null);

        final EditText editText = (EditText)view.findViewById(R.id.editText);


        builder.setView(view)
                .setTitle("Enter password")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                         boolean isvalidated = validate(editText.getText().toString());
                         if(isvalidated){
                             showMessage("Correct password");
                         }else {
                             showMessage("Incorrect password");
                         }
                    }
                });
        return builder.create();
    }

    private boolean validate(String password){

        String savedPass = AppUtility.getCredential().password;
        return savedPass.equals(password);

    }

    private void showMessage(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
