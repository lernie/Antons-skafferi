package se.miun.antonsskafferi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Wictor on 2017-10-15.
 */

public class ScheduleListAdapter extends BaseAdapter {
    private Context context;
    private List<ScheduledEvents> scheduledEvents;
    private LayoutInflater inflater;
    public ScheduleListAdapter(Context context, List<ScheduledEvents> scheduledEvents){
        this.context = context;
        this.scheduledEvents = scheduledEvents;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return scheduledEvents.size();
    }

    @Override
    public Object getItem(int i) {
        return scheduledEvents.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EventHolder eventHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.schedule_list_item, parent, false);
            eventHolder = new EventHolder(convertView);
            convertView.setTag(eventHolder);
        } else {
            eventHolder = (EventHolder) convertView.getTag();
        }
        ScheduledEvents scheduledEvents = (ScheduledEvents) getItem(position);
        eventHolder.eventName.setText(scheduledEvents.getEventName());
        eventHolder.eventStart.setText(scheduledEvents.getStartDate());
        //eventHolder.eventEnd.setText(scheduledEvents.getEndDate());
        eventHolder.bookingQuantity.setText(scheduledEvents.getBookingQuantity());
        return convertView;
    }
    private class EventHolder {
        TextView eventName,  eventStart, bookingQuantity;

        public EventHolder(View item) {
            eventName = (TextView) item.findViewById(R.id.booking_name);
            eventStart = (TextView) item.findViewById(R.id.booking_start_time);
            //eventEnd = (TextView) item.findViewById(R.id.booking_end_time);
            bookingQuantity= (TextView) item.findViewById(R.id.booking_quantity);
        }
    }
}
