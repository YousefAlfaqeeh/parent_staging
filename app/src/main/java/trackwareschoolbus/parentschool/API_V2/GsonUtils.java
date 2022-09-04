package trackwareschoolbus.parentschool.API_V2;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;


public class GsonUtils {

    private static Gson gson;

    public static Gson getGson() {
        if (gson == null) {
            com.google.gson.GsonBuilder builder = new com.google.gson.GsonBuilder()
                    .disableHtmlEscaping()
                    .serializeNulls()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE);


            gson = builder.create();
        }
        return gson;
    }


    public static class GsonBuilder {
        JsonObject jsonObject;

        public GsonBuilder() {
            jsonObject = new JsonObject();
        }

        public GsonBuilder addParam(String key, Object value) {
            if (value instanceof String)
                jsonObject.addProperty(key, value.toString());
            if (value instanceof Integer)
                jsonObject.addProperty(key, ((Integer) value).intValue());
            if (value instanceof Boolean)
                jsonObject.addProperty(key, (Boolean) value);
            return this;
        }

        public JsonObject create() {
            return jsonObject;
        }

    }

//    public static<T> T toStringGson(String jsonInString){
//        return gson.fromJson(jsonInString, (Class<T>) (T) new TypeToken<T>() {}.getType());
//    }


//    public static <T> String gsonToString(T t) {
//        return getGson().toJson(t);
//    }


    public static <T> T StringToGson(String stringGson) {
        try {
            return getGson().fromJson(stringGson, (Class<T>) (T) new TypeToken<T>() {
            }.getType());
        } catch (Exception e) {
            return null;
        }

    }

    public static <T> String GsonToString(T gsonObject) {
        try {
            return getGson().toJson(gsonObject);

        } catch (Exception e) {
            return "";
        }
    }

//    public static <Review> void GsonBuilderFromFile(String fileName) {
//        try {
//            final Type review_type = new TypeToken<List<Review>>() {
//            }.getType();
//            Gson gson = new Gson();
//            JsonReader reader = new JsonReader(new FileReader(fileName));
//            List<Review> data = gson.fromJson(reader, review_type); // contains the whole reviews list
//        } catch (Exception e) {
//
//        }
//
//
//    }


}
