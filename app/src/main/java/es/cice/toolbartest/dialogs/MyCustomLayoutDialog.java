package es.cice.toolbartest.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

import es.cice.toolbartest.R;

/**
 * Created by cice on 25/1/17.
 */

public class MyCustomLayoutDialog extends DialogFragment {

    private CustomDialogInterface mDialogInterface;
    public static final String FABRICANTE_KEY = "fabricante";
    public static final String MODELO_KEY = "modelo";
    private EditText fabricanteET, modeloET;



    public interface CustomDialogInterface {
        public void setData(Map<String, String> data);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mDialogInterface = (CustomDialogInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("el contexto debe implementar el interfaz CustomlogInterface");
        }
    }

    @Override


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View layout=getActivity().getLayoutInflater().inflate(R.layout.add_layout,null);
        //atributos de la clase
        fabricanteET=(EditText) layout.findViewById(R.id.fabricanteET);
        modeloET=(EditText) layout.findViewById(R.id.modeloET);

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        builder.setMessage("Custom LayouDialog")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //EditText nombreET = (EditText) getView().findViewById(R.id.nombreET);
                        //EditText emailET = (EditText) getView().findViewById(R.id.emailET);
                        Map<String, String> data = new HashMap<>();
                        data.put(FABRICANTE_KEY, fabricanteET.getText().toString());
                        data.put(MODELO_KEY, modeloET.getText().toString());
                        mDialogInterface.setData(data);

                        //Toast.makeText(getActivity(), "OK", Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();

                    }
                })
                .setView(layout);
        return builder.create();

    }
}

