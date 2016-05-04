package takeanote.takeanote.fragment;


import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import takeanote.takeanote.model.IItem;
import takeanote.takeanote.model.SharedDocument;

/**
 * A simple {@link Fragment} subclass.
 */
public class SharedNoteListFragment extends NoteListFragment {

    public SharedNoteListFragment() {
        // Required empty public constructor
    }

    @Override
    protected List<IItem> getItemList() {
        List<SharedDocument> list = SharedDocument.listAll(SharedDocument.class);
        mListItems = new ArrayList<>();
        for (SharedDocument sharedDocument : list) {
            mListItems.add(sharedDocument);
        }
        return mListItems;
    }
}
