/**
 * 单例模式
 */
public class Singleton {
    public static void main(String[] args) {
//        // 枚举方式
//        VolvoCorp volvoCorp = VolvoCorp.INSTANCE;
//        volvoCorp.sellCar(10);
//        System.out.println(volvoCorp.getCarNum());
//
//        VolvoCorp volvoCorp1 = VolvoCorp.INSTANCE;
//        volvoCorp1.sellCar(20);
//        System.out.println(volvoCorp1.getCarNum());

        // 其他方式
        VolvoCorp volvoCorp = VolvoCorp.getInstance();
        volvoCorp.sellCar(10);
        System.out.println(volvoCorp.getCarNum());

        VolvoCorp volvoCorp1 = VolvoCorp.getInstance();
        volvoCorp1.sellCar(20);
        System.out.println(volvoCorp1.getCarNum());
    }

    /**
     * 懒汉式
     *
     * 支持延迟加载
     * */
    private static class VolvoCorp{
        private int carNum = 100;

        private static VolvoCorp instance;

        private VolvoCorp(){};

        public void sellCar(int num){
            carNum -= num;
        }

        public int getCarNum(){
            return carNum;
        }

        public static VolvoCorp getInstance(){
            if (instance == null) {
                instance = new VolvoCorp();
            }

            return instance;
        }
    }

    /**
     * 饿汉式
     *
     * 不支持延迟加载
     * */
//    public static class VolvoCorp{
//        private int carNum = 100;
//
//        private static final VolvoCorp instance = new VolvoCorp();
//
//        private VolvoCorp(){}
//
//        public void sellCar(int num){
//            carNum -= num;
//        }
//
//        public int getCarNum(){
//            return carNum;
//        }
//
//        public static VolvoCorp getInstance(){
//            return instance;
//        }
//    }

    /**
     * 饿汉式
     *
     * 不支持延迟加载
     * */
//    public static class VolvoCorp{
//        private int carNum = 100;
//
//        private static final VolvoCorp instance = new VolvoCorp();
//
//        private VolvoCorp(){}
//
//        public void sellCar(int num){
//            carNum -= num;
//        }
//
//        public int getCarNum(){
//            return carNum;
//        }
//
//        public static VolvoCorp getInstance(){
//            return instance;
//        }
//    }

    /**
     * 双重检测
     *
     * 支持懒加载，高并发
     * */
//    public static class VolvoCorp{
//        private int carNum = 100;
//        private static VolvoCorp instance;
//
//        private VolvoCorp(){}
//
//        public void sellCar(int num){
//            carNum -= num;
//        }
//
//        public int getCarNum(){
//            return carNum;
//        }
//
//        public static VolvoCorp getInstance(){
//            if (instance == null){
//                synchronized (VolvoCorp.class){
//                    if (instance == null){
//                        instance = new VolvoCorp();
//                    }
//                }
//            }
//
//            return instance;
//        }
//    }

    /**
     * 静态内部类
     *
     * 调用 getInstance() 时，SingletonClass 才会被加载，instance 才会被创建
     * 既支持延迟加载，又支持高并发
     */
//    public static class VolvoCorp {
//        private int carNum = 100;
//        private static VolvoCorp instance;
//
//        private VolvoCorp() {}
//
//        public void sellCar(int num) {
//            carNum -= num;
//        }
//
//        public int getCarNum() {
//            return carNum;
//        }
//
//        public static VolvoCorp getInstance(){
//            return SingletonClass.volvoCorpInstance;
//        }
//
//        private static class SingletonClass{
//            private static final VolvoCorp volvoCorpInstance = new VolvoCorp();
//        }
//    }

    /**
     * 枚举类
     *
     * 利用枚举本身的特性保证线程安全性和实例唯一性
     */
//    public enum VolvoCorp {
//        INSTANCE;
//
//        private int carNum = 100;
//
//        public void sellCar(int num) {
//            carNum -= num;
//        }
//
//        public int getCarNum() {
//            return carNum;
//        }
//    }


}
