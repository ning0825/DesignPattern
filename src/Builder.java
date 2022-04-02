/**
 * 建造者模式/生成器模式
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

        private static class VolvoCarBuilder{
            private VolvoCar volvoCar;

            VolvoCarBuilder(){
                volvoCar = new VolvoCar();
            }

            VolvoCar build(){
                // 某些属性之间有约束关系的话，可以在这里作校验
                return volvoCar;
            }

            VolvoCarBuilder setExteriorColor(String color){
                if (color != null && !color.isEmpty()) {
                    volvoCar.exteriorColor = color;
                }
                return this;
            }

            VolvoCarBuilder setInteriorColor(String color){
                if (color != null && !color.isEmpty()) {
                    volvoCar.interiorColor = color;
                }
                return this;
            }

            VolvoCarBuilder setHasBwSound(boolean hasBwSound){
                volvoCar.hasBwSound = hasBwSound;
                return this;
            }

            VolvoCarBuilder setHasHUD(boolean hasHUD){
                volvoCar.hasHUD = hasHUD;
                return this;
            }

            VolvoCarBuilder setWheelSize(int wheelSize){
                volvoCar.wheelSize = wheelSize;
                return this;
            }
        }
    }
}
