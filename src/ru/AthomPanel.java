package ru;

import javax.swing.*;
import java.awt.*;

public class AthomPanel extends JPanel {
    //Component[] components;
    Model model = new Model();

    public AthomPanel() {
        super();
        //setLayout(null);
        setPreferredSize(new Dimension(model.W, model.H));
        inits();
        //model.calculate(getComponents());
    }

    public void inits(){

        this.removeAll();


        add(new Athom(30, 150 - (int) (model.sigma * 1.12)));
        add(new Athom(30, 150));
        add(new Athom(30 + (int) (model.sigma * 1.12), 150));
        add(new Athom(30, (int) (150 + model.sigma)));

        add(new Athom(150, 160));
        add(new Athom(125, 80));
        add(new Athom(175, 230));

        //components = getComponents();
        model.calculate(getComponents());

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        model.setConstrains(getWidth(), getHeight());
        Component[] components = getComponents();
        g.drawString(model.W + "," + model.H, getWidth() - 55, 14);
        g.drawString("E=" + model.epsilon + ", S=" + model.sigma, getWidth() / 2, 14);
        g.drawString("X " + getWidth(), getWidth() - 45, getHeight() - 8);
        g.drawString("Y " + getHeight(), 4, 14);
        g.drawString("0,0", 4, getHeight() - 8);

        for (int i = 0; i < components.length; i++) {
            int dr = components[i].getWidth() / 2;
            for (int j = i; j < components.length; j++) {
                if (i != j) {
                    int Xi = components[i].getLocation().x + dr;
                    int Xj = components[j].getLocation().x + dr;
                    int Yi = components[i].getLocation().y + dr;
                    int Yj = components[j].getLocation().y + dr;

                    Color oldcolor = g.getColor();
                    g.setColor(Color.LIGHT_GRAY);
                    g.drawLine(Xi, Yi, Xj, Yj);
                    g.setColor(oldcolor);

                    Font oldfont = g.getFont();
                    g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 9));
                    if (model.F != null)
                        g.drawString(String.format("%1$,.2f", model.F[i][j]), (Xi + Xj) / 2, (Yi + Yj) / 2);
                    if (model.R != null)
                        //g.drawString(String.format("%1$,.0f", model.R[i][j]), (Xi + Xj) / 2, (Yi + Yj) / 2 + 12);
                    g.setFont(oldfont);
                }
            }
        }

        for (int i = 0; i < components.length; i++) {
            components[i].paint(g);
        }

    }

    public void calc() {
        //model.animate(getComponents());
        model.calculate(getComponents());
        repaint();
    }

}
