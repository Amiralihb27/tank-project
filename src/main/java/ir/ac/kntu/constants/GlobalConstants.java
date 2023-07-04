package ir.ac.kntu.constants;

public class GlobalConstants {
    public static int canvasWidth = 600;


    public static int canvasHeight = 600;

    public static int tankSize = 25;

    public static int bulletSpeed;


    public static int speedX;


    public static int speedY;

    public static int timeForSpeacialPowers;



    public GlobalConstants(int width, int height) {
        canvasWidth = width;
        canvasHeight = height;
    }


    public static int getTankSize() {
        return tankSize;
    }

    public static void setTankSize(int tankSize) {
        GlobalConstants.tankSize = tankSize;
    }

    public static int getCanvasWidth() {
        return canvasWidth;
    }

    public static void setCanvasWidth(int canvasWidth) {
        GlobalConstants.canvasWidth = canvasWidth;
    }

    public static int getCanvasHeight() {
        return canvasHeight;
    }

    public static void setCanvasHeight(int canvasHeight) {
        GlobalConstants.canvasHeight = canvasHeight;
    }

    public static int getBulletSpeed() {
        return bulletSpeed;
    }

    public static void setBulletSpeed(int bulletSpeed) {
        GlobalConstants.bulletSpeed = bulletSpeed;
    }

    public static int getTimeForSpeacialPowers() {
        return timeForSpeacialPowers;
    }

    public static void setTimeForSpeacialPowers(int timeForSpeacialPowers) {
        GlobalConstants.timeForSpeacialPowers = timeForSpeacialPowers;
    }
}
