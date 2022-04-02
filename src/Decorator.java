/**
 * 装饰器模式
 *
 * 组合代替继承
 *
 * 装饰器类和原始类继承同样的父类，可以对原始类嵌套多个装饰类
 *
 * 代理类附加跟原始类无关的功能，而装饰器类附加的是跟原始类相关的增强功能
 */
public class Decorator {
    public static void main(String[] args) {
        new VolvoCarDecorator(new VolvoCar()).run();
    }

    //接口
    private interface Car{
        void run();
    }

    //原始类
    private static class VolvoCar implements Car{
        @Override
        public void run() {
            System.out.println("Volvo car is running!");
        }
    }

    //装饰器类
    private static class VolvoCarDecorator implements Car{
        Car car;

        VolvoCarDecorator(Car car){
            this.car = car;
        }

        @Override
        public void run() {
            car.run();
            System.out.println("Open B&W sound!");
        }
    }
}
