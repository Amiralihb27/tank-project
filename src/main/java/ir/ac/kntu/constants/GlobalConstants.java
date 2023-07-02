package ir.ac.kntu.constants;

public class GlobalConstants {
    public static int canvasWidth = 600;


    public static int canvasHeight = 600;

    public static int tankSize = 25;


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
}
