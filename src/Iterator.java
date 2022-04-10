import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * 迭代器模式（游标模式）
 *
 * ## 定义
 * 提供一种方法顺序访问聚合对象中各个元素，而不暴露该对象的内部表示
 */
public class Iterator {
    public static void main(String[] args) {
        ArrayList<String> cars = new ArrayList<>();
        cars.add("S60");
        cars.add("S90");
        cars.add("S80");

        IIterator<String> iterator = new ArrayIterator<>(cars);
        while (iterator.hasNext()) {
            System.out.println(iterator.currentItem());
            iterator.next();
        }
    }

    // 迭代器接口
    public interface IIterator<T> {
        boolean hasNext();

        T currentItem();

        void next();
    }

    private static class ArrayIterator<T> implements IIterator<T> {
        private int cursor;
        private ArrayList<T> arrayList;

        public ArrayIterator(ArrayList<T> arrayList) {
            this.cursor = 0;
            this.arrayList = arrayList;
        }

        @Override
        public boolean hasNext() {
            return cursor != arrayList.size() - 1;
        }

        @Override
        public T currentItem() {
            if (cursor >= arrayList.size()) {
                throw new NoSuchElementException();
            }
            return arrayList.get(cursor);
        }

        @Override
        public void next() {
            cursor++;
        }
    }
}
