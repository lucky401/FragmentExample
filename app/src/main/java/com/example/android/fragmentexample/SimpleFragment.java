package com.example.android.fragmentexample;

import android.content.Context;
import android.media.MediaCodec;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SimpleFragment extends Fragment {

    // The radio button choice has 3 states: 0 = yes, 1 = no,
    // 2 = default (no choice). Using only 0 and 1.
    private static final int YES = 0;
    private static final int NO = 1;
    private int mRadioButtonChoice=2;
    private OnFragmentInteractionListener mListener;

    public SimpleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment.
        final View rootView = inflater.inflate(R.layout.fragment_simple,
                container, false);
        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);

        // Set the radioGroup onCheckedChanged listener.
        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        View radioButton = radioGroup.findViewById(checkedId);
                        int index = radioGroup.indexOfChild(radioButton);
                        TextView textView =
                                rootView.findViewById(R.id.fragment_header);
                        switch (index) {
                            case YES: // User chose "Yes".
                                textView.setText(R.string.yes_message);
                                break;
                            case NO: // User chose "No".
                                textView.setText(R.string.no_message);
                                break;
                            default: // No choice made.
                                // Do nothing.
                                break;
                        }
                        mListener.onRadioButtonChoice(mRadioButtonChoice);
                    }
                });

        // Return the View for the fragment's UI.
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener){
            mListener =(OnFragmentInteractionListener) context;
        }
    }

    public static SimpleFragment newInstance(int mRadioButtonChoice) {
        return new SimpleFragment();
    }

    public interface OnFragmentInteractionListener{
        void onRadioButtonChoice(int choice);
    }
}