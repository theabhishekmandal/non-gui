package reflection.annotations.annotation_init_example.app.configs;

import reflection.annotations.annotation_init_example.annotations.InitializerClass;
import reflection.annotations.annotation_init_example.annotations.InitializerMethod;

@InitializerClass
public class ConfigLoader {
    @InitializerMethod
    public void loadAllConfigs() {
        System.out.println("Loading all configuration files");
    }
}
