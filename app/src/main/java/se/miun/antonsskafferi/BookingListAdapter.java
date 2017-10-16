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

public class BookingListAdapter extends BaseAdapter {
    private Context context;
    private List<BookedEvents> bookedEvents;
    private LayoutInflater inflater;
    public BookingListAdapter(Context context, List<BookedEvents> bookedEvents){
        this.context = context;
        this.bookedEvents = bookedEvents;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return bookedEvents.size();
    }

    @Override
    public Object getItem(int i) {
        return bookedEvents.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EventHolder eventHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.booking_list_item, parent, false);
            eventHolder = new EventHolder(convertView);
            convertView.setTag(eventHolder);
        } else {
            eventHolder = (EventHolder) convertView.getTag();
        }
        BookedEvents bookedEvents = (BookedEvents) getItem(position);
        eventHolder.eventName.setText(bookedEvents.getEventName());
        eventHolder.eventStart.setText(bookedEvents.getStartDate());
        //eventHolder.eventEnd.setText(bookedEvents.getEndDate());
        eventHolder.bookingQuantity.setText(bookedEvents.getBookingQuantity());
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
