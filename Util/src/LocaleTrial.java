import java.util.Locale;

import static java.util.Locale.CHINA;

/**
 * Created by Administrator on 2020/2/13 15:29.
 */
public class LocaleTrial {
    public static void main(String[] args) {
        LocaleTrial localeTrial = new LocaleTrial();

//        localeTrial.get();

//        localeTrial.filter();
//        localeTrial.create();
        localeTrial.tag();

    }

    private void tag() {

//        Locale locale = Locale.forLanguageTag("xx-YY");
//        System.out.println("getLanguage is " + locale.getLanguage());
//        System.out.println("getCountry is " + locale.getCountry());

//        System.out.println(CHINA);
//        System.out.println(CHINA.toLanguageTag());



    }

    private void create() {

//        Locale locale = new Locale("zh");
//        System.out.println(locale);

//        Locale locale = new Locale("zh", "CN");
//        System.out.println(locale);

        Locale locale = new Locale("aa", "BBB", "cccc");
        System.out.println(locale);


    }

    private void filter() {
//        Locale.filter()
    }


    public void get() {
//        System.out.println("getAvailableLocales is " + CHINA.getAvailableLocales().length);
//        for (Locale locale : CHINA.getAvailableLocales()) {
//            System.out.println("locale is " + locale);
//        }


//        System.out.println("getDefault is " + Locale.getDefault());//获取当前默认地域
//        System.out.println("getDisplayName is " + CHINA.getDisplayName());//获取地域显示名
//        System.out.println("getCountry is " + CHINA.getCountry());//获取地域所属的国家码
//        System.out.println("getDisplayCountry is " + CHINA.getDisplayCountry());//获取地域所属的国家名
//        System.out.println("getLanguage is " + CHINA.getLanguage());//获取地域语言码
//        System.out.println("getDisplayLanguage is " + CHINA.getDisplayLanguage());//获取地域语言显示名
//        System.out.println("getScript is " + CHINA.getScript());//获取地域脚本码
//        System.out.println("getDisplayScript is " + CHINA.getDisplayScript());//获取地域脚本显示名
//        System.out.println("getVariant is " + CHINA.getVariant());//获取地域变体码
//        System.out.println("getDisplayVariant is " + CHINA.getDisplayVariant());//获取地域变体显示名



//        System.out.println("getISO3Country is " + CHINA.getISO3Country());//地域所属国家码（3字母）
//        System.out.println("getISO3Language is " + CHINA.getISO3Language());//地域语言码（3字母）


//        System.out.println("getISOCountries is " + CHINA.getISOCountries().length);//获取所有相关国家码
//        for (String s : CHINA.getISOCountries()) {
//            System.out.println(s);
//        }
//        System.out.println("getISOLanguages is " + CHINA.getISOLanguages().length);//获取所有相关语言码
//        for (String s : CHINA.getISOLanguages()) {
//            System.out.println(s);
//        }


//        System.out.println("getExtension is " + CHINA.getExtension());
//        System.out.println("getExtensionKeys is " + CHINA.getExtensionKeys());

//        System.out.println("getUnicodeLocaleAttributes is " + CHINA.getUnicodeLocaleAttributes());
//        System.out.println("getUnicodeLocaleKeys is " + CHINA.getUnicodeLocaleKeys());
//        System.out.println("getUnicodeLocaleType is " + CHINA.getUnicodeLocaleType());

    }
}
