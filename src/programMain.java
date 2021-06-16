import helper.InstanceHelper;

public class programMain {

    public static void main(String[] args) {
        load();
    }

    private static void load() {
        init();
        InstanceHelper.startApp();
        //InstanceHelper.gotoDashboard();
    }

    private static void init() {
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
    }

}
