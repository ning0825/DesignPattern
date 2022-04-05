import java.util.HashMap;
import java.util.Map;

/**
 * 工厂方法
 */
public class FactoryMethod {
    public static void main(String[] args) {
        VolvoCarProducer.produceCar("s60").printInfo();
        VolvoCarProducer.produceCar("S60").printInfo();
        VolvoCarProducer.produceCar("s90").printInfo();
    }

    // /**
    // * 简单工厂 - 实现方式一
    // */
    // private static class VolvoCarProducer{
    // public static VolvoCar produceCar(String module){
    // VolvoCar volvoCar = null;
    // if ("s60".equalsIgnoreCase(module)){
    // volvoCar = new S60Car();
    // }
    // if ("s90".equalsIgnoreCase(module)) {
    // volvoCar = new S90Car();
    // }
    // return volvoCar;
    // }
    // }

    /**
     * 简单工厂 - 实现方式二
     * <p>
     * 加入缓存
     */
    private static class VolvoCarProducer {
        static Map<String, VolvoCar> cachedCars = new HashMap<>();

        static {
            cachedCars.put("s60", new S60Car());
            cachedCars.put("s90", new S90Car());
        }

        public static VolvoCar produceCar(String module) {
            if (module == null || module.isEmpty()) {
                return null;
            }
            VolvoCar volvoCar = cachedCars.get(module.toLowerCase());
            return volvoCar;
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
}
