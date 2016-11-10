package com.chandler.android.aca.kindredspirits;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HotlineDetailFragment extends Fragment {

    @BindView(R.id.fragTitle) TextView mTitle;
    @BindView(R.id.fragNumber) TextView mNumber;
    @BindView(R.id.fragDesc) TextView mDesc;
    @BindView(R.id.fragButton) Button mCall;
    @BindView(R.id.fragDismiss) Button mDismiss;

    String title;
    String num;
    String desc;

    private ArrayList<Hotline> mHotlines;
    private Hotline mCurrentHotline;
    HotlineActivity mHotlineActivity;
    Bundle mHotlineBundle;

    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.hotline_fragment_layout, container, false);

        mHotlineBundle = getArguments();

        title = mHotlineBundle.getString("title");
        num = mHotlineBundle.getString("number");
        desc = mHotlineBundle.getString("description");

        ButterKnife.bind(this, view);
        unbinder = ButterKnife.bind(this, view);

        mTitle.setText(title);
        mNumber.setText(num);
        mDesc.setText(desc);

        mCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent action = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + num));
                startActivity(action);
                Log.v("Dialer value: ", num);
            }
        });

        mDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getFragmentManager().beginTransaction().remove(HotlineDetailFragment.this).commit();
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
