package com.bawei.newproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentTwo extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag, container, false);
        TextView text = view.findViewById(R.id.frag_text);

        Bundle arguments = getArguments();
        String name = arguments.getString("hh");
        text.setText(name);

        return view;
    }
    public void setValues(String name){
        Bundle bundle=new Bundle();
        bundle.putString("hh",name);
        setArguments(bundle);

    }
}
