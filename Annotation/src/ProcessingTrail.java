import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * Created by Administrator on 2021/5/11.
 */
public class ProcessingTrail {
    public static void main(String[] args) {
        ProcessingTrail processingTrail = new ProcessingTrail();


        AbstractProcessor abstractProcessor = new AbstractProcessor() {
            @Override
            public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
                return false;
            }
        };
    }
}
