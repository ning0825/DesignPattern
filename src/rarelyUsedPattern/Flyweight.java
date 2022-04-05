package rarelyUsedPattern;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式
 * 
 * ## 定义
 * 
 * ## 应用场景
 * 
 * ## 目的
 * 复用对象，节省内存
 */
public class Flyweight {
    public static void main(String[] args) {

    }

    /**
     * 假设举办沃尔沃赛车比赛
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
        }
    }

}
