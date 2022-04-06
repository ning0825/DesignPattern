package rarelyUsedPattern;

/**
 * 原型模式
 * 
 * 原型模式将克隆过程委派给被克隆的对象
 * 
 * 
 */
public class Prototype {
    public static void main(String[] args) {
        VolvoCar volvoCar = new VolvoCar("s60", "black", 2.0);
        VolvoCar clonedVolvoCar = volvoCar.clone();
        System.out.println(clonedVolvoCar);
    }

    private static class VolvoCar {
        private String module;
        private String color;
        private double displacement;

        public VolvoCar(String module, String color, double displacement) {
            this.module = module;
            this.color = color;
            this.displacement = displacement;
        }

        public VolvoCar clone() {
            VolvoCar volvoCar = new VolvoCar(module, color, displacement);
            return volvoCar;
        }

        @Override
        public String toString() {
            return "VolvoCar: {module: " + module + ", color: " + color + ", displacement: " + displacement + "}";
        }
    }
}
