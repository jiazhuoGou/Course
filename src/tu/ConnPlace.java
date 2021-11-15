package tu;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConnPlace extends JFrame {
    int x,y,x1,y1,x2,y2,m1,n1,m2,n2;
    Color col=new Color(200,200,200);
    private final Font font=new Font("华文琥珀",Font.BOLD,32);
    JPanel contentPanel=new MyJPanel();
    BasicStroke pen = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);
    Graphics2D g2=null;

    public  ConnPlace(){
        super();
        this.setSize(600, 400);
        contentPanel.setBackground(new Color(0,0,0));
        setContentPane(contentPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("客运连线实验");


        addMouseListener(new MouseAdapter() {         //继承适配器MouseAdapter，重写单击方法
            public void mouseClicked(MouseEvent e) {   // 发生单击事件时被触发

                x=e.getX();
                y=e.getY();
                if((x-150)*(x-150)+(y-100)*(y-100)<=2500)
                {x1=200;y1=100;m1=400;n1=100;
                    x2=150;y2=150;m2=250;n2=250;

                }

                else if((x-450)*(x-450)+(y-100)*(y-100)<=2500)
                {x1=200;y1=100;m1=400;n1=100;
                    x2=450;y2=150;m2=350;n2=250;
                }
                else if((x-300)*(x-300)+(y-250)*(y-250)<=2500)
                {x1=150;y1=150;m1=250;n1=250;
                    x2=450;y2=150;m2=350;n2=250;
                }
                else
                {x1=0;y1=0;m1=0;n1=0;
                    x2=0;y2=0;m2=0;n2=0;
                };

                contentPanel.repaint();
            }

        });
    }

    public static void main(String[] args) {
        new ConnPlace().setVisible(true);
    }


    class MyJPanel extends JPanel {

        public void paint(Graphics g) {
            super.paint(g);
            g2 = (Graphics2D)g;
            g2.setColor(col);
            g2.setFont(font);
            g2.setStroke(pen);
            g2.drawOval(100,50,  100, 100);		// 绘制第1个圆形
            g2.drawOval(400,50, 100, 100);		// 绘制第2个圆形
            g2.drawOval(250,200,  100, 100); 	// 绘制第3个圆形
            g2.drawString("北京", 118, 110);
            g2.drawString("上海", 418, 110);
            g2.drawString("广州", 268, 260);
            g2.drawLine(x1,y1,m1,n1);
            g2.drawLine(x2,y2,m2,n2);
        }
    }


}
