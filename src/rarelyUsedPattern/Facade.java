package rarelyUsedPattern;

/**
 * 门面模式
 * 
 * ## 定义
 * 门面模式为子系统提供一组统一的接口，定义一组高层接口让子系统更易用。（“子系统”可以是完整的系统，也可以是类或模块）
 * 
 * ## 应用场景
 * - 解决易用性问题：隐藏实现复杂性，类似迪米特法则，如Linux系统的调用函数和Shell命令
 * - 解决性能问题：如减少网络通信次数
 * - 解决分布式事务问题：在一个接口中执行两个SQL操作
 */
public class Facade {
    public static void main(String[] args) {
        new VolvoAssembler().assemble();
    }

    private static class VolvoAssembler {
        private void assembleBody() {
            System.out.println("assemble body");
        }

        private void assembleInterior() {
            System.out.println("assemble interior");
        }

        public void assemble() {
            assembleBody();
            assembleInterior();
        }
    }
}
