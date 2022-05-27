package reflection.field_object.configuration_parser;

import reflection.field_object.configuration_parser.dto.GameConfig;
import reflection.field_object.configuration_parser.dto.UserInterfaceConfig;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;

public class Main {
    private static final Path GAME_CONFIG_PATH = Path.of("src/reflection/field_object/configuration_parser/config/game-properties.cfg");
    private static final Path UI_CONFIG_PATH = Path.of("src/reflection/field_object/configuration_parser/config/user-interface.cfg");

    public static void main(String[] args) throws IOException,
            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        GameConfig gameConfig = ConfigurationParser.getInstance().createConfigObject(GameConfig.class, GAME_CONFIG_PATH);
        System.out.println(gameConfig);

        UserInterfaceConfig userInterfaceConfig = ConfigurationParser.getInstance().createConfigObject(UserInterfaceConfig.class, UI_CONFIG_PATH);
        System.out.println(userInterfaceConfig);
    }
}
