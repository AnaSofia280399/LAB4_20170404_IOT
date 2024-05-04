package com.example.lab4_20170404;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.lab4_20170404.databinding.FragmentClimaBinding;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link climaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class climaFragment extends Fragment {

    FragmentClimaBinding climaBinding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public climaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment clima.
     */
    // TODO: Rename and change types and number of parameters
    public static climaFragment newInstance(String param1, String param2) {
        climaFragment fragment = new climaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        climaBinding= climaBinding.inflate(inflater, container, false);

        NavController navController = NavHostFragment.findNavController(climaFragment.this);


        climaBinding.geoDeClima.setOnClickListener(view -> {

            navController.navigate(R.id.action_nav_gallery_to_nav_home);
        });




        // Inflate the layout for this fragment
        return climaBinding.getRoot();
    }
}