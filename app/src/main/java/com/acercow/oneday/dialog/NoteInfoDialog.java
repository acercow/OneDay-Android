package com.acercow.oneday.dialog;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acercow.oneday.R;

/**
 * Created by zhaosen on 2018/3/6.
 */

public class NoteInfoDialog extends AppCompatDialogFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflater.inflate(R.layout.dialog_note_info, container);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
