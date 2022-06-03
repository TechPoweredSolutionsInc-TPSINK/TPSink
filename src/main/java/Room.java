import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import java.awt.*;
import java.io.File;

/**
 * A representation of a {@link Room} object including a name and other optional fields.
 * @author Jasper Newkirk
 * @version 1.0.0
 */
public class Room {
    /**
     * The name of the current {@link Room}.
     */
    private final String name;
    /**
     * The dimensions of the current {@link Room}.
     */
    private final Dimension dimensions;
    /**
     * The description of the current {@link Room}.
     */
    private final String description;

    /**
     * Constructs a new {@link Room} with the given {@code name}. And creates a corresponding folder in the given {@code path}.
     * @param name The name to be given to the current {@link Room}.
     * @param path The path to the directory associated to the current {@link Room}.
     */
    public Room(String name, String path) {
        this(name, path, null, "");
    }
    /**
     * Constructs a new {@link Room} with the given {@code name}. And creates a corresponding folder in the given {@code path}.
     * @param name The name to be given to the current {@link Room}.
     * @param path The path to the directory associated to the current {@link Room}.
     * @param dimensions The dimensions of this room.
     */
    public Room(String name, String path, Dimension dimensions) {
        this(name, path, dimensions, "");
    }
    /**
     * Constructs a new {@link Room} with the given {@code name}. And creates a corresponding folder in the given {@code path}.
     * @param name The name to be given to the current {@link Room}.
     * @param path The path to the directory associated to the current {@link Room}.
     * @param description The description of the current {@link Room}.
     */
    public Room(String name, String path, String description) {
        this(name, path, null, description);
    }

    /**
     * Constructs a new {@link Room} with the given {@code name}. And creates a corresponding folder in the given {@code path}.
     * @param name The name to be given to the current {@link Room}.
     * @param path The path to the directory associated to the current {@link Room}.
     * @param dimensions The dimensions of the current room.
     * @param description The description of the current {@link Room}.
     */
    public Room(String name, String path, Dimension dimensions, String description) {
        // TODO: Test this
        File room = new File(path + "\\" + name);
        int i = 1;
        while (!room.mkdir()) {
            room = new File(path + "\\" + name + "(" + i++ + ")");
        }
        name += "(" + i + ")";
        this.name = name;
        this.dimensions = dimensions;
        this.description = description;
    }
}



