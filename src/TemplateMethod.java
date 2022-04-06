/**
 * 模板方法
 * 
 * ## 定义
 * 模板方法模式在一个方法中定义一个算法骨架，并将某些步骤推迟到子类实现。
 * 模板方法模式可以让子类在不改变算法整体结构的情况下，重新定义算法中的某些步骤。
 * （这里的算法指业务逻辑，算法骨架就是模板，包含算法骨架的方法就是模板方法）
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
        // 模板方法
        void printBaseInfo() {
            System.out.println("Some S90 base infomation.");

            int wheelSize = getWheelSize();
            System.out.println("Wheel size: " + wheelSize);

            boolean hasBWSound = getHasBWSound();
            System.out.println("Has BW sound: " + hasBWSound);
        }

        // 交由子类实现
        abstract int getWheelSize();

        // 交由子类实现
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
