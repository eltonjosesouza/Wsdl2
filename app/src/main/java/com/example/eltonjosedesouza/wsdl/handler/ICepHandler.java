package com.example.eltonjosedesouza.wsdl.handler;


import java.util.Vector;

public interface ICepHandler {
    public void RequisicaoIniciada();
    public void RequisicaoFinalizada(String methodName, Vector<String> Data);
    public void RequisicaoFinalizadaComErro(Exception ex);
    public void RequisicaoFechada();
}
