package helper;

import DataClasses.CoffeeData;
import DataClasses.Orders;
import UI.Fragments.*;
import UI.DashboardUI;
import UI.LoginUI;
import UI.StrippedUI;
import de.craften.ui.swingmaterial.MaterialWindow;
import javax.swing.*;
import java.awt.*;

public class InstanceHelper {

    public static final String FRAGMENT_NOTICEBOARD = "notice_board_fragment";
    public static final String FRAGMENT_DASHBOARD_HOME = "dashboard_home_fragment";
    public static final String FRAGMENT_LIST_COFFEE = "list_coffee_fragment";
    public static final String FRAGMENT_TARGET = "target_fragment";
    public static final String FRAGMENT_TODAYS_SPECIAL = "todays_special_fragment";
    public static final String FRAGMENT_ORDERS = "orders_fragment";
    public static final String FRAGMENT_ADD_ORDERS = "add_orders_fragment";
    public static final String FRAGMENT_ADD_COFFEE = "add_coffee_fragment";

    private static StrippedUI mainUI;
    private static DashboardUI dashboardUI;
    private static LoginUI loginUI;

    private static final int
            login_width = Utils.max_width / 4,
            login_height = Utils.max_height / 2;
    public static final int
            dashboard_width = Utils.max_width * 4 / 5,
            dashboard_height = Utils.max_height * 4 / 5;
    public static final int
            fragment_width = dashboard_width - dashboard_width / 5,
            fragment_height = dashboard_height;

    private static MaterialWindow mainWindow;
    private static DashboardContent dashboardContent;
    private static NoticeboardFragment noticeboardFragment;
    private static JPanel selectedDasboardPanel;

    private static boolean isDashboardPage = false;
    private static boolean isLoginPage = false;
    private static ListCoffeeFragment listCoffeeFragment;
    private static TargetFragment tagetFragment;
    private static TodaySpecialFragment todaysSpecialFragment;
    private static AddOrderFragment addOrderFragment;
    private static ShowOrdersFragment showOrdersFragment;
    private static AddCoffeeFragment addCoffeeFragment;

    private static JLabel snackbar;
    public static boolean doEdit = false;
    public static CoffeeData data;
    public static Orders order;

    public InstanceHelper() {
        init();
    }

    public static void init() {
        isLoginPage = true;
        isDashboardPage = false;
        mainUI = new StrippedUI(login_width, login_height);
        dashboardUI = new DashboardUI(dashboard_width, dashboard_height);
        loginUI = new LoginUI(login_width, login_height);

        dashboardContent = new DashboardContent(dashboardUI, fragment_width, fragment_height);
        noticeboardFragment = new NoticeboardFragment(fragment_width, fragment_height);
        tagetFragment = new TargetFragment(fragment_width, fragment_height);
        addOrderFragment = new AddOrderFragment(fragment_width, fragment_height);
        addCoffeeFragment = new AddCoffeeFragment(fragment_width, fragment_height);

    }

    public static void startApp() {
        mainWindow = new MaterialWindow();
        mainWindow.setWindowSize(Utils.max_width / 4, Utils.max_height / 2);

        if (mainUI == null)
            init();
        mainUI.setContentPane(loginUI);

        mainWindow.add(mainUI);
        Utils.centerWindow(mainWindow);
        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void testApp(JPanel panel) {
        mainWindow = new MaterialWindow();
        mainWindow.setWindowSize( Utils.max_height / 2,Utils.max_width / 4);

        if (mainUI == null)
            init();
        mainUI.setContentPane(panel);

        mainWindow.add(mainUI);
        Utils.centerWindow(mainWindow);
        mainWindow.setVisible(true);
    }

    public static void gotoDashboard() {
        isDashboardPage = true;
        isLoginPage = false;
        mainWindow.remove(mainUI);
        mainUI = new StrippedUI(dashboard_width + 30, dashboard_height);
        dashboardUI.setWindow_pane(InstanceHelper.selectedDasboardPanel, dashboardContent);
        InstanceHelper.selectedDasboardPanel = dashboardContent;
        mainUI.setContentPane(dashboardUI);
        mainWindow.add(mainUI);
        mainWindow.setWindowSize(new Dimension(dashboard_width + 30, dashboard_height));
        //mainWindow.pack();
        Utils.centerWindow(mainWindow);
        mainWindow.setVisible(true);
        //  mainWindow.revalidate();
    }

    public static void gotoFragment(String name) {

        JPanel panel = new JPanel();
        switch (name) {
            case FRAGMENT_NOTICEBOARD:
                panel = noticeboardFragment;
                break;
            case FRAGMENT_DASHBOARD_HOME:
                panel = new DashboardContent(dashboardUI, fragment_width, fragment_height);
                break;
            case InstanceHelper.FRAGMENT_LIST_COFFEE:
                panel =  new ListCoffeeFragment(fragment_width, fragment_height);
                break;
            case InstanceHelper.FRAGMENT_TARGET:
                panel = tagetFragment;
                break;
            case InstanceHelper.FRAGMENT_TODAYS_SPECIAL:
                panel = new TodaySpecialFragment(fragment_width, fragment_height);
                break;
            case InstanceHelper.FRAGMENT_ORDERS:
                panel = new ShowOrdersFragment(fragment_width, fragment_height);
                break;
            case InstanceHelper.FRAGMENT_ADD_ORDERS:
                if (order != null)
                    panel = new AddOrderFragment(fragment_width, fragment_height);
                else
                    panel = addOrderFragment;
                break;
            case InstanceHelper.FRAGMENT_ADD_COFFEE:
                panel = new AddCoffeeFragment(fragment_width, fragment_height);
                break;
        }
        if (isDashboardPage) {
            dashboardUI.setWindow_pane(InstanceHelper.selectedDasboardPanel, panel);
            InstanceHelper.selectedDasboardPanel = panel;
            dashboardUI.revalidate();
        }
    }

    public static void setData(CoffeeData dat, boolean toEdit) {
        data = dat;
        doEdit = toEdit;
    }

    public static void setData(Orders dat, boolean toEdit) {
        order = dat;
        doEdit = toEdit;
    }
}
