package reflection.annotations.util;

import reflection.annotations.annotation_init_example.Main;
import reflection.annotations.annotation_init_example.annotations.ScanPackages;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class AnnotationUtil {

    private AnnotationUtil(){}

    public static List<Class<?>> getAllClasses(Class<?> fromClass) throws URISyntaxException, IOException, ClassNotFoundException {
        ScanPackages scanPackagesAnnotation = fromClass.getAnnotation(ScanPackages.class);
        if (scanPackagesAnnotation == null || scanPackagesAnnotation.values().length == 0) {
            return Collections.emptyList();
        }
        return getAllClasses(scanPackagesAnnotation.values());
    }

    private static List<Class<?>> getAllClasses(String[] packageNames) throws URISyntaxException, IOException,
            ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        for (String packageName : packageNames) {
            String packageRelativePath = packageName.replace(".", "/");
            URI packageUri = Main.class.getResource(packageRelativePath).toURI();
            if (packageUri.getScheme().equals("file")) {
                Path packageFullPath = Paths.get(packageUri);
                classes.addAll(getAllPackageClasses(packageFullPath, packageName));
            } else if (packageUri.getScheme().equals("jar")) {
                FileSystem fileSystem = FileSystems.newFileSystem(packageUri, Collections.emptyMap());
                Path packageFullPath = fileSystem.getPath(packageRelativePath);
                classes.addAll(getAllPackageClasses(packageFullPath, packageName));
                fileSystem.close();
            }
        }
        return classes;
    }


    private static List<Class<?>> getAllPackageClasses(Path packagePath, String packageName) throws IOException,
            ClassNotFoundException {
        if (!Files.exists(packagePath)) {
            return Collections.emptyList();
        }

        List<Path> files = Files.list(packagePath)
                .filter(Files::isRegularFile)
                .toList();

        List<Class<?>> classes = new ArrayList<>();
        for (var file : files) {
            String fileName = file.getFileName().toString();
            if (fileName.endsWith(".class")) {
                if (packageName.startsWith("/")) {
                    packageName = packageName.substring(1);
                }
                String replacedString = packageName.replace('/', '.');
                String classFullName = replacedString + "." + fileName.replaceAll("\\.class$", "");
                Class<?> clazz = Class.forName(classFullName);
                classes.add(clazz);
            }
        }
        return classes;
    }
}
