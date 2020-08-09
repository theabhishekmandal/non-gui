package annotations_processing;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.stream.Stream;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno {
    String str();
    int val();
}

@Retention(RetentionPolicy.RUNTIME)
@interface What {
    String description() default "adding default value";
}

public class AnnotationDemo1 {
    static class Meta{
        private Meta(){}
        @MyAnno(str = "Annotation Example.", val = 100)
        @What(description = "An annotation test class")
        public static void myMeth(){

            // Obtain the annotation for this method
            // and display the values of the members.
            try{
                // First, get a Class object that represents
                // this method
                Class<?> c = Meta.class;

                //Now, get a Method object that represents
                // this method.
                Method m = c.getMethod("myMeth");

                //Next, get the annotation for this class
                MyAnno anno = m.getAnnotation(MyAnno.class);

                // Getting all the annotations
                Annotation[] annotations = m.getAnnotations();
                Stream.of(annotations).forEach(System.out::println);

                System.out.println(anno.str() + " " + anno.val());
            }
            catch(NoSuchMethodException e){
                System.out.println("method not found");
            }
        }
    }
    public static void main(String[] args) {
       AnnotationDemo1.Meta.myMeth();
    }
}
