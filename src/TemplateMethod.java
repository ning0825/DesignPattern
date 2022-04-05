/**
 * 模板方法
 * 
 * 
 */
public class TemplateMethod {

    public static void main(String[] args) {
        StandardS90 standardS90 = new StandardS90();
        standardS90.printBaseInfo();

        UltimateS90 ultimateS90 = new UltimateS90();
        ultimateS90.printBaseInfo();
    }

    private static abstract class S90Procuder {
        void printBaseInfo() {
            System.out.println("Some S90 base infomation.");

            int wheelSize = getWheelSize();
            System.out.println("Wheel size: " + wheelSize);

            boolean hasBWSound = getHasBWSound();
            System.out.println("Has BW sound: " + hasBWSound);
        }

        abstract int getWheelSize();

        abstract boolean getHasBWSound();
    }

    private static class StandardS90 extends S90Procuder {
        @Override
        int getWheelSize() {
            return 18;
        }

        @Override
        boolean getHasBWSound() {
            return false;
        }
    }

    private static class UltimateS90 extends S90Procuder {
        @Override
        int getWheelSize() {
            return 20;
        }

        @Override
        boolean getHasBWSound() {
            return true;
        }

    }
}
