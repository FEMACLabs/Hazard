package com.example.rezeq.hazard;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Rezeq on 3/8/2017.
 * E-mail : rezeq.elewa@gmail.com
 */

public class EmptyFragment  extends Fragment {

    public EmptyFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_empty, container, false);
    }

}