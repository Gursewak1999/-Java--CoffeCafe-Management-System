package helper;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static final int max_height = 768;
    public static final int max_width = 1366;

    public static String generateId() {
        //Date dNow = new Date();
        //2020-11-28 05:56:25
        //SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        //return ft.format(dNow);
        return String.valueOf(System.currentTimeMillis());
    }

    public static JWindow centerWindow(JWindow window) {
        window.setLocation((max_width - window.getWidth()) / 2, (max_height - window.getHeight()) / 2);
        return window;
    }

    public static JFrame centerWindow(JFrame window) {
        window.setLocation((max_width - window.getWidth()) / 2, (max_height - window.getHeight()) / 2);
        return window;
    }

    public static JComponent centerWindow(JComponent window) {
        window.setLocation((max_width - window.getWidth()) / 2, (max_height - window.getHeight()) / 2);
        return window;
    }

    public static String getMd5(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            StringBuilder hashtext = new StringBuilder(no.toString(16));
            while (hashtext.length() < 32) {
                hashtext.insert(0, "0");
            }
            return hashtext.toString();
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validatePhone(String mobile) {
        Pattern p = Pattern.compile("[6-9][0-9]{8}");
        Matcher m = p.matcher(mobile);
        return (m.find() && m.group().equals(mobile));
    }

    public static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }

    public static boolean validateEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    public static void logSizes(String name, JComponent content) {
        System.out.println(name + " = " + content.getWidth() + " " + content.getHeight() + " " + content.getX() + " " + content.getY());
    }

    public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        // This is what we want, but it only does hard-clipping, i.e. aliasing
        // g2.setClip(new RoundRectangle2D ...)

        // so instead fake soft-clipping by first drawing the desired clip shape
        // in fully opaque white with antialiasing enabled...
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

        // ... then compositing the image on top,
        // using the white shape from above as alpha source
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
    }

    public static String convertToDate(String timestamp) {

        Timestamp ts = new Timestamp(Long.parseLong(timestamp));
        Date date = new Date(ts.getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        sdf.setTimeZone(TimeZone.getDefault());

        return sdf.format(calendar.getTime());
    }

    public static String convertToDay(String timestamp) {

        Timestamp ts = new Timestamp(Long.parseLong(timestamp));
        Date date = new Date(ts.getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        sdf.setTimeZone(TimeZone.getDefault());

        return sdf.format(calendar.getTime());
    }

    public static String convertToMonth(String timestamp) {

        Timestamp ts = new Timestamp(Long.parseLong(timestamp));
        Date date = new Date(ts.getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        sdf.setTimeZone(TimeZone.getDefault());

        return sdf.format(calendar.getTime());
    }

    public static String convertToYear(String timestamp) {

        Timestamp ts = new Timestamp(Long.parseLong(timestamp));
        Date date = new Date(ts.getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        sdf.setTimeZone(TimeZone.getDefault());

        return sdf.format(calendar.getTime());
    }

    public static boolean validatePassword(String password) {
        return password.length() >= 8;
    }
}
