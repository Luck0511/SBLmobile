package com.itlvck.sblmobile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.itlvck.sblmobile.R;

public class ProfileFragment extends Fragment {
    //Variables

    public ProfileFragment(){
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        //Here it goes the layout of the fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    //Creating the logic for the login submit (not finished)
    private void loginSubmit(){}
}
