package reflection.annotations.annotation_init_example.app;

import reflection.annotations.annotation_init_example.annotations.InitializerClass;
import reflection.annotations.annotation_init_example.annotations.InitializerMethod;

@InitializerClass
public class AutoSaver {
    @InitializerMethod
    public void startAutoSavingThreads() {
        System.out.println("Start automatic data saving to disk");
    }
}
