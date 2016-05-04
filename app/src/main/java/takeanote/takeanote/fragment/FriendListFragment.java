package takeanote.takeanote.fragment;


import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.InputType;

import com.afollestad.materialdialogs.MaterialDialog;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import takeanote.takeanote.R;
import takeanote.takeanote.model.Document;
import takeanote.takeanote.model.Friend;
import takeanote.takeanote.model.IItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendListFragment extends NoteListFragment {

    public FriendListFragment() {
        // Required empty public constructor
    }

    @Override
    protected List<IItem> getItemList() {
        List<Friend> list = Friend.listAll(Friend.class);
        mListItems = new ArrayList<>();
        for (Friend friend : list) {
            mListItems.add(friend);
        }
        return mListItems;
    }

    @OnClick(R.id.fab)
    public void onClickFab() {
        new MaterialDialog.Builder(mContext)
                .title(R.string.dialog_create_friend_title)
                .content(R.string.dialog_create_friend_content)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input(R.string.dialog_create_friend_content, R.string.empty, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        Friend friend = new Friend(input.toString().toLowerCase().trim());
                        if (Friend.find(Friend.class, "name = ?", friend.getName()).isEmpty()) {
                            friend.save();
                            mAdapter.addItem(friend);
                        } else {
                            Snackbar.make(mNotesLayout, WordUtils.capitalize(friend.getName()) +
                                    "\" already added.", Snackbar.LENGTH_LONG).show();
                        }
                    }
                }).show();
    }

    @Override
    protected int getImageRes() {
        return R.mipmap.ic_person_black_24dp;
    }
}
