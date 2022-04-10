import java.util.HashMap;
import java.util.Map;

/**
 * 策略模式
 * 
 * ## 定义
 * 定义一组算法类，将每个算法封装起来，让它们可以互相替换。
 * 策略模式可以使算法的变化独立于使用它们的客户端
 * 
 * ## 应用场景
 * 解耦策略的定义，创建和使用，控制代码的复杂度
 * 让复杂代码满足开闭原则，添加新策略时，最小化、集中化代码改动，减少引入bug的风险
 */
public class Strategy {
    public static void main(String[] args) {
        // 运行时动态指定
        ProductStrategy strategy = ProductStrategyFactory.getStrategy("s90");
        ProductInfoPrinter printer = new ProductInfoPrinter(strategy);
        printer.printInfo();

        ProductStrategy strategy2 = new S90Strategy();
        ProductInfoPrinter printer2 = new ProductInfoPrinter(strategy2);
        printer2.printInfo();
    }

    /**
     * 为了灵活替换不同的策略，需要基于接口而非实现编程
     */
    private interface ProductStrategy {
        void printProductConfig();
    }

    private static class S90Strategy implements ProductStrategy {
        @Override
        public void printProductConfig() {
            System.out.println("s90:{wheel base: 3067, has BW sound: true}");
        }
    }

    private static class S60Strategy implements ProductStrategy {
        @Override
        public void printProductConfig() {
            System.out.println("s60: {wheel base: 2870, has BW sound: false}");
        }
    }

    private static class ProductStrategyFactory {
        private static final Map<String, ProductStrategy> strategies = new HashMap<>();

        static {
            strategies.put("s90", new S90Strategy());
            strategies.put("s60", new S60Strategy());
        }

        static ProductStrategy getStrategy(String product) {
            if (product == null || product.isEmpty()) {
                throw new IllegalArgumentException("product should not be empty");
            }
            return strategies.get(product.toLowerCase());
        }
    }

    private static class ProductInfoPrinter {
        ProductStrategy strategy;

        ProductInfoPrinter(ProductStrategy strategy) {
            this.strategy = strategy;
        }

        void printInfo() {
            System.out.println("Basic Volvo info.");
            this.strategy.printProductConfig();
        }
    }

}
