package takeanote.takeanote.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import takeanote.takeanote.R;
import takeanote.takeanote.adapter.NoteListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoteListFragment extends Fragment {

    @Bind(R.id.list)
    ListView mList;

    private Context mContext;

    public NoteListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);
        ButterKnife.bind(this, view);

        NoteListAdapter adapter = new NoteListAdapter(mContext, getItemList());
        mList.setAdapter(adapter);
        return view;
    }

    private List<String> getItemList() {
        List<String> list = new ArrayList<>();
        list.add("Document one");
        list.add("Second document");
        list.add("My document");
        return list;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

}
