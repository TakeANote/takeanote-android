package takeanote.takeanote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import org.apache.commons.lang3.text.WordUtils;

import java.util.List;

import takeanote.takeanote.R;
import takeanote.takeanote.model.IItem;

/**
 * Created by linard_f on 4/27/16.
 */
public class NoteListAdapter extends BaseAdapter {


    public interface IProcessItemOptionSelection {
        void onItemOptionSelection(IItem item, String option);
        String[] getOptionList();
    }

    private static class ViewHolder {
        TextView title;
        ImageButton options;
    }

    private Context context;
    private LayoutInflater inflater;
    private List<IItem> items;
    private final IProcessItemOptionSelection callBack;
    private int imageRes;

    public NoteListAdapter(Context context, List<IItem> items, IProcessItemOptionSelection callBack, int imageRes) {
        this.context = context;
        this.items = items;
        this.callBack = callBack;
        inflater = LayoutInflater.from(this.context);
        this.imageRes = imageRes;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.note_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.options = (ImageButton) convertView.findViewById(R.id.picture_option);
            ImageView documentPicture = (ImageView) convertView.findViewById(R.id.document_picture);
            documentPicture.setAlpha(0.5f);
            documentPicture.setImageResource(imageRes);
            ImageView pictureOption = (ImageView) convertView.findViewById(R.id.picture_option);
            pictureOption.setAlpha(0.5f);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final IItem item = items.get(position);
        viewHolder.title.setText(WordUtils.capitalize(item.getName()));
        viewHolder.options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(context)
                        .autoDismiss(true)
                        .adapter(new ArrayAdapter(context, android.R.layout.simple_list_item_1, callBack.getOptionList()),
                                new MaterialDialog.ListCallback() {
                                    @Override
                                    public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                        callBack.onItemOptionSelection(item, text.toString());
                                        dialog.dismiss();
                                    }
                                })
                        .show();
            }
        });
        return convertView;
    }

    public void addItem(IItem document) {
        items.add(document);
        notifyDataSetChanged();
    }

    public void deleteItem(IItem item) {
        items.remove(item);
        notifyDataSetChanged();
    }

}