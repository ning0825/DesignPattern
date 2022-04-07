package rarelyUsedPattern;

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

    }

}
