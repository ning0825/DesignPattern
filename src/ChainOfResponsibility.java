import com.sun.xml.internal.ws.wsdl.writer.document.soap.Body;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChainOfResponsibility {
    public static void main(String[] args) {
        AssemblyLine assemblyLine = new AssemblyLine();
        assemblyLine.addAssembler(new BodyAssembler());
        assemblyLine.addAssembler(new InteriorAssembler());
        assemblyLine.addAssembler(new ComponentAssembler());
        assemblyLine.startAssemble();
    }

    private static class AssemblyLine{
        private Assembler head = null;
        private Assembler tail = null;

        void addAssembler(Assembler assembler){
            assembler.setNextAssembler(null);

            if (head == null){
                head = assembler;
                tail = assembler;
                return;
            }

            tail.setNextAssembler(assembler);
            tail = assembler;
        }

        void startAssemble(){
            if (head != null){
                head.assemble();
            }
        }
    }

    private abstract static class Assembler{
        private Assembler nextAssembler;

        public void setNextAssembler(Assembler nextAssembler){
            this.nextAssembler = nextAssembler;
        }

        public void assemble(){
            boolean stop = doAssemble();
            if (!stop && nextAssembler != null){
                nextAssembler.assemble();
            }
        }

        public abstract boolean doAssemble();
    }

    //车身
    private static class BodyAssembler extends Assembler{
        @Override
        public boolean doAssemble() {
            System.out.println("Body Assembly Finished!");
            return false;
        }
    }

    //内饰
    private static class InteriorAssembler extends Assembler{
        @Override
        public boolean doAssemble() {
            System.out.println("Interior Assembly Finished!");
            if (new Random().nextBoolean()) {
                return true;
            }
            return false;
        }
    }

    //其他零件（最后一步）
    private static class ComponentAssembler extends Assembler{
        @Override
        public boolean doAssemble() {
            System.out.println("Component Assembly Finished! The Car Is READY!");
            return true;
        }
    }
}
