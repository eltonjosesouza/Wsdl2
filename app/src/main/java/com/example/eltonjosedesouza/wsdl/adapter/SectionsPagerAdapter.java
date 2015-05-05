package com.example.eltonjosedesouza.wsdl.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.eltonjosedesouza.wsdl.R;
import com.example.eltonjosedesouza.wsdl.fragment.CepFragment;
import com.example.eltonjosedesouza.wsdl.fragment.LogradouroFragment;
import com.example.eltonjosedesouza.wsdl.fragment.ResultadoFragment;

import java.util.Locale;

/**
 * Created by eltonjosedesouza on 28/04/15.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    CepFragment cepFragment;
    LogradouroFragment logradouroFragment;
    ResultadoFragment resultadoFragment;
    Context context;

    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (cepFragment == null) {
                    cepFragment = new CepFragment();
                }
                return cepFragment;
            case 1:
                if (logradouroFragment == null) {
                    logradouroFragment = new LogradouroFragment();
                }
                return logradouroFragment;
            case 2:
                if (resultadoFragment == null) {
                    resultadoFragment = new ResultadoFragment();
                }
                return resultadoFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return context.getString(R.string.title_section1).toUpperCase(l);
            case 1:
                return context.getString(R.string.title_section2).toUpperCase(l);
            case 2:
                return context.getString(R.string.title_section3).toUpperCase(l);
        }
        return null;
    }
}
