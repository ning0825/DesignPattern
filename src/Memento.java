
import java.util.Scanner;
import java.util.Stack;

/**
 * 备忘录模式
 * 
 * ## 定义
 * 在不违反封装原则的前提下，捕获一个对象的内部状态，并在对象之外保存这个状态，以便之后恢复对象为先前的状态。
 * 
 * ## 应用场景
 * 防丢失，撤销，恢复
 */
public class Memento {
    public static void main(String[] args) {
        InputText inputText = new InputText();
        SnapshotHolder snapshotHolder = new SnapshotHolder();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.next();
            if (input.equals(":list")) {
                System.out.println(inputText.getText());
            } else if (input.equals(":undo")) {
                Snapshot snapshot = snapshotHolder.popSnapshot();
                inputText.restoreSnapshot(snapshot);
            } else if (input.equals(":q")) {
                scanner.close();
                System.exit(0);
            } else {
                snapshotHolder.pushSnapshot(inputText.createSnapshot());
                inputText.append(input);
            }
        }
    }

    private static class InputText {
        private StringBuilder text = new StringBuilder();

        public String getText() {
            return text.toString();
        }

        public void append(String input) {
            text.append(input);
        }

        public Snapshot createSnapshot() {
            return new Snapshot(text.toString());
        }

        public void restoreSnapshot(Snapshot snapshot) {
            this.text.replace(0, text.length(), snapshot.getText());
        }

    }

    private static class Snapshot {
        private String text;

        public Snapshot(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }
    }

    private static class SnapshotHolder {
        private Stack<Snapshot> snapshots = new Stack<>();

        public Snapshot popSnapshot() {
            return snapshots.pop();
        }

        public void pushSnapshot(Snapshot snapshot) {
            snapshots.push(snapshot);
        }
    }
}
