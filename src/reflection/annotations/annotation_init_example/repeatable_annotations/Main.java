package reflection.annotations.annotation_init_example.repeatable_annotations;

import reflection.annotations.annotation_init_example.annotations.Annotations;
import reflection.annotations.annotation_init_example.annotations.ScanPackages;
import reflection.annotations.util.AnnotationUtil;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Repeatable Annotation example.
 */
@ScanPackages(values = {"/reflection/annotations/annotation_init_example/repeatable_annotations/loaders"})
public class Main {
    public static void main(String[] args) throws Throwable {
        schedule();
    }

    public static void schedule() throws Throwable {
        List<Class<?>> allClasses = AnnotationUtil.getAllClasses(Main.class);
        List<Method> scheduledExecutorMethods = getScheduledExecutorMethods(allClasses);

        for (Method method : scheduledExecutorMethods) {
            scheduleMethodExecution(method);
        }
    }

    private static void scheduleMethodExecution(Method method) {
        Annotations.ExecuteOnSchedule[] executeOnSchedules = method.getAnnotationsByType(Annotations.ExecuteOnSchedule.class);
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        for (Annotations.ExecuteOnSchedule schedule : executeOnSchedules) {
            scheduledExecutorService.scheduleAtFixedRate(() -> runWhenScheduled(method),
                    schedule.delaySeconds(), schedule.periodSeconds(), TimeUnit.SECONDS);
        }
    }

    private static void runWhenScheduled(Method method) {
        Date currentDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println(String.format("Executing at %s", simpleDateFormat.format(currentDate)));

        try {
            method.invoke(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static List<Method> getScheduledExecutorMethods(List<Class<?>> allClasses) {
        var methods = new ArrayList<Method>();
        for (Class<?> clazz : allClasses) {
            if (!clazz.isAnnotationPresent(Annotations.ScheduledExecutorClass.class)) {
                continue;
            }
            for (Method declaredMethod : clazz.getDeclaredMethods()) {
                // Since it is a repeatable annotation, only getAnnotationByType will work in this.
                if (declaredMethod.getAnnotationsByType(Annotations.ExecuteOnSchedule.class).length != 0) {
                    methods.add(declaredMethod);
                }
            }
        }
        return methods;
    }
}
