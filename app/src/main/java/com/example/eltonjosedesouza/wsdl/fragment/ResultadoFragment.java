package com.example.eltonjosedesouza.wsdl.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eltonjosedesouza.wsdl.R;

/**
 * Created by eltonjosedesouza on 28/04/15.
 */
public class ResultadoFragment extends Fragment {

    private static String resposta = "";
    private static TextView respostaTV;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_resultado, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        respostaTV = (TextView) view.findViewById(R.id.resposta);
    }

    @Override
    public void onResume() {
        super.onResume();
        respostaTV.setText(resposta);
    }

    public static void addResposta(String respostaAux) {
        resposta = respostaAux;
        respostaTV.setText(resposta);

    }


}
