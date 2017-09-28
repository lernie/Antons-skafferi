package se.miun.antonsskafferi;

/**
 * Created by My on 9/28/2017.
 */
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import se.miun.antonsskafferi.Courses.Courses;

import static se.miun.antonsskafferi.R.id.counter;
import static se.miun.antonsskafferi.R.layout.course_main;


public class CourseAdapter extends ArrayAdapter<Courses>{
    int count = 0;


        ArrayList<Courses> list = new ArrayList<Courses>();
        Context context;
        int layoutResourceld;


        public CourseAdapter(Context context, int layoutResourceld,
                                 ArrayList<Courses> list){
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
                        holder.quantity = (EditText) row.findViewById(R.id.counter);
                        row.setTag(holder);
                    }else{
                        holder = (UserHolder) row.getTag();
                    }

                    Courses courses = list.get(position);
                    holder.meal.setText(courses.getName());
                    holder.btnAdd.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
/*
                    Log.i("Course Added", "*****");
                    Toast.makeText(context, "Course Added",
                                    Toast.LENGTH_LONG).show();
*/
                    count++;
                    ((EditText) ((View) v.getParent()).findViewById(R.id.counter))
                            .setText(Integer.toString(count));
                }

            });

            return row;
        }

        static class UserHolder{

            EditText quantity;
            TextView meal;
            Button btnAdd;
        }


    }




