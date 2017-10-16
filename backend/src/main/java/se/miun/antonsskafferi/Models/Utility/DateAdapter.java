package se.miun.antonsskafferi.Models.Utility;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class DateAdapter extends XmlAdapter<Date, String>{

    @Override
    public String unmarshal(Date v) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("DateSerializer called");
        return df.format(v);
        //jgen.writeString(df.format(value));
    }

    @Override
    public Date marshal(String v) throws Exception {
        java.util.Date utilDate = null;
        System.out.println("DateDeserializer called");
        try {
            utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(v);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date(utilDate.getTime());
    }
}
