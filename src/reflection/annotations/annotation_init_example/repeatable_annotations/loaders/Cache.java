package reflection.annotations.annotation_init_example.repeatable_annotations.loaders;

import reflection.annotations.annotation_init_example.annotations.Annotations;

@Annotations.ScheduledExecutorClass
public class Cache {

    @Annotations.ExecuteOnSchedule(periodSeconds = 5)
    @Annotations.ExecuteOnSchedule(delaySeconds = 10, periodSeconds = 1)
    public static void reloadCache() {
        System.out.println("Reloading Cache");
    }
}
