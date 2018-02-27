package com.acercow.oneday.note.feed;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acercow.oneday.BaseFragment;
import com.acercow.oneday.R;
import com.acercow.oneday.data.Note;
import com.acercow.oneday.mockdata.MockNoteGenerator;

import java.util.Collections;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class FeedFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FeedFragment.OnFragmentInteractionListener mListener;
    private RecyclerView lvNote;

    public FeedFragment() {
    }


    public static FeedFragment newInstance(String param1, String param2)  {
        FeedFragment fragment = new FeedFragment();
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
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_diary_list;
    }

    @Override
    public void initView(View view) {
        lvNote = view.findViewById(R.id.list_note);
    }

    @Override
    public void doBusiness(Context mContext) {
//        List<Note> mockNotes = MockNoteGenerator.getMockNotes(mContext);
//        Collections.sort(mockNotes, (o1, o2) -> o1.getNoteDate().compareTo(o2.getNoteDate()));
//        FeedAdapter adapter = new FeedAdapter(getContext());
//        adapter.setData(mockNotes);
//        lvNote.setLayoutManager(new LinearLayoutManager(getContext()));
//        lvNote.setAdapter(adapter);
    }

    @Override
    public void widgetClick(View v) {

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FeedFragment.OnFragmentInteractionListener) {
            mListener = (FeedFragment.OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
