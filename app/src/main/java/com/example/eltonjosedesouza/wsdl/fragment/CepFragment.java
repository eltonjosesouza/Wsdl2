package com.example.eltonjosedesouza.wsdl.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.eltonjosedesouza.wsdl.activity.BuscaCepLogradouro;
import com.example.eltonjosedesouza.wsdl.service.CEPService;
import com.example.eltonjosedesouza.wsdl.handler.ICepHandler;
import com.example.eltonjosedesouza.wsdl.R;

import java.util.Vector;

/**
 * Created by eltonjosedesouza on 28/04/15.
 */
public class CepFragment extends Fragment {

    public EditText cep;
    public Button buscar;
    public ICepHandler cepHandler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_busca_cep, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cep = (EditText) view.findViewById(R.id.cep);
        buscar = (Button) view.findViewById(R.id.buscar_logradouro);

        cepHandler = new ICepHandler() {
            @Override
            public void RequisicaoIniciada() {
                Log.e("Wsdl2Code", "RequisicaoIniciada");
            }

            @Override
            public void RequisicaoFinalizada(String methodName, Vector<String> data) {
                Log.e("Wsdl2Code", "RequisicaoFinalizada");
                Log.e("Wsdl2Code", methodName);
                Log.e("Wsdl2Code", data.toString());
                BuscaCepLogradouro.mViewPager.setCurrentItem(2);
                ResultadoFragment.addResposta(data.toString());

            }

            @Override
            public void RequisicaoFinalizadaComErro(Exception ex) {
                Log.e("Wsdl2Code", ex.getMessage());
                ex.printStackTrace();
            }

            @Override
            public void RequisicaoFechada() {
                Log.e("Wsdl2Code", "RequisicaoFechada");
            }

        };


        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CEPService cepService = new CEPService(cepHandler);
                    cepService.obterLogradouroAuthAsync(cep.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
