import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.util.Locale.*;
import static java.util.Locale.CHINESE;

/**
 * Created by Administrator on 2020/2/13 8:55.
 */
public class ResourceBundleTrial {
    public static void main(String[] args) {
        ResourceBundleTrial resourceBundleTrial = new ResourceBundleTrial();

        resourceBundleTrial.get();
//        resourceBundleTrial.contral();
//        resourceBundleTrial.cache();

    }

    private void cache() {

        ResourceBundle resourceBundle;
        ResourceBundle.Control control = new ResourceBundle.Control(){
            @Override
            public long getTimeToLive(String baseName, Locale locale) {
                System.out.println("~~" + getClass().getSimpleName() + ".getTimeToLive~~");
                System.out.println("baseName is " + baseName);
                System.out.println("locale is " + locale);

                return 1000;
            }

            @Override
            public boolean needsReload(String baseName, Locale locale, String format, ClassLoader loader, ResourceBundle bundle, long loadTime) {
                System.out.println("~~" + getClass().getSimpleName() + ".needsReload~~");
                System.out.println("baseName is " + baseName);
                System.out.println("locale is " + locale);
                System.out.println("locale is " + locale);

//                return super.needsReload(baseName, locale, format, loader, bundle, loadTime);
                return true;
            }
        };

        while (true) {

            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            ResourceBundle.clearCache();
//            resourceBundle = ResourceBundle.getBundle("cc");
            resourceBundle = ResourceBundle.getBundle("cc", control);



            System.out.println(resourceBundle.keySet());
            for (String k : resourceBundle.keySet()) {
                System.out.println(k + " is " + resourceBundle.getString(k));
            }
        }
    }

    private void contral(){

        ResourceBundle.Control control = new ResourceBundle.Control() {
            @Override
            public List<Locale> getCandidateLocales(String baseName, Locale locale) {
                System.out.println("~~" + getClass().getSimpleName() + ".getCandidateLocales~~");

                System.out.println("baseName is " + baseName);
                System.out.println("locale is " + locale);

                System.out.println(super.getCandidateLocales(baseName, locale));

                return super.getCandidateLocales(baseName, locale);
            }

            @Override
            public List<String> getFormats(String baseName) {
                System.out.println("~~" + getClass().getSimpleName() + ".getFormats~~");
                System.out.println("baseName is " + baseName);

                System.out.println(super.getFormats(baseName));
                return super.getFormats(baseName);
            }

            @Override
            public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IllegalAccessException, InstantiationException, IOException {
                System.out.println("~~" + getClass().getSimpleName() + ".newBundle~~");
                System.out.println("baseName is " + baseName);
                System.out.println("locale is " + locale);
                System.out.println("format is " + format);
                System.out.println("loader is " + loader);
                System.out.println("reload is " + reload);

                return super.newBundle(baseName, locale, format, loader, reload);
            }

            @Override
            public String toBundleName(String baseName, Locale locale) {
                System.out.println("~~" + getClass().getSimpleName() + ".toBundleName~~");
                System.out.println("baseName is " + baseName);
                System.out.println("locale is " + locale);

                System.out.println(super.toBundleName(baseName, locale));
                System.out.println(toResourceName(super.toBundleName(baseName, locale), "oooo"));

                return super.toBundleName(baseName, locale);
            }
        };

        ResourceBundle resourceBundle = ResourceBundle.getBundle("XX", Locale.forLanguageTag("aa-BB"), control);
        System.out.println(resourceBundle.keySet());
        for (String k : resourceBundle.keySet()) {
            System.out.println(k + " is " + resourceBundle.getString(k));
        }
    }

    private void get() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("XX");
//        ResourceBundle resourceBundle = ResourceBundle.getBundle("XX", Locale.forLanguageTag("aa"));
//        ResourceBundle resourceBundle = ResourceBundle.getBundle("XX", Locale.forLanguageTag("aa-BB"));
        System.out.println("resourceBundle is " + resourceBundle);
        System.out.println("getBaseBundleName is " + resourceBundle.getBaseBundleName());
        System.out.println("getLocale is " + resourceBundle.getLocale());


//        System.out.println(resourceBundle.containsKey("one"));
//        System.out.println(resourceBundle.containsKey("two"));

//        System.out.println(resourceBundle.getString("one"));
        System.out.println(resourceBundle.keySet());
        for (String k : resourceBundle.keySet()) {
            System.out.println(k + " is " + resourceBundle.getString(k));
        }


//        System.out.println(resourceBundle.getF);


//        ResourceBundle resourceBundle = ResourceBundle.getBundle("a", CHINA);
//        System.out.println("resourceBundle is " + resourceBundle);
    }
}
