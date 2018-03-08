package com.acercow.oneday.dialog;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.acercow.oneday.R;
import com.acercow.oneday.constant.DeviceType;
import com.acercow.oneday.data.Note;

/**
 * Created by zhaosen on 2018/3/6.
 */

public class NoteInfoDialog extends AppCompatDialogFragment {
    public static String ARG_NOTE = "arg_note";
    private Note mNote;


    public NoteInfoDialog() {

    }

    public static NoteInfoDialog newInstance(@NonNull Note note) {
        NoteInfoDialog fragment = new NoteInfoDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_NOTE, note);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mNote = (Note) bundle.getSerializable(ARG_NOTE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_note_info, container, false);
        TextView tvDay = view.findViewById(R.id.note_info_dialog_day);
        TextView tvMonth = view.findViewById(R.id.note_info_dialog_month);
        TextView tvYear = view.findViewById(R.id.note_info_dialog_year);
        TextView tvWeek = view.findViewById(R.id.note_info_dialog_week);
//        TextView tvTime = view.findViewById(R.id.note_info_dialog_time);
        TextView tvCharacters = view.findViewById(R.id.note_info_dialog_characters);
        TextView tvReadCount = view.findViewById(R.id.note_info_dialog_read_count);
        TextView tvCreateAt = view.findViewById(R.id.note_info_dialog_create_at);
        TextView tvModifiedAt = view.findViewById(R.id.note_info_dialog_modified_at);
        TextView tvEditedDeviceName = view.findViewById(R.id.note_info_dialog_edited_device_name);
        ImageView ivEditedDeviceIcon = view.findViewById(R.id.note_info_dialog_edited_device_icon);

        view.findViewById(R.id.note_info_dialog_close).setOnClickListener(v -> NoteInfoDialog.this.dismiss());


        tvDay.setText(mNote.getNoteDate().substring(8, 10));
        tvMonth.setText(mNote.getNoteDate().substring(5, 7));
        tvYear.setText(mNote.getNoteDate().substring(0, 4));

        tvCharacters.setText(String.valueOf(mNote.getNoteContent().length()));
        tvReadCount.setText(String.valueOf(mNote.getNoteReadCount()));
        tvCreateAt.setText(mNote.getCreatedDate());
        tvModifiedAt.setText(mNote.getModifiedDate());
        tvEditedDeviceName.setText(mNote.getLastEditDeviceName());
        ivEditedDeviceIcon.setImageResource(getDeviceIconResouce(mNote.getLastEditDeviceType()));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout((int) getResources().getDimension(R.dimen.note_info_dialog_width), WindowManager.LayoutParams.WRAP_CONTENT);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    private int getDeviceIconResouce(int type) {
        int resourceId = R.drawable.ic_phonelink_black_48dp; // unknown device
        if (type == DeviceType.NOTEBOOK) {
            resourceId = R.drawable.ic_computer_black_48dp;
        } else if (type == DeviceType.ANDROID_PHONE) {
            resourceId = R.drawable.ic_phone_android_black_48dp;
        } else if (type == DeviceType.IPHONE) {
            resourceId = R.drawable.ic_phone_iphone_black_48dp;
        }
        return resourceId;
    }
}
