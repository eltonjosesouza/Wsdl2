package com.example.eltonjosedesouza.wsdl.service;


import android.os.AsyncTask;

import com.example.eltonjosedesouza.wsdl.handler.ICepHandler;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Vector;

public class CEPService {

    public String url = "http://www.byjg.com.br/site/webservice.php/ws/cep";
    public int timeOut = 60000;
    public ICepHandler cepHandler;
    public String usuario = "eltonjose";
    public String senha = "123456";

    public CEPService(ICepHandler cepHandler) {
        this.cepHandler = cepHandler;
    }

    public void obterLogradouroAuthAsync(final String cep) throws Exception {

        new AsyncTask<Void, Void, Vector<String>>() {
            @Override
            protected void onPreExecute() {
                cepHandler.RequisicaoIniciada();
            }

            @Override
            protected Vector<String> doInBackground(Void... params) {
                return obterLogradouroAuth(cep);
            }

            @Override
            protected void onPostExecute(Vector<String> result) {
                cepHandler.RequisicaoFechada();
                if (result != null) {
                    cepHandler.RequisicaoFinalizada("obterLogradouroAuth", result);
                }
            }
        }.execute();
    }

    private Vector<String> obterLogradouroAuth(String cep) {
        Vector<String> vector = new Vector<String>();
        SoapSerializationEnvelope soapEnvelope =
                new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject("urn:CEPService", "obterLogradouroAuth");
        soapReq.addProperty("cep", cep);
        soapReq.addProperty("usuario", usuario);
        soapReq.addProperty("senha", senha);
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url, timeOut);
        try {
            httpTransport.call("urn:CEPService/obterLogradouroAuth", soapEnvelope);

            Object retObj = soapEnvelope.bodyIn;
            if (retObj instanceof SoapFault) {
                SoapFault fault = (SoapFault) retObj;
                Exception ex = new Exception(fault.faultstring);
                if (cepHandler != null)
                    cepHandler.RequisicaoFinalizadaComErro(ex);
            } else {
                SoapObject result = (SoapObject) retObj;
                if (result.getPropertyCount() > 0) {
                    Object obj = result.getProperty(0);
                    if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                        SoapPrimitive j = (SoapPrimitive) obj;
                        String resultVariable = j.toString();
                        vector.add(resultVariable);
                        return vector;
                    } else if (obj != null && obj instanceof String) {
                        String resultVariable = (String) obj;
                        vector.add(resultVariable);
                        return vector;
                    }
                }
            }
        } catch (Exception e) {
            if (cepHandler != null)
                cepHandler.RequisicaoFinalizadaComErro(e);
            e.printStackTrace();
        }
        vector.add("");
        return vector;
    }

    public void obterCEPAuthAsync(final String logradouro, final String localidade, final String uF)
            throws Exception {

        new AsyncTask<Void, Void, Vector<String>>() {
            @Override
            protected void onPreExecute() {
                cepHandler.RequisicaoIniciada();
            }

            @Override
            protected Vector<String> doInBackground(Void... params) {
                return obterCEPAuth(logradouro, localidade, uF);
            }

            @Override
            protected void onPostExecute(Vector<String> result) {
                cepHandler.RequisicaoFechada();
                if (result != null) {
                    cepHandler.RequisicaoFinalizada("obterCEPAuth", result);
                }
            }
        }.execute();
    }


    private Vector<String> obterCEPAuth(String logradouro, String localidade, String uF) {
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject("urn:CEPService", "obterCEPAuth");
        soapReq.addProperty("logradouro", logradouro);
        soapReq.addProperty("localidade", localidade);
        soapReq.addProperty("UF", uF);
        soapReq.addProperty("usuario", usuario);
        soapReq.addProperty("senha", senha);
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url, timeOut);
        try {
            httpTransport.call("urn:CEPService/obterCEPAuth", soapEnvelope);

            Object retObj = soapEnvelope.bodyIn;
            if (retObj instanceof SoapFault) {
                SoapFault fault = (SoapFault) retObj;
                Exception ex = new Exception(fault.faultstring);
                if (cepHandler != null)
                    cepHandler.RequisicaoFinalizadaComErro(ex);
            } else {
                SoapObject result = (SoapObject) retObj;
                if (result.getPropertyCount() > 0) {
                    Vector<String> obj = (Vector<String>) result.getProperty(0);
//                    VectorString resultVariable = new VectorString();
                    return obj;
                }
            }
        } catch (Exception e) {
            if (cepHandler != null)
                cepHandler.RequisicaoFinalizadaComErro(e);
            e.printStackTrace();
        }
        return null;
    }

}
