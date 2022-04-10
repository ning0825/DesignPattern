import java.util.Random;

/**
 * 职责链模式（责任链模式）
 * 
 * ## 定义
 * 将请求的发送和接收解耦，让多个对象都有机会接收这个请求。
 * 将这些接收对象串成一条链，并沿着这条链传递这个请求，直到某个接收对象能够处理它为止。
 * 
 * ## 应用场景
 * 过滤器，拦截器等
 */
public class ChainOfResponsibility {
    public static void main(String[] args) {
        AssemblyLine assemblyLine = new AssemblyLine();
        assemblyLine.addAssembler(new BodyAssembler());
        assemblyLine.addAssembler(new InteriorAssembler());
        assemblyLine.addAssembler(new ComponentAssembler());
        assemblyLine.startAssemble();
    }

    private static class AssemblyLine {
        private Assembler head = null;
        private Assembler tail = null;

        void addAssembler(Assembler assembler) {
            assembler.setNextAssembler(null);

            if (head == null) {
                head = assembler;
                tail = assembler;
                return;
            }

            tail.setNextAssembler(assembler);
            tail = assembler;
        }

        void startAssemble() {
            if (head != null) {
                head.assemble();
            }
        }
    }

    private abstract static class Assembler {
        private Assembler nextAssembler;

        public void setNextAssembler(Assembler nextAssembler) {
            this.nextAssembler = nextAssembler;
        }

        // 这里使用了模板模式，doAssemble()交由子类实现
        public void assemble() {
            boolean stop = doAssemble();
            if (!stop && nextAssembler != null) {
                nextAssembler.assemble();
            }
        }

        public abstract boolean doAssemble();
    }

    // 车身
    private static class BodyAssembler extends Assembler {
        @Override
        public boolean doAssemble() {
            System.out.println("Body Assembly Finished!");
            return false;
        }
    }

    // 内饰
    private static class InteriorAssembler extends Assembler {
        @Override
        public boolean doAssemble() {
            System.out.println("Interior Assembly Finished!");
            if (new Random().nextBoolean()) {
                return true;
            }
            return false;
        }
    }

    // 其他零件（最后一步）
    private static class ComponentAssembler extends Assembler {
        @Override
        public boolean doAssemble() {
            System.out.println("Component Assembly Finished! The Car Is READY!");
            return true;
        }
    }
}
