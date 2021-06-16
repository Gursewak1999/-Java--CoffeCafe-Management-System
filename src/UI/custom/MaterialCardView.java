package UI.custom;

import de.craften.ui.swingmaterial.MaterialPanel;
import helper.ColorUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class MaterialCardView extends JPanel {

    private int _width = 0, _height = 0;
    private int _margin = 12, _padding = 12;

    int shadow_width = 0, shadow_height = 0;
    int shadow_x = 0, shadow_y = 0;

    int background_width = 0, background_height = 0;
    int background_x = 0, background_y = 0;

    int content_width = 0, content_height = 0;
    int content_x = 0, content_y = 0;

    int card_radius = 8, shadow_radius = 8;

    private ShadowBackground shadow;
    private CardBackground card;
    public JPanel content_panel;

    public MaterialCardView(int width, int height) {

        calculate(width, height);
        shadow = new ShadowBackground();
        this.card = new CardBackground();
        content_panel = new JPanel();

        this.setBackground(new Color(0, 0, 0, 0));
        setSizes(width, height);
        addTransparency();

        this.setLayout(null);
        shadow.setLayout(null);
        card.setLayout(null);

        this.add(shadow);
        shadow.add(card);
        card.add(content_panel);

    }

    public int getInnerWidth(){
        return content_width;
    }
    public int getInnerHeight(){
        return content_height;
    }

    private void calculate(int width, int height) {
        _width = width;
        _height = height;
        _margin = 12;
        _padding = 12;

        shadow_width = _width - _margin;
        shadow_height = _height - _margin;
        shadow_x = _margin / 2;
        shadow_y = _margin / 2;

        background_width = shadow_width - 2;
        background_height = shadow_height - 2;
        background_x = -1;
        background_y = 0;

        content_width = background_width - _padding;
        content_height = background_height - _padding;
        content_x = _padding / 2;
        content_y = _padding / 2;
    }

    private void addTransparency() {
        this.content_panel.setOpaque(false);
        this.content_panel.setBackground(new Color(200, 200, 200, 25));
    }

    private void setSizes(int width, int height) {
        this.setBounds(0, 0, width, height);
        this.setPreferredSize(new Dimension(width, height));
        this.shadow.setBounds(
                shadow_x+1,
                shadow_y,
                shadow_width-1,
                shadow_height-1);
        this.shadow.setPreferredSize(new Dimension(shadow_width, shadow_height));
        this.card.setBounds(
                background_x,
                background_y,
                background_width,
                background_height);
        this.card.setPreferredSize(new Dimension(background_width, background_height));
        this.content_panel.setBounds(
                content_x,
                content_y,
                content_width,
                content_height);
        this.content_panel.setPreferredSize(new Dimension(content_width, content_height));
    }

    public void setCardBackground(Color color) {
        card.setBackground(color);
    }

    public class ShadowBackground extends MaterialPanel {

        public ShadowBackground() {
            super();
            this.setOpaque(false);
            this.setBackground(ColorUtils.shadowColor);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Dimension arcs = new Dimension(shadow_radius, shadow_radius);
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //Draws the rounded opaque panel
            graphics.setColor(getBackground());
            graphics.fillRoundRect(shadow_x, shadow_y, getWidth() - shadow_radius - 1, getHeight() - shadow_radius - 1, arcs.width, arcs.height);//paint background

        }

        @Override
        public void setPreferredSize(Dimension preferredSize) {
            super.setPreferredSize(preferredSize);
            revalidate();
        }
    }

    public class CardBackground extends JPanel {

        public CardBackground() {
            super();
            this.setOpaque(false);
            this.setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Dimension arcs = new Dimension(card_radius, card_radius);
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //Draws the rounded opaque panel with borders.
            graphics.setColor(getBackground());
            RoundRectangle2D curved_rect = new RoundRectangle2D.Float(shadow_x, shadow_y, getWidth() - card_radius - 1, getHeight() - card_radius - 1, arcs.width, arcs.height);
            graphics.fill(curved_rect);//paint background

        }

        @Override
        public void setPreferredSize(Dimension preferredSize) {
            super.setPreferredSize(preferredSize);
            revalidate();
        }
    }

    public void addContent(JComponent component){
        this.content_panel.add(component);
    }

}
