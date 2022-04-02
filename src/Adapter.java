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
        //没有 TeslaCharger 的 showBatteryLevel().
//        volvoCharger.showBatteryLevel();
    }

    private interface IVolvoCharger{
        void chargeVolvo();
    }

    private static class TeslaCharger{
        int power = 120;
        int batteryLevel = 100;

        protected void chargeTesla(){
            System.out.println("Charging... power: "+ power);
        }

        void showBatteryLevel(){
            System.out.println(batteryLevel);
        }
    }

    private static class VolvoCharger implements IVolvoCharger{
        private TeslaCharger teslaCharger;

        VolvoCharger(TeslaCharger teslaCharger){
            this.teslaCharger = teslaCharger;
        }

        @Override
        public void chargeVolvo() {
            teslaCharger.power = 80;
            teslaCharger.chargeTesla();
        }
    }
}
