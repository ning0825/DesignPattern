package rarelyUsedPattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 命令模式
 * 
 * 将函数封装成对象，可以将对象存储下来，方便控制执行。
 * 
 * 主要应用场景：异步，延迟，排队执行命令，撤销重做命令，存储命令，给命令记录日志等。
 * 
 * 
 */
public class Command {
    public static void main(String[] args) {
        GameController controller = new GameController();
        controller.mainLoop();
    }

    public static class Request {
        private Event event;

        public Request(Event event) {
            this.event = event;
        }

        Event getEvent() {
            return this.event;
        }
    }

    private enum Event {
        GotDiamond,
        HitObstacle,
    }

    private static interface ICommand {
        void execute();
    }

    private static class GotDiamondCommand implements ICommand {
        @Override
        public void execute() {
            System.out.println("execute got diamond command");
        }
    }

    private static class HitObstacleCommand implements ICommand {
        @Override
        public void execute() {
            System.out.println("execute hit obstacle command");
        }
    }

    private static class GameController {
        private static final int MAX_HANDLED_REQ_COUNT_PER_LOOP = 100;
        private Queue<ICommand> queue = new LinkedList<>();

        void mainLoop() {
            // while(true){
            List<Request> requests = Arrays.asList(new Request(Event.GotDiamond), new Request(Event.HitObstacle));

            for (Request request : requests) {
                Event event = request.getEvent();
                ICommand command = null;

                if (event == Event.GotDiamond) {
                    command = new GotDiamondCommand();
                } else if (event == Event.HitObstacle) {
                    command = new HitObstacleCommand();
                }

                queue.add(command);
            }

            int handedCount = 0;
            while (handedCount < MAX_HANDLED_REQ_COUNT_PER_LOOP) {
                if (queue.isEmpty()) {
                    break;
                }

                ICommand command = queue.poll();
                command.execute();
            }
            // }
        }
    }
}
