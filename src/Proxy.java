/**
 * 代理模式
 *
 * 解耦框架代码和业务代码
 *
 * 代理类附加跟原始类无关的功能，而装饰器类附加的是跟原始类相关的增强功能
 */
public class Proxy {
    public static void main(String[] args) {
        SPAPlatformProxy spaPlatformProxy = new SPAPlatformProxy(new SPAPlatform());
        spaPlatformProxy.produceCarBody();
    }

    private interface IPlatform{
        void produceCarBody();
    }

    private static class SPAPlatform implements IPlatform{
        @Override
        public void produceCarBody() {
            System.out.println("Produce basic car body");
        }
    }

    //TODO 代理类应该添加与原始类无关的功能
    private static class SPAPlatformProxy implements IPlatform{
        private IPlatform spaPlatform;

        SPAPlatformProxy(IPlatform platform){
            this.spaPlatform = platform;
        }

        public void produceCarBody() {
            spaPlatform.produceCarBody();
            System.out.println("Add more components");
        }
    }
}
