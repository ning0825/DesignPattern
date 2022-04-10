/**
 * 状态模式
 * 
 * 状态模式将事件触发的状态转移和动作执行，分拆到不同的类中，来避免分支判断逻辑
 * 
 * 有限状态机（Finite state machine）包含三个部分：状态（State），事件（Event），动作（Action），
 * 事件触发状态的转移和动作的执行（也可能只转移状态，不执行动作）
 *
 * 状态机的实现方式：状态模式，分支逻辑法，查表法
 *
 * 分支逻辑法：可读性和可维护性差，适合简单的状态机
 * <code>
public class CarStateMachine{
    ...
    public void engineOn(){
        if(currentState == CarState.IDLE){
            currentState = CarState.READY;
        }
    }
}
</code>
 * 
 * 查表法：
 * <code>
public class CarStateMachine {
    private CarState currentState;

    private enum CarState {
        IDLE(0), // 未启动
        READY(1), // 已启动
        RUNNING(2); // 行驶中

        private int value;

        private CarState(int value){
            this.value = value;
        }

        public int getValue(){
            return this.value;
        }
    }

    private enum CarEvent {
        ENGINE_ON(0),
        ACCELERATE(1),
        BRAKE(2),
        ENGINE_OFF(3);

        private int value;

        private CarEvent(int value){
            this.value = value;
        }

        public int getValue(){
            return this.value;
        }
    }


    // 定义一个二维数组，第一维表示当前状态，第二维表示事件，值表示当前状态经过事件之后，转移到的新状态
    private static final CarState[][] transitionTable = {
            { CarState.READY, CarState.IDLE, CarState.IDLE, CarState.IDLE },
            { CarState.READY, CarState.RUNNING, CarState.READY, CarState.IDLE },
            { CarState.RUNNING, CarState.RUNNING, CarState.READY, CarState.IDLE },
    };

    // 状态转移
    private void executeEvent(CarEvent event){
        this.currentState = transitionTable[currentState.getValue()][event.getValue()];
    }
}
</code>
 */
public class State {
    public static void main(String[] args) {
        CarStateMachine machine = new CarStateMachine();
        System.out.println(machine.getCurrentCarState().getState());

        machine.engineOn();
        System.out.println(machine.getCurrentCarState().getState());

        machine.engineOff();
        System.out.println(machine.getCurrentCarState().getState());
    }

    private enum CarState {
        IDLE, // 未启动
        READY, // 已启动
        RUNNING, // 行驶中
    }

    private interface ICarState {
        CarState getState();

        void engineOn(CarStateMachine machine);

        void accelerate(CarStateMachine machine);

        void brake(CarStateMachine machine);

        void engineOff(CarStateMachine machine);
    }

    private static class IdleCar implements ICarState {
        private static final IdleCar instance = new IdleCar();

        private IdleCar() {
        }

        public static IdleCar getInstance() {
            return instance;
        }

        @Override
        public CarState getState() {
            return CarState.IDLE;
        }

        @Override
        public void engineOn(CarStateMachine machine) {
            machine.setCurrentCarState(ReadyCar.getInstance());
        }

        @Override
        public void accelerate(CarStateMachine machine) {

        }

        @Override
        public void brake(CarStateMachine machine) {

        }

        @Override
        public void engineOff(CarStateMachine machine) {

        }
    }

    private static class ReadyCar implements ICarState {
        private static final ReadyCar instance = new ReadyCar();

        private ReadyCar() {
        }

        public static ReadyCar getInstance() {
            return instance;
        }

        @Override
        public CarState getState() {
            return CarState.READY;
        }

        @Override
        public void engineOn(CarStateMachine machine) {

        }

        @Override
        public void accelerate(CarStateMachine machine) {
            machine.setCurrentCarState(RunningCar.getInstance());
        }

        @Override
        public void brake(CarStateMachine machine) {

        }

        @Override
        public void engineOff(CarStateMachine machine) {
            machine.setCurrentCarState(IdleCar.getInstance());
        }
    }

    private static class RunningCar implements ICarState {
        private static final RunningCar instance = new RunningCar();

        private RunningCar() {
        }

        public static RunningCar getInstance() {
            return instance;
        }

        @Override
        public CarState getState() {
            return CarState.RUNNING;
        }

        @Override
        public void engineOn(CarStateMachine machine) {

        }

        @Override
        public void accelerate(CarStateMachine machine) {

        }

        @Override
        public void brake(CarStateMachine machine) {
            machine.setCurrentCarState(ReadyCar.getInstance());
        }

        @Override
        public void engineOff(CarStateMachine machine) {
            machine.setCurrentCarState(IdleCar.getInstance());
        }
    }

    private static class CarStateMachine {
        private ICarState currentCarState;

        public CarStateMachine() {
            this.currentCarState = IdleCar.getInstance();
        }

        void engineOn() {
            this.currentCarState.engineOn(this);
        }

        void accelerate() {
            this.currentCarState.accelerate(this);
        }

        void engineOff() {
            this.currentCarState.engineOff(this);
        }

        void setCurrentCarState(ICarState carState) {
            this.currentCarState = carState;
        }

        ICarState getCurrentCarState() {
            return currentCarState;
        }
    }
}
