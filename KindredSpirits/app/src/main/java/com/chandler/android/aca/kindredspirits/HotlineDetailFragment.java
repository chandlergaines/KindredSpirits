package com.chandler.android.aca.kindredspirits;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HotlineDetailFragment extends Fragment{

    @BindView(R.id.fragTitle) TextView mTitle;
    @BindView(R.id.fragNumber) TextView mNumber;
    @BindView(R.id.fragDesc) TextView mDesc;
    @BindView(R.id.fragButton) Button mCall;

    private ArrayList<Hotline> mHotlines;
    private Hotline mCurrentHotline;
    Bundle mHotlineBundle;

    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mHotlineBundle = getActivity().getIntent().getExtras();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_layout, container, false);

        String title = mHotlineBundle.getString("title");
        String num = mHotlineBundle.getString("number");
        String desc = mHotlineBundle.getString("description");

        ButterKnife.bind(this, view);
        unbinder = ButterKnife.bind(this, view);

        mTitle.setText(title);
        mNumber.setText(num);
        mDesc.setText(desc);

        mCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "This will call the thing" , Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static HotlineDetailFragment newInstance (int position) {
        Bundle args = new Bundle();
        args.putInt("Position", position);

        HotlineDetailFragment frag = new HotlineDetailFragment();
        frag.setArguments(args);

        return frag;
    }



}
