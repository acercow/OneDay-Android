package com.acercow.oneday.mockdata;

import android.content.Context;

import com.acercow.oneday.R;
import com.acercow.oneday.data.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaosen on 2018/2/11.
 */

public class MockNoteGenerator {

    public static List<Note> getMockNotes(Context context) {
    int num = 20;
    List<Note> notes = new ArrayList<>();
    String[] names = context.getResources().getStringArray(R.array.people_names);
    String[] titles = context.getResources().getStringArray(R.array.note_titles);
    String[] contents = context.getResources().getStringArray(R.array.note_contents);
    String[] dates = context.getResources().getStringArray(R.array.note_date);
    for (int i = 0; i < num; i++) {

    }
    return notes;
}
}
