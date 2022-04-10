/**
 * 桥接模式
 *
 * ## 定义
 * 1. 解耦【抽象】和【实现】，使其能独立变化。
 * 2. 一个类存在两个（或多个）独立变化的纬度，通过组合的方式，让这两个（或多个）维度可以独立进行扩展。
 * 通过组合关系来替代继承关系，避免继承层次的指数级爆炸。
 * 
 * 
 */
public class Bridge {
    public static void main(String[] args) {
        ErrorAlertHandler errorAlertHandler = new ErrorAlertHandler(5,
                new SevereNotification(new TelephoneMsgSender()));
        errorAlertHandler.check(8);
    }

    private enum NotificationEmergencyLevel {
        SEVERE, URGENCY, NORMAL
    }

    private interface MsgSender {
        void send(String message);
    }

    private static class TelephoneMsgSender implements MsgSender {
        @Override
        public void send(String message) {
            System.out.println("Send message by telephone: " + message);
        }
    }

    private static class EmailMsgSender implements MsgSender {
        @Override
        public void send(String message) {
            System.out.println("Send message by email: " + message);
        }
    }

    private static class WechatMsgSender implements MsgSender {
        @Override
        public void send(String message) {
            System.out.println("Send message by wechat: " + message);
        }
    }

    private static abstract class Notification {
        protected MsgSender msgSender;

        public Notification(MsgSender msgSender) {
            this.msgSender = msgSender;
        }

        public abstract void notify(String message);
    }

    private static class SevereNotification extends Notification {
        public SevereNotification(MsgSender msgSender) {
            super(msgSender);
        }

        @Override
        public void notify(String message) {
            msgSender.send(message);
        }
    }

    private static class UrgencyNotification extends Notification {
        public UrgencyNotification(MsgSender msgSender) {
            super(msgSender);
        }

        @Override
        public void notify(String message) {
            msgSender.send(message);
        }
    }

    private static class NormalNotification extends Notification {
        public NormalNotification(MsgSender msgSender) {
            super(msgSender);
        }

        @Override
        public void notify(String message) {
            msgSender.send(message);
        }
    }

    private static class ErrorAlertHandler {
        private int maxCount;
        private Notification notification;

        public ErrorAlertHandler(int maxCount, Notification notification) {
            this.maxCount = maxCount;
            this.notification = notification;
        }

        public void check(int errorCount) {
            if (errorCount > maxCount) {
                notification.notify("error count: " + errorCount);
            }
        }
    }

}
