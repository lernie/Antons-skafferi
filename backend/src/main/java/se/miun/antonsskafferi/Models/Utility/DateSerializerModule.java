package se.miun.antonsskafferi.Models.Utility;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.sql.Date;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateSerializerModule extends SimpleModule {

    public DateSerializerModule() {
        super("DateSerializerModule");
        this.addSerializer(Date.class, new DateSerializer());
        this.addDeserializer(Date.class, new DateDeserializer());
    }

    public class DateSerializer extends JsonSerializer<Date> {
        @Override
        public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            /*https://coderanch.com/t/521898/java/Convert-Java-sql-Date-String*/
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            jgen.writeString(df.format(value));
        }
    }

    public class DateDeserializer extends JsonDeserializer<Date> {

        @Override
        public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            java.util.Date utilDate = null;
            try {
                utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(jsonParser.getText());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new Date(utilDate.getTime());
        }
    }
}