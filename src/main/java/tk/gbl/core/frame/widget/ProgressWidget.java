package tk.gbl.core.frame.widget;

import javax.swing.*;
import java.awt.*;

/**
 * Date: 2017/7/8
 * Time: 13:48
 *
 * @author Tian.Dong
 */
public class ProgressWidget extends JPanel {
    Color borderColor;
    Color backgroundColor;
    Color foregroundColor;
    int current;
    int max;

    public ProgressWidget(Color color) {
        this.setBackground(color);
        this.setBounds(0, 0, 82, 14);
        this.setLayout(null);
    }

    public void refresh() {
        repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.clearRect(0, 0, 82, 14);
        graphics.setColor(backgroundColor);
        graphics.fillRect(0, 0, 82, 14);
        graphics.setColor(foregroundColor);
        int length = (int) (current * 1.0 / max * 82);
        graphics.fillRect(0, 0, length, 14);
        graphics.setColor(borderColor);
        graphics.drawRect(0, 0, 81, 13);
        graphics.setColor(Color.black);
//        graphics.drawString("1231231231", 0, 0);
        graphics.drawString(current + "/" + max, 0, 10);
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }
}
