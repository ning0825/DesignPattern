/**
 * 建造者模式/生成器模式
 * 
 * ## 定义
 * 将对象的创建划分为一系列步骤，创建对象时只需调用所需的步骤
 * 
 * ## 应用场景
 * 1. 必填参数很多，放在构造器中会导致参数列表很长，用set()方法设置又无处校验
 * 2. 类的属性间存在一定的依赖条件或约束条件，用set()方法无处校验条件这些
 * 3. 希望创建不可变对象，不适用于set()方法
 */
public class Builder {
    public static void main(String[] args) {
        VolvoCar volvoCar = new VolvoCar.VolvoCarBuilder().setExteriorColor("blue").setHasBwSound(true).build();
        System.out.println(volvoCar.toString());
    }

    private static class VolvoCar {
        String exteriorColor = "black";
        String interiorColor = "black";
        boolean hasBwSound = false;
        boolean hasHUD = false;
        int wheelSize = 18;

        @Override
        public String toString() {
            return "VolvoCar{" +
                    "exteriorColor='" + exteriorColor + '\'' +
                    ", interiorColor='" + interiorColor + '\'' +
                    ", hasBwSound=" + hasBwSound +
                    ", hasHUD=" + hasHUD +
                    ", wheelSize=" + wheelSize +
                    '}';
        }

        private static class VolvoCarBuilder {
            private VolvoCar volvoCar;

            VolvoCarBuilder() {
                volvoCar = new VolvoCar();
            }

            VolvoCar build() {
                // 某些属性之间有约束关系的话，可以在这里作校验
                return volvoCar;
            }

            VolvoCarBuilder setExteriorColor(String color) {
                if (color != null && !color.isEmpty()) {
                    volvoCar.exteriorColor = color;
                }
                return this;
            }

            VolvoCarBuilder setInteriorColor(String color) {
                if (color != null && !color.isEmpty()) {
                    volvoCar.interiorColor = color;
                }
                return this;
            }

            VolvoCarBuilder setHasBwSound(boolean hasBwSound) {
                volvoCar.hasBwSound = hasBwSound;
                return this;
            }

            VolvoCarBuilder setHasHUD(boolean hasHUD) {
                volvoCar.hasHUD = hasHUD;
                return this;
            }

            VolvoCarBuilder setWheelSize(int wheelSize) {
                volvoCar.wheelSize = wheelSize;
                return this;
            }
        }
    }
}
