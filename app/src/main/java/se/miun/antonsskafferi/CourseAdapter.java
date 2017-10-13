package se.miun.antonsskafferi;

/**
 * Created by My on 9/28/2017.
 */
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageButton;

public class CourseAdapter extends ArrayAdapter<CourseListItem> {

    private List<CourseListItem> list = new ArrayList<CourseListItem>();
    private Context context;

    public CourseAdapter(Context context, List<CourseListItem> list){
        super(context, R.layout.courses_list_item, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ListItem listItem = null;

        final CourseListItem item = list.get(position);

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.courses_list_item, parent, false);

            ImageButton addBtn = (ImageButton) row.findViewById(R.id.courses_item_add_button);
            ImageButton subBtn = (ImageButton) row.findViewById(R.id.courses_item_sub_button);

            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.setCount(item.getCount() + 1);
                    ((TextView) ((View) v.getParent()).findViewById(R.id.courses_item_quantity))
                            .setText(Integer.toString(item.getCount()));
                }
            });

            subBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item.getCount() > 0) {
                        item.setCount(item.getCount() - 1);

                        TextView countView = ((TextView) ((View) v.getParent())
                                .findViewById(R.id.courses_item_quantity));

                        if (item.getCount() == 0) {
                            countView.setText("");
                        } else {
                            countView.setText(Integer.toString(item.getCount()));
                        }
                    }
                }
            });

            listItem = new ListItem((TextView) row.findViewById(R.id.courses_item_quantity),
                    (TextView) row.findViewById(R.id.courses_item_course_name),
                    addBtn,
                    subBtn);

            row.setTag(item);
        } else {
            listItem = (ListItem) row.getTag();
        }

        CourseListItem courses = list.get(position);
        listItem.courseView.setText(courses.getCourse().getName());

        if (item.getCount() > 0) {
            ((TextView) row.findViewById(R.id.courses_item_quantity))
                    .setText(Integer.toString(item.getCount()));
        }

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




