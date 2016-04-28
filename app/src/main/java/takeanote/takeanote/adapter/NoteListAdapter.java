package takeanote.takeanote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import takeanote.takeanote.R;

/**
 * Created by linard_f on 4/27/16.
 */
public class NoteListAdapter extends BaseAdapter {

    private static class ViewHolder {
        TextView title;
    }

    private Context context;
    private LayoutInflater inflater;
    private List<String> items;

    public NoteListAdapter(Context context, List<String> items) {
        this.context = context;
        this.items = items;
        inflater = LayoutInflater.from(this.context);
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
            ImageView documentPicture = (ImageView) convertView.findViewById(R.id.document_picture);
            documentPicture.setAlpha(0.5f);
            ImageView pictureOption = (ImageView) convertView.findViewById(R.id.picture_option);
            pictureOption.setAlpha(0.5f);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String item = items.get(position);
        viewHolder.title.setText(item);
        return convertView;
    }
}