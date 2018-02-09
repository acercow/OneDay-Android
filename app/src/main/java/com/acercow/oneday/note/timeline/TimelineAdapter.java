package com.acercow.oneday.note.timeline;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.acercow.oneday.R;
import com.acercow.oneday.data.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaosen on 2018/2/9.
 */

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.NoteItemViewHolder> {
    private List<Note> mNotes = new ArrayList<>();
    private static final int VIEW_TYPE_TIME_TAG = 0;
    private static final int VIEW_TYPE_NOTE_ITEM = 1;

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private String mPreviousDate = "";

    public TimelineAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<Note> notes) {
        this.mNotes = notes;
    }

    public void removeItem(Note note) {
        mNotes.remove(note);
    }

    public void addItem(Note note) {
        mNotes.add(note);
    }


    @Override
    public NoteItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoteItemViewHolder(mLayoutInflater.inflate(R.layout.timeline_item_note, parent, false));
    }

    @Override
    public void onBindViewHolder(NoteItemViewHolder holder, int position) {
        Note note = mNotes.get(position);
        if (isTimeTagHidePosition(position)) {
            holder.ltTimeTag.setVisibility(View.GONE);
        } else {
            holder.ltTimeTag.setVisibility(View.VISIBLE);
            holder.tvTimeDay.setText(note.getDate().substring(8, 10));
        }
        mPreviousDate = note.getDate();
        holder.tvTitle.setText("Lorem Ipsum   " + note.getDate());
        holder.ltNoteItem.setOnClickListener(v -> {
            Toast.makeText(mContext, "[Click]: " + note.getTitle(), Toast.LENGTH_SHORT).show();
        });
    }

    private boolean isTimeTagHidePosition(int position) {
        if (position < 0) {
            throw new IllegalArgumentException("Arguments must be at least 0");
        }
        if (position == 0) {
            return false;
        } else {
            return mNotes.get(position - 1).getDate().equals(mNotes.get(position).getDate());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    static class NoteItemViewHolder extends RecyclerView.ViewHolder {
        private View ltTimeTag;
        private TextView tvTitle;
        private TextView tvTimeDay;
        private View ltNoteItem;
        public NoteItemViewHolder(View itemView) {
            super(itemView);
            ltTimeTag = itemView.findViewById(R.id.list_item_time_tag);
            tvTitle = itemView.findViewById(R.id.note_item_title);
            tvTimeDay = itemView.findViewById(R.id.timestamp_day);
            ltNoteItem = itemView.findViewById(R.id.list_item_note);
        }
    }

}
