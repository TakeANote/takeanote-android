package takeanote.takeanote.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import takeanote.takeanote.R;
import takeanote.takeanote.activity.interaction.INoteInteraction;
import takeanote.takeanote.adapter.NoteListAdapter;
import takeanote.takeanote.model.Document;
import takeanote.takeanote.model.IItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoteListFragment extends Fragment implements NoteListAdapter.IProcessItemOptionSelection {

    private static final String TAG = "NOTE_LIST_FRAGMENT";
    @Bind(R.id.list)
    ListView mList;
    @Bind(R.id.note_list_layout)
    View mNotesLayout;

    protected Context mContext;
    protected List<IItem> mListItems;
    protected NoteListAdapter mAdapter;

    public NoteListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);
        ButterKnife.bind(this, view);

        mAdapter = new NoteListAdapter(mContext, getItemList(), this, getImageRes());
        mList.setAdapter(mAdapter);
        return view;
    }

    @OnItemClick(R.id.list)
    public void onItemClick(int position) {
        try {
            INoteInteraction noteInteraction = (INoteInteraction) mContext;
            noteInteraction.onNoteSelected((Document) mListItems.get(position));
        } catch (ClassCastException e) {
            throw new ClassCastException(mContext.toString() + " must implement INoteInteraction");
        }

    }

    @OnClick(R.id.fab)
    public void onClickFab() {
        new MaterialDialog.Builder(mContext)
                .title(R.string.dialog_create_document_title)
                .content(R.string.dialog_create_document_content)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input(R.string.dialog_create_document_content, R.string.empty, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        Document document = new Document(input.toString().toLowerCase().trim());
                        if (Document.find(Document.class, "name = ?", document.getName()).isEmpty()) {
                            document.save();
                            mAdapter.addItem(document);
                        } else {
                            Snackbar.make(mNotesLayout, "The document \"" +
                                    WordUtils.capitalize(document.getName()) +
                                    "\" already exists.", Snackbar.LENGTH_LONG).show();
                        }
                    }
                }).show();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    protected List<IItem> getItemList() {
        List<Document> list = Document.listAll(Document.class);
        mListItems = new ArrayList<>();
        for (Document doc : list) {
            mListItems.add(doc);
        }
        return mListItems;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemOptionSelection(final IItem item, String option) {
        final Document doc = (Document) item;
        if (mContext.getString(R.string.delete_note).equals(option)) {
            mAdapter.deleteItem(item);
            Document.delete(doc);
            Snackbar.make(mNotesLayout, WordUtils.capitalize(item.getName()) +
                    " deleted.", Snackbar.LENGTH_LONG).show();
        } else if (mContext.getString(R.string.rename_note).equals(option)) {
            new MaterialDialog.Builder(mContext)
                    .title(R.string.dialog_rename_document_title)
                    .content(item.getName())
                    .inputType(InputType.TYPE_CLASS_TEXT)
                    .input("", item.getName(), new MaterialDialog.InputCallback() {
                                @Override
                                public void onInput(MaterialDialog dialog, CharSequence input) {
                                    if (Document.find(Document.class, "name = ?", input.toString().toLowerCase().trim()).isEmpty()) {
                                        mAdapter.deleteItem(item);
                                        doc.setName(String.valueOf(input));
                                        doc.save();
                                        mAdapter.addItem(doc);
                                    } else {
                                        Snackbar.make(mNotesLayout, "The document \"" +
                                                WordUtils.capitalize(String.valueOf(input)) +
                                                "\" already exists.", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            }

                    ).show();
        }
    }

    @Override
    public String[] getOptionList() {
        return mContext.getResources().getStringArray(R.array.note_options);
    }

    protected int getImageRes() {
        return R.mipmap.ic_description_black_24dp;
    }
}
