/**
 * 装饰器模式
 * 
 * * ## 定义
 * 将对象放入包含行为的特殊封装对象中来为原对象绑定新的行为
 * 
 * ## 应用场景
 * 用以处理继承关系过于复杂的场景，用组合代替继承，给原始类增强功能
 * 
 * ## 注意
 * 装饰器类和原始类需要继承相同的抽象类或接口，以实现对原始类嵌套多个装饰类
 * 代理类附加跟原始类无关的功能，而装饰器类附加的是跟原始类相关的增强功能
 */
public class Decorator {
    public static void main(String[] args) {
        VolvoCar volvoCar = new VolvoCar();
        BWSoundDecorator bwSoundDecorator = new BWSoundDecorator(volvoCar);
        AutopilotDecorator autopilotDecorator = new AutopilotDecorator(bwSoundDecorator);
        autopilotDecorator.run();
    }

    // 接口
    private interface Car {
        void run();
    }

    // 原始类
    private static class VolvoCar implements Car {
        @Override
        public void run() {
            System.out.println("Volvo car is running!");
        }
    }

    // 装饰器类1
    private static class BWSoundDecorator implements Car {
        Car car;

        BWSoundDecorator(Car car) {
            this.car = car;
        }

        @Override
        public void run() {
            car.run();
            System.out.println("Open B&W sound!");
        }
    }

    // 装饰器类2
    private static class AutopilotDecorator implements Car {
        Car car;

        AutopilotDecorator(Car car) {
            this.car = car;
        }

        @Override
        public void run() {
            car.run();
            System.out.println("Turn on autopilot!");
        }
    }
}
