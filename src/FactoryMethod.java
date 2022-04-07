import java.util.HashMap;
import java.util.Map;

/**
 * 工厂方法
 * 
 * 工厂的工厂，适合对象创建逻辑复杂的场景使用
 */
public class FactoryMethod {
    public static void main(String[] args) {
        VolvoCarFactoryMap.getCarFactory("s60").createVolvoCar().printInfo();
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

    private static class VolvoCarFactoryMap {
        private static final Map<String, IVolvoCarFactory> cachedFactories = new HashMap<>();

        static {
            cachedFactories.put("s60", new S60CarFactory());
            cachedFactories.put("s90", new S90CarFactory());
        }

        public static IVolvoCarFactory getCarFactory(String module) {
            if (module == null || module.isEmpty()) {
                return null;
            }
            IVolvoCarFactory carFactory = cachedFactories.get(module.toLowerCase());
            return carFactory;
        }
    }
}
