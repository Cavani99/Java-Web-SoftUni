package main.model;

import java.util.concurrent.ThreadLocalRandom;

public enum PlayerClass {

    MYSTIC_MUSE, GHOST_HUNTER, DOOMBRINGER;

    private static final PlayerClass[] VALUES = values();
    private static final int SIZE = VALUES.length;

    public static PlayerClass random() {
        return VALUES[ThreadLocalRandom.current().nextInt(SIZE)];
    }

    }