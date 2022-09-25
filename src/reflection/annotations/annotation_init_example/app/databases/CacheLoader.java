package reflection.annotations.annotation_init_example.app.databases;

import reflection.annotations.annotation_init_example.annotations.InitializerClass;
import reflection.annotations.annotation_init_example.annotations.InitializerMethod;

@InitializerClass
public class CacheLoader {

    @InitializerMethod
    public void loadCache() {
        System.out.println("Loading data from cache");
    }

    public void reloadCache() {
        System.out.println("Reload cache");
    }
}
