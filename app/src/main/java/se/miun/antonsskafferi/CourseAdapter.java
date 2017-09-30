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
import android.widget.EditText;
import android.widget.TextView;

import se.miun.antonsskafferi.Courses.CourseListItem;

public class CourseAdapter extends ArrayAdapter<CourseListItem>{

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
            UserHolder holder = null;
                    if (row == null) {
                        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                        row = inflater.inflate(layoutResourceld, parent, false);
                        holder = new UserHolder();
                        holder.meal = (TextView) row.findViewById(R.id.textView2);
                        holder.btnAdd = (Button) row.findViewById(R.id.ButtonAdd);
                        holder.btnSubb = (Button) row.findViewById(R.id.ButtonSubb);
                        holder.quantity = (TextView) row.findViewById(R.id.counter);
                        holder.btnAdd.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                CourseListItem item = list.get(position);
                                item.setCount(item.getCount() +1 );
                                ((TextView) ((View) v.getParent()).findViewById(R.id.counter))
                                        .setText(Integer.toString(item.getCount()));
                        }
                        });
                        holder.btnSubb.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                CourseListItem item = list.get(position);
                                if (item.getCount() > 0) {
                                    item.setCount(item.getCount() - 1);

                                    ((TextView) ((View) v.getParent()).findViewById(R.id.counter))
                                            .setText(Integer.toString(item.getCount()));
                                }
                            }
                        });
                        row.setTag(holder);

                    }else{
                        holder = (UserHolder) row.getTag();
                    }
            CourseListItem courses = list.get(position);
            holder.meal.setText(courses.getCourse().getName());



            return row;
        }

        static class UserHolder{

            TextView quantity;
            TextView meal;
            Button btnAdd;
            Button btnSubb;
        }


    }




