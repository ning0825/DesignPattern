
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;

/**
 * 中介模式
 * 
 * ## 定义
 * 定义一个单独的中介对象，来处理一组对象之间的交互。将这组对象之间的交互委派给避免对象直接交互（类比负责航空管制的塔台）
 * 
 * ## 应用场景
 * 一些对象和其他对象紧密耦合时，可以将对象间的关系抽取成单独的类
 */
public class Mediator {
    public static void main(String[] args) {
        UIControl.init();
    }

    private interface IMeditator {
        void handleEvent(Control componentView, String event);
    }

    /**
     * 所有UI组件都跟LandingPageDialog交互
     * 简化了控件之间的交互，但中介类可能变成庞大的“上帝类”
     */
    private static class LandingPageDialog implements IMeditator {
        private Button loginButton;
        private Button regButton;
        private TextField usernameInput;
        private TextField passwordInput;

        @Override
        public void handleEvent(Control control, String event) {
            if (control.equals(loginButton)) {
                String username = usernameInput.getText();
                String password = passwordInput.getText();
                // 检验数据及业务处理
                return;
            }

            if (control.equals(regButton)) {
                String username = usernameInput.getText();
                String password = passwordInput.getText();
                // 检验数据及业务处理
                return;
            }
        }

        public void setLoginButton(Button button) {
            this.loginButton = button;
        }

        public void setPasswordButton(Button button) {
            this.regButton = button;
        }
    }

    private static class UIControl {
        public static void init() {
            Button loginButton = new Button("login");
            Button regButton = new Button("register");

            LandingPageDialog landingPageDialog = new LandingPageDialog();
            landingPageDialog.setLoginButton(loginButton);
            landingPageDialog.setPasswordButton(regButton);

            loginButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    landingPageDialog.handleEvent(loginButton, "click");
                }
            });

            regButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    landingPageDialog.handleEvent(regButton, "reg");
                }
            });
        }
    }
}
