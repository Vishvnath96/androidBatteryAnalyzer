package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class GsonUtil {

    private static GsonUtil sInstance;

    private GsonBuilder mGsonBuilder;
    private Gson mGson;

    private GsonUtil(){
        mGsonBuilder = new GsonBuilder();
        mGson = mGsonBuilder.create();
    }

    public static synchronized GsonUtil getInstance(){
        if(sInstance == null){
            sInstance = new GsonUtil();
        }
        return sInstance;
    }



    /**
     * Serializes an object to its JSON representation
     * <p>
     *
     * This method should be used only with non generic objects.
     * @param  object the InputStream to read the incoming JSON object
     * @return        the  JSON representation in form of a String
     */
    public String serializeToJson(Object object) {
        try {
            return mGson.toJson(object);
        }catch (Exception e){
         e.printStackTrace();
        }catch (IncompatibleClassChangeError e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Serializes an object to its JSON representation
     * <p>
     * This method should be used only with non generic objects.
     *
     * @param  object the InputStream to read the incoming JSON object
     * @return        the  JSON representation in form of a JsonElement
     */
    public JsonElement serializeToJsonElement(Object object) {
        try {
            return mGson.toJsonTree(object);
        }catch (Exception e){
            e.printStackTrace();
        }catch (IncompatibleClassChangeError e){
            e.printStackTrace();
        }
        return null;
    }

    @Deprecated
    public String prettyPrintObject(Object object){
        return mGsonBuilder.setPrettyPrinting().create().toJson(object);
    }


   }
