package factory;

import java.util.ResourceBundle;

/**
 * Created by RZ on 4/19/16.
 */
public class BeanFactory {

    private static ResourceBundle bundle;
    static {
        bundle = ResourceBundle.getBundle("instance");
    }

    public static <T> T getInstance(String key,Class<T> clazz) {
            String className = bundle.getString(key);
        try {
            return (T) Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        }
    }
