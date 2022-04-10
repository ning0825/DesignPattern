
import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式
 * 
 * ## 定义
 * 共享多个对象所共有的相同状态，达到节省内存的目的
 * 
 * ## 应用场景
 * 系统中存在大量重复对象，并且是不可变对象
 */
public class Flyweight {
    public static void main(String[] args) {
        // 五个车手，只实例化了三个VolvoCar对象就够
        Player p1 = new Player("John", VolvoCarFactory.getVolvoCar("red_s60"));
        Player p2 = new Player("John", VolvoCarFactory.getVolvoCar("red_s60"));
        Player p3 = new Player("John", VolvoCarFactory.getVolvoCar("red_s90"));
        Player p4 = new Player("John", VolvoCarFactory.getVolvoCar("black_s90"));
        Player p5 = new Player("John", VolvoCarFactory.getVolvoCar("black_s90"));
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        System.out.println(p5);
    }

    /**
     * 假设开发一款赛车游戏，比赛中可能出现相同型号相同颜色的车
     */
    private static class VolvoCar {
        String module;
        String color;

        public VolvoCar(String module, String color) {
            this.module = module;
            this.color = color;
        }
    }

    private static class VolvoCarFactory {
        private static final Map<String, VolvoCar> cars = new HashMap<>();

        static {
            cars.put("red_s60", new VolvoCar("s60", "red"));
            cars.put("black_s60", new VolvoCar("s60", "black"));
            cars.put("red_s90", new VolvoCar("s90", "red"));
            cars.put("black_s90", new VolvoCar("s90", "black"));
        }

        static VolvoCar getVolvoCar(String module) {
            if (module != null && !module.isEmpty()) {
                return cars.get(module.toLowerCase());
            }
            return null;
        }
    }

    /**
     * 参赛者，包含车手和车辆
     */
    private static class Player {
        // 车手名称
        private String name;
        // 所用赛车
        private VolvoCar car;

        public Player(String name, VolvoCar car) {
            this.name = name;
            this.car = car;
        }

        @Override
        public String toString() {
            return "Player: {name: " + name + "car: " + car.module + "_" + car.color + ", hashcode: " + car.hashCode()
                    + "}";
        }
    }

}
