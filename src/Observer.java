import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式(Observable-Observer)
 * 别的叫法：Subject-Observer, Publisher-Subscriber, Producer-Consumer, EvenEmitter-EventListener, Dispatcher-Listener
 *
 *
 */
public class Observer {
    public static void main(String[] args) {
        VolvoCorp volvoCorp = new VolvoCorp();
        BeiJingAgency beiJingAgency1 = new BeiJingAgency();
        BeiJingAgency beiJingAgency2 = new BeiJingAgency();
        TaiYuanAgency taiYuanAgency1 = new TaiYuanAgency();
        TaiYuanAgency taiYuanAgency2 = new TaiYuanAgency();

        volvoCorp.addAgency(beiJingAgency1);
        volvoCorp.addAgency(beiJingAgency2);
        volvoCorp.addAgency(taiYuanAgency1);
        volvoCorp.addAgency(taiYuanAgency2);
        volvoCorp.announceNewCar("The All New Volvo S90");

        volvoCorp.removeAgency(beiJingAgency1);
        volvoCorp.announceNewCar("The All New Volvo S60");
    }

    // 被观察者（Observable）
    private static class VolvoCorp {
        List<IAgency> agencies = new ArrayList<IAgency>();

        void addAgency(IAgency agency) {
            agencies.add(agency);
        }

        void removeAgency(IAgency agency){
            agencies.remove(agency);
        }

        void announceNewCar(String module) {
            for (IAgency agency : agencies) {
                agency.advertise(module);
            }
        }
    }

    // 观察者（Observer）
    private interface IAgency {
        //有新车发布，在店内布置广告
        void advertise(String module);
    }

    private static class BeiJingAgency implements IAgency {
        @Override
        public void advertise(String module) {
            System.out.println("BeiJing agency will have new car: " + module + " |" + hashCode());
        }
    }

    private static class TaiYuanAgency implements IAgency {
        @Override
        public void advertise(String module) {
            System.out.println("TaiYuan agency will have new car: " + module + " |" + hashCode());
        }
    }
}
