/**
 * 状态模式
 *
 * 有限状态机（Finite state machine）包含三个部分：状态（State），事件（Event），动作（Action），
 * 事件触发状态的转移和动作的执行（也可能只转移状态，不执行动作）
 *
 * 状态机的实现方式：状态模式，分支逻辑法，查表法
 * 分支逻辑法：可读性和可维护性差，适合简单的状态机
 * ```java
 * public class CarStateMachine{
 *     ...
 *
 *     public void engineOn(){
 *         if(currentState == CarState.IDLE){
 *             currentState = CarState.READY;
 *         }
 *     }
 * }
 * ```
 *
 * 查表法：
 * ```java
 * public class CarStateMachine{
 *     ...
 *
 *     public void engineOn(){
 *
 *     }
 * }
 * ```
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

    private enum CarState{
        IDLE,    //未启动
        READY,   //已启动
        RUNNING, //行驶中
    }

    private interface ICarState{
        CarState getState();
        void engineOn(CarStateMachine machine);
        void accelerate(CarStateMachine machine);
        void brake(CarStateMachine machine);
        void engineOff(CarStateMachine machine);
    }

    private static class IdleCar implements ICarState{
        private static final IdleCar instance = new IdleCar();
        private IdleCar(){}
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

    private static class ReadyCar implements ICarState{
        private static final ReadyCar instance = new ReadyCar();
        private ReadyCar(){}

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

    private static class RunningCar implements ICarState{
        private static final RunningCar instance = new RunningCar();
        private RunningCar(){}
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
            machine.setCurrentCarState(IdleCar.getInstance());
        }

        @Override
        public void engineOff(CarStateMachine machine) {

        }
    }

    private static class CarStateMachine{
        private ICarState currentCarState;

        public CarStateMachine(){
            this.currentCarState = IdleCar.getInstance();
        }

        void engineOn(){
            this.currentCarState.engineOn(this);
        }

        void accelerate(){
            this.currentCarState.accelerate(this);
        }

        void engineOff(){
            this.currentCarState.engineOff(this);
        }

        void setCurrentCarState(ICarState carState){
            this.currentCarState = carState;
        }

        ICarState getCurrentCarState(){
            return currentCarState;
        }
    }
}
