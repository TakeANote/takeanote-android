package takeanote.takeanote.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.richeditor.RichEditor;
import takeanote.takeanote.R;

public class DocActivity extends AppCompatActivity {

    private static final int COLOR = 1;
    private static final String TAG = "DOC_ACTIVITY";
    private int mColor = 0x000000;
    @Bind(R.id.editor)
    RichEditor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Toolbar toolbarBottom = (Toolbar) findViewById(R.id.toolbar_bottom);
        toolbarBottom.setOnMenuItemClickListener(mToolbarClickListener);
        toolbarBottom.inflateMenu(R.menu.activity_doc_bottom);

        mEditor.focusEditor();
        mEditor.setTextColor(mColor);
        mThis = this;
        Log.v(TAG, String.valueOf(mEditor.getHeight()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.action_undo)
    void undo() {
        mEditor.undo();
    }

    @OnClick(R.id.action_redo)
    void redo() {
        mEditor.redo();
    }

    private Context mThis;
    private Toolbar.OnMenuItemClickListener mToolbarClickListener = new Toolbar.OnMenuItemClickListener() {

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.action_bold) {
                mEditor.setBold();
            } else if (id == R.id.action_italic) {
                mEditor.setItalic();
            } else if (id == R.id.action_underlined) {
                mEditor.setUnderline();
            } else if (id == R.id.action_align_left) {
                mEditor.setAlignLeft();
            } else if (id == R.id.action_align_center) {
                mEditor.setAlignCenter();
            } else if (id == R.id.action_align_right) {
                mEditor.setAlignRight();
            } else if (id == R.id.action_color_text) {
                startActivityForResult(new Intent(mThis, ColorPickerActivity.class), COLOR);
            }
            return true;
        }

    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Bundle b = data.getExtras();
            mColor = b.getInt("color");
            mEditor.focusEditor();
            mEditor.setTextColor(mColor);
            mThis = this;
        }
    }
}
