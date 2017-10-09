package se.miun.antonsskafferi;

/**
 * Created by My on 9/28/2017.
 */
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageButton;

public class CourseAdapter extends ArrayAdapter<CourseListItem> {

    ArrayList<CourseListItem> list = new ArrayList<CourseListItem>();
    Context context;
    int layoutResourceld;

    public CourseAdapter(Context context, int layoutResourceld,
                             ArrayList<CourseListItem> list){
        super(context, layoutResourceld, list);
        this.layoutResourceld = layoutResourceld;
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ListItem item = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceld, parent, false);
            ImageButton addBtn = (ImageButton) row.findViewById(R.id.courses_item_add_button);
            ImageButton subBtn = (ImageButton) row.findViewById(R.id.courses_item_sub_button);

            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CourseListItem item = list.get(position);
                    item.setCount(item.getCount() + 1);

                    ((TextView) ((View) v.getParent()).findViewById(R.id.courses_item_quantity))
                            .setText(Integer.toString(item.getCount()));
                }
            });

            subBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CourseListItem item = list.get(position);

                    if (item.getCount() > 0) {
                        item.setCount(item.getCount() - 1);

                        if (item.getCount() == 0) {
                            ((TextView) ((View) v.getParent()).findViewById(R.id.courses_item_quantity))
                                    .setText("");
                        } else {
                            ((TextView) ((View) v.getParent()).findViewById(R.id.courses_item_quantity))
                                    .setText(Integer.toString(item.getCount()));
                        }
                    }
                }
            });

            item = new ListItem((TextView) row.findViewById(R.id.courses_item_quantity),
                    (TextView) row.findViewById(R.id.courses_item_course_name),
                    addBtn,
                    subBtn);

            row.setTag(item);

        } else {
            item = (ListItem) row.getTag();
        }

        CourseListItem courses = list.get(position);
        item.courseView.setText(courses.getCourse().getName());

        return row;
    }

    private static class ListItem {
        private TextView quantityView;
        private TextView courseView;
        private ImageButton addBtn;
        private ImageButton subBtn;

        public ListItem(TextView quantityView, TextView courseView, ImageButton addBtn, ImageButton subBtn) {
            this.quantityView = quantityView;
            this.courseView = courseView;
            this.addBtn = addBtn;
            this.subBtn = subBtn;
        }
    }
}




