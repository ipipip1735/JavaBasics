import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/5/4.
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
//@Target({ElementType.TYPE, ElementType.METHOD,
//        ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE,
//        ElementType.PACKAGE, ElementType.FIELD, ElementType.LOCAL_VARIABLE})
@Inherited

public @interface MyAnnotation {
    int value() default 33;
}
