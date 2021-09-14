import javax.annotation.processing.Completion;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * Created by Administrator on 2021/5/11.
 */
public class TheProcessor implements Processor {
    @Override
    public Set<String> getSupportedOptions() {
        System.out.println("TheProcessor.getSupportedOptions");
        return null;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        System.out.println("TheProcessor.getSupportedAnnotationTypes");
        return null;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        System.out.println("TheProcessor.getSupportedSourceVersion");
        return null;
    }

    @Override
    public void init(ProcessingEnvironment processingEnv) {
        System.out.println("TheProcessor.init");
        System.out.println("processingEnv = " + processingEnv);

    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("TheProcessor.process");
        System.out.println("annotations = " + annotations + ", roundEnv = " + roundEnv);
        return false;
    }

    @Override
    public Iterable<? extends Completion> getCompletions(Element element, AnnotationMirror annotation, ExecutableElement member, String userText) {
        System.out.println("TheProcessor.getCompletions");
        System.out.println("element = " + element + ", annotation = " + annotation + ", member = " + member + ", userText = " + userText);
        return null;
    }
}
