/**
 * 适配器模式
 * 
 * ## 定义
 * 将不兼容的接口转换为兼容的接口，让原本由于接口不兼容而不能一起工作的类可以一起工作
 * 类比 USB 转接头
 * 
 * ## 应用场景
 * 1. 封装有缺陷的接口设计
 * 2. 统一多个类的接口设计
 * 3. 替换依赖的外部系统
 * 4. 兼容老版本接口
 * 5. 适配不同格式的数据
 * 
 * ## 何时使用类适配器，何时使用对象适配器
 * 1. Adaptee 接口不多的话，两种都可以
 * 2. Adaptee 接口很多，且 Adaptee 和 ITarget 接口定义大部分相同时，使用类适配器，因为 Adaptor 可以复用父类 Adaptee 的接口
 * 3. Adaptee 接口很多，且 Adaptee 和 ITarget 接口定义大部分不相同，使用对象适配器，因为组合相比继承更灵活
 */

/**
 * 类适配器 - 基于继承
 */
//public class Adapter {
//    public static void main(String[] args) {
//        VolvoCharger volvoCharger = new VolvoCharger();
//        volvoCharger.chargeVolvo();
//        volvoCharger.showBatteryLevel();
//    }
//
//    private interface IVolvoCharger{
//        void chargeVolvo();
//    }
//
//    private static class TeslaCharger{
//        int power = 120;
//        int batteryLevel = 100;
//
//        protected void chargeTesla(){
//            System.out.println("Charging... power: "+ power);
//        }
//
//        void showBatteryLevel(){
//            System.out.println(batteryLevel);
//        }
//    }
//
//    private static class VolvoCharger extends TeslaCharger implements IVolvoCharger{
//        @Override
//        public void chargeVolvo() {
//            power = 80;
//            super.chargeTesla();
//        }
//    }
//}

/**
 * 对象适配器 - 基于组合
 */
public class Adapter {
    public static void main(String[] args) {
        TeslaCharger teslaCharger = new TeslaCharger();
        VolvoCharger volvoCharger = new VolvoCharger(teslaCharger);
        volvoCharger.chargeVolvo();
        // 没有 TeslaCharger 的 showBatteryLevel().
        // volvoCharger.showBatteryLevel();
    }

    private interface IVolvoCharger {
        void chargeVolvo();
    }

    private static class TeslaCharger {
        int power = 120;
        int batteryLevel = 100;

        protected void chargeTesla() {
            System.out.println("Charging... power: " + power);
        }

        void showBatteryLevel() {
            System.out.println(batteryLevel);
        }
    }

    private static class VolvoCharger implements IVolvoCharger {
        private TeslaCharger teslaCharger;

        VolvoCharger(TeslaCharger teslaCharger) {
            this.teslaCharger = teslaCharger;
        }

        @Override
        public void chargeVolvo() {
            teslaCharger.power = 80;
            teslaCharger.chargeTesla();
        }
    }
}
