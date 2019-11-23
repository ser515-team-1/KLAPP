package com.asu.ser.klapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.asu.ser.klapp.interfaces.Dialogcallback;
import com.asu.ser.klapp.utilities.AppUtility;

import androidx.appcompat.app.AppCompatDialogFragment;

/**
 * @author          ashwath
 * @version         1.0 Validation rules
 * date created     11/02/2019
 *
 * @author          rsingh92
 * @version         2.0 YES/NO dialog creation
 */
public class ExampleDialog extends AppCompatDialogFragment {

    AlertDialog.Builder builder;
    private EditText editText;
    private Button submit;
    private Dialogcallback dialogcallback;

    public ExampleDialog(Dialogcallback dialogcallback){
        this.dialogcallback = dialogcallback;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.layout_dialog,null);
        editText = view.findViewById(R.id.editText);
        submit = view.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isvalidated = validate(editText.getText().toString());
                if(isvalidated){
                    showMessage("Passed");
                    dismiss();
                    dialogcallback.dialogResult(true);
                }else{
                    showMessage("Failed");
                }

            }
        });

        builder.setView(view)
                .setTitle("Enter Password")
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
