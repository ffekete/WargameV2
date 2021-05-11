package com.blacksoft.state;

public class Config {

    public static final int MAP_HEIGHT = 20;
    public static final int MAP_WIDTH = 30;

    public static final int TEXTURE_SIZE = 16;

    public static final int SCREEN_WIDTH = 1366;
    public static final int SCREEN_HEIGHT = 768;

    public static final int STARTING_DUNGEON_LENGTH = 0;

    // priorities
    public static final int GRAVEYARD_PRIORITY = 5;
    public static final int TORCH_PRIORITY = 5;
    public static final int BLOOD_POOL_PRIORITY = 5;

    // progress values
    public static final int CLEAN_PROGRESS_VALUE = 5;
    public static final int GRAVEYARD_PROGRESS_VALUE = 10;
    public static final int TORCH_PROGRESS_VALUE = 10;
    public static final int BLOOD_POOL_PROGRESS_VALUE = 20;

    // spawn limits
    public static final int GRAVEYARD_SPAWN_TIME_LIMIT = 30;
    public static final int OOZE_SPAWN_TIME_LIMIT = 25;
    public static final int VAMPIRE_SPAWN_TIME_LIMIT = 25;

    // salaries
    public static final int VAMPIRE_SALARY_REQUEST = 100;
    public static final int SKELETON_SALARY_REQUEST = 0;
    public static final int OOZE_SALARY_REQUEST = 0;

    public static final float NO_SALARY_MORALE_PENALTY = 10f;
}
