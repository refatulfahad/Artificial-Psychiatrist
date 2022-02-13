package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import org.w3c.dom.Text;

public class dialog extends AppCompatDialogFragment {
    String ans;boolean ck;

    dialog(String str,boolean bl){
        ans=str;
        ck=bl;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Artificial Psychiatrist")
                .setMessage(ans)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(ck){
//                            Intent intent=new Intent(context,MainActivity2.class);
//                            startActivity(intent);
                            Intent intent=new Intent(getContext(),MainActivity2.class);
                            getContext().startActivity(intent);
                        }
                    }
                });

        return  builder.create();
    }
}
