
import java.util.Deque;
import java.util.LinkedList;

/**
 * 解释器模式
 * 
 * 不常用，只在编译器、规则引擎、正则表达式等领域用到
 * 
 * ## 定义
 * 为某个语言定义一个语法，并定义一个解释器来处理这个语法
 */
public class Interpreter {
    public static void main(String[] args) {
        long result = new ExpressionInterpreter().interpret("1 2 3 4 * + -");
        System.out.println(result);
    }

    private interface Expression {
        long interpret();
    }

    private static class NumberExpression implements Expression {
        private long number;

        public NumberExpression(long number) {
            this.number = number;
        }

        public NumberExpression(String number) {
            this.number = Long.parseLong(number);
        }

        @Override
        public long interpret() {
            return this.number;
        }
    }

    private static class AdditionExpression implements Expression {
        private Expression exp1;
        private Expression exp2;

        public AdditionExpression(Expression exp1, Expression exp2) {
            this.exp1 = exp1;
            this.exp2 = exp2;
        }

        @Override
        public long interpret() {
            return exp1.interpret() + exp2.interpret();
        }
    }

    private static class SubstractionExpression implements Expression {
        private Expression exp1;
        private Expression exp2;

        public SubstractionExpression(Expression exp1, Expression exp2) {
            this.exp1 = exp1;
            this.exp2 = exp2;
        }

        @Override
        public long interpret() {
            return exp1.interpret() - exp2.interpret();
        }
    }

    private static class MultiplicationExpression implements Expression {
        private Expression exp1;
        private Expression exp2;

        public MultiplicationExpression(Expression exp1, Expression exp2) {
            this.exp1 = exp1;
            this.exp2 = exp2;
        }

        @Override
        public long interpret() {
            return exp1.interpret() * exp2.interpret();
        }
    }

    private static class DivisionExpression implements Expression {
        private Expression exp1;
        private Expression exp2;

        public DivisionExpression(Expression exp1, Expression exp2) {
            this.exp1 = exp1;
            this.exp2 = exp2;
        }

        @Override
        public long interpret() {
            return exp1.interpret() / exp2.interpret();
        }
    }

    private static class ExpressionInterpreter {
        private Deque<Expression> numbers = new LinkedList<>();

        public long interpret(String expression) {
            String[] elements = expression.split(" ");
            int length = elements.length;
            for (int i = 0; i < (length + 1) / 2; i++) {
                numbers.addLast(new NumberExpression(elements[i]));
            }

            for (int i = (length + 1) / 2; i < length; i++) {
                String operator = elements[i];
                boolean isValid = "+".equals(operator) || "-".equals(operator) || "*".equals(operator)
                        || "/".equals(operator);
                if (!isValid) {
                    throw new RuntimeException("Expression is invalid: " + expression);
                }

                Expression exp1 = numbers.pollFirst();
                Expression exp2 = numbers.pollFirst();
                Expression combinedExp = null;
                if (operator.equals("+")) {
                    combinedExp = new AdditionExpression(exp1, exp2);
                } else if (operator.equals("-")) {
                    combinedExp = new SubstractionExpression(exp1, exp2);
                } else if (operator.equals("*")) {
                    combinedExp = new MultiplicationExpression(exp1, exp2);
                } else if (operator.equals("/")) {
                    combinedExp = new DivisionExpression(exp1, exp2);
                }
                long result = combinedExp.interpret();
                numbers.addFirst(new NumberExpression(result));
            }

            if (numbers.size() != 1) {
                throw new RuntimeException("Expression is invalid: " + expression);
            }
            return numbers.pop().interpret();
        }
    }

}
