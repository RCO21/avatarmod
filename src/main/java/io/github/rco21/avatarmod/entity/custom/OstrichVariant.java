package io.github.rco21.avatarmod.entity.custom; 

import java.util.Arrays;
import java.util.Comparator;
import net.minecraft.util.StringRepresentable;

public enum OstrichVariant implements StringRepresentable {
    DEFAULT(0, "ostrich_horse"),
    DARK(1, "ostrich_horse_darkbrown"),
    BLACK(2, "ostrich_horse_black"), // Change or add names here to match your PNG filenames!
    ORANGISH(3, "ostrich_horse_orangishbrown"),
    ORANGE(4, "ostrich_horse_orange");


    private static final OstrichVariant[] BY_ID = Arrays.stream(values())
            .sorted(Comparator.comparingInt(OstrichVariant::getId))
            .toArray(OstrichVariant[]::new);

    private final int id;
    private final String name;

    OstrichVariant(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return this.id; }
    @Override
    public String getSerializedName() { return this.name; }

    public static OstrichVariant byId(int id) {
        if (id < 0 || id >= BY_ID.length) {
            return DEFAULT;
        }
        return BY_ID[id];
    }
}