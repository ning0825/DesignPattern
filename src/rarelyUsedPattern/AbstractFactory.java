package rarelyUsedPattern;

import java.util.HashMap;
import java.util.Map;

/**
 * 抽象工厂
 * 
 * 简单工厂和工厂方法都只能创建一种类型的对象，抽象工厂可以创建多个不同类型的对象
 * 
 * ## 定义
 * 创建一系列相关对象，而无需指定其具体类
 * 
 * ## 应用场景
 * 代码需要和多个不同系列的相关产品交互，无法预知相关信息，或处于未来扩展性的考虑
 */
public class AbstractFactory {
    public static void main(String[] args) {
        VolvoFactoryMap.getCarFactory("s60").createVolvoCar().printInfo();
        VolvoFactoryMap.getCarFactory("s60").createVolvoManual().printManualContent();
    }

    private interface VolvoManual {
        void printManualContent();
    }

    private static class S60Manual implements VolvoManual {
        @Override
        public void printManualContent() {
            System.out.println("This is a manual for Volvo S60");
        }
    }

    private static class S90Manual implements VolvoManual {
        @Override
        public void printManualContent() {
            System.out.println("This is a manual for Volvo S90");
        }
    }

    private interface IVolvoManualFactory {
        VolvoManual createVolvoManual();
    }

    private static class S60ManualFactory implements IVolvoManualFactory {
        @Override
        public VolvoManual createVolvoManual() {
            return new S60Manual();
        }
    }

    private static class S90ManualFactory implements IVolvoManualFactory {
        @Override
        public VolvoManual createVolvoManual() {
            return new S90Manual();
        }
    }

    private static abstract class VolvoCar {
        String name;
        int wheelBase;

        protected void printInfo() {
            System.out.println("name: " + this.name + ", wheelBase: " + this.wheelBase);
        }
    }

    private static class S60Car extends VolvoCar {
        S60Car() {
            this.name = "Volvo S60";
            this.wheelBase = 2870;
        }
    }

    private static class S90Car extends VolvoCar {
        S90Car() {
            this.name = "Volvo S90";
            this.wheelBase = 3061;
        }
    }

    private interface IVolvoCarFactory {
        VolvoCar createVolvoCar();
    }

    private static class S60CarFactory implements IVolvoCarFactory {
        @Override
        public VolvoCar createVolvoCar() {
            return new S60Car();
        }
    }

    private static class S90CarFactory implements IVolvoCarFactory {
        @Override
        public VolvoCar createVolvoCar() {
            return new S90Car();
        }
    }

    private interface IVolvoFactory {
        VolvoCar createVolvoCar();

        VolvoManual createVolvoManual();
    }

    private static class S60Factory implements IVolvoFactory {
        @Override
        public VolvoCar createVolvoCar() {
            return new S60Car();
        }

        @Override
        public VolvoManual createVolvoManual() {
            return new S60Manual();
        }
    }

    private static class S90Factory implements IVolvoFactory {

        @Override
        public VolvoCar createVolvoCar() {
            return new S90Car();
        }

        @Override
        public VolvoManual createVolvoManual() {
            return new S90Manual();
        }
    }

    private static class VolvoFactoryMap {
        private static final Map<String, IVolvoFactory> cachedFactories = new HashMap<>();

        static {
            cachedFactories.put("s60", new S60Factory());
            cachedFactories.put("s90", new S90Factory());
        }

        public static IVolvoFactory getCarFactory(String module) {
            if (module == null || module.isEmpty()) {
                return null;
            }
            IVolvoFactory carFactory = cachedFactories.get(module.toLowerCase());
            return carFactory;
        }
    }
}
