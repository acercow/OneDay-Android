package com.acercow.oneday.note.timeline;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acercow.oneday.BaseFragment;
import com.acercow.oneday.R;
import com.acercow.oneday.data.Note;
import com.acercow.oneday.note.edit.EditNoteActivity;
import com.acercow.oneday.note.preview.PreviewNoteActivity;
import com.acercow.oneday.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import static com.acercow.oneday.note.edit.EditNoteActivity.EXTRA_NOTE_ID;


public class TimelineFragment extends BaseFragment implements TimelineContract.View {
    private TimelineContract.Presenter mPresenter;
    private OnFragmentInteractionListener mListener;
    private RecyclerView lytNote;
    private TimelineAdapter mTimeLineAdapter;
    private ViewGroup lytEmptyView;
    private ImageView ivEmptyImage;
    private TextView tvEmptyDescription;

    public TimelineFragment() {
    }


    public static TimelineFragment newInstance() {
        return new TimelineFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_diary_time_line;
    }

    @Override
    public void initView(View view) {
        lytNote = view.findViewById(R.id.list_note);
        lytEmptyView = view.findViewById(R.id.note_list_empty_view);
        tvEmptyDescription = lytEmptyView.findViewById(R.id.note_list_empty_text);
        ivEmptyImage = lytEmptyView.findViewById(R.id.note_list_empty_img);
    }

    @Override
    public void doBusiness(Context mContext, Bundle savedInstanceState) {
//        List<Note> mockNotes = MockNoteGenerator.getMockNotes(mContext);
//        Collections.sort(mockNotes, (o1, o2) -> o1.getNoteDate().compareTo(o2.getNoteDate()));

        mPresenter = new TimeLinePresenter(this, mContext);

        mTimeLineAdapter = new TimelineAdapter(getContext());
        lytNote.setLayoutManager(new LinearLayoutManager(getContext()));
        lytNote.setAdapter(mTimeLineAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unSubscribe();
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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

    @Override
    public void setPresenter(TimelineContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showNotes(List<Note> notes) {
        lytEmptyView.setVisibility(View.GONE);
        lytNote.setVisibility(View.VISIBLE);
        mTimeLineAdapter.setData(notes);
        mTimeLineAdapter.notifyDataSetChanged();
}

    @Override
    public void showEmptyView() {
        lytEmptyView.setVisibility(View.VISIBLE);
        lytNote.setVisibility(View.GONE);
    }

    @Override
    public void toEditNoteActivity(String noteId) {
        ActivityUtils.startActivity(getActivity(), EditNoteActivity.class);
    }

    @Override
    public void toPreviewNoteActivity(String noteId) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_NOTE_ID, noteId);
        ActivityUtils.startActivity(getActivity(), PreviewNoteActivity.class, bundle);
    }

    @Override
    public void showNoteDeleted() {

    }

    @Override
    public void showNoteSaved() {

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.NoteItemViewHolder> {
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
                holder.timelineNode.setVisibility(View.GONE);
            } else {
                holder.ltTimeTag.setVisibility(View.VISIBLE);
                holder.timelineNode.setVisibility(View.VISIBLE);
                holder.tvTimestampDay.setText(note.getNoteDate().substring(8, 10));
                holder.tvTimestampMonth.setText(note.getNoteDate().substring(5, 7));
                holder.tvTimestampYear.setText(note.getNoteDate().substring(0, 4));
            }
            mPreviousDate = note.getNoteDate();
            holder.tvTitle.setText(note.getNoteTitle());
            holder.tvContent.setText(note.getNoteContent());
//        holder.ltNoteItem.setOnClickListener(v -> Toast.makeText(mContext, "[Click]: " + note.getTitle(), Toast.LENGTH_SHORT).show());

            holder.ltNoteItem.setOnClickListener(v -> {
                mPresenter.previewNote(note.getNoteGUID());
            });
        }

        private boolean isTimeTagHidePosition(int position) {
            if (position < 0) {
                throw new IllegalArgumentException("Arguments must be at least 0");
            }
            if (position == 0) {
                return false;
            } else {
                return mNotes.get(position - 1).getNoteDate().substring(0, 10).equals(mNotes.get(position).getNoteDate().substring(0, 10));
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

        class NoteItemViewHolder extends RecyclerView.ViewHolder {
            private View ltTimeTag;
            private TextView tvTitle;
            private TextView tvContent;
            private TextView tvTimestampDay;
            private TextView tvTimestampMonth;
            private TextView tvTimestampYear;
            private View ltNoteItem;
            private View timelineNode;

            public NoteItemViewHolder(View itemView) {
                super(itemView);
                ltTimeTag = itemView.findViewById(R.id.list_item_time_tag);
                tvTitle = itemView.findViewById(R.id.note_item_title);
                tvContent = itemView.findViewById(R.id.note_item_content);
                tvTimestampDay = itemView.findViewById(R.id.timestamp_day);
                tvTimestampMonth = itemView.findViewById(R.id.timestamp_month);
                tvTimestampYear = itemView.findViewById(R.id.timestamp_year);
                ltNoteItem = itemView.findViewById(R.id.list_item_note);
                timelineNode = itemView.findViewById(R.id.timeline_node);
            }
        }

    }

}
