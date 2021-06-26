package com.mygdx.mechwargame.util;

import com.badlogic.gdx.math.Vector2;

public class MathUtils {

    public static float getAngle(Vector2 s, Vector2 e) {
        float angle = (float) (Math.atan2(
                e.y - s.y,
                e.x - s.x
        ) * 180.0d / Math.PI);

        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }

    public static double getDistance(float x, float y, float x2, float y2) {
        return Math.sqrt(Math.abs(x - x2) * Math.abs(x - x2) + Math.abs(y - y2) * Math.abs(y - y2));


    }
}
