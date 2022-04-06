package rarelyUsedPattern;

/**
 * 访问者模式
 * 
 * 会导致可读性，可维护性变差
 * 
 * 添加新的文件类型或处理类时，只需要添加新类继承ResourceFile或IVisitor
 */
public class Visitor {
    public static void main(String[] args) {

    }

    private static interface IVisitor {
        void visit(PdfFile PdfFile);

        void visit(WordFile WordFile);

        void visit(PPTFile pptFile);
    }

    private static abstract class ResourceFile {
        protected String filePath;

        public ResourceFile(String filePath) {
            this.filePath = filePath;
        }

        public abstract void accept(IVisitor visitor);
    }

    private static class PdfFile extends ResourceFile {
        public PdfFile(String filePath) {
            super(filePath);
        }

        @Override
        public void accept(IVisitor visitor) {
            visitor.visit(this);
        }
    }

    private static class WordFile extends ResourceFile {
        public WordFile(String filePath) {
            super(filePath);
        }

        @Override
        public void accept(IVisitor visitor) {
            visitor.visit(this);
        }
    }

    private static class PPTFile extends ResourceFile {
        public PPTFile(String filePath) {
            super(filePath);
        }

        @Override
        public void accept(IVisitor visitor) {
            visitor.visit(this);
        }
    }

    private static class Extractor implements IVisitor {

        @Override
        public void visit(PdfFile PdfFile) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(WordFile WordFile) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(PPTFile pptFile) {
            // TODO Auto-generated method stub

        }
    }

    private static class Compressor implements IVisitor {

        @Override
        public void visit(PdfFile PdfFile) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(WordFile WordFile) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(PPTFile pptFile) {
            // TODO Auto-generated method stub

        }
    }
}
