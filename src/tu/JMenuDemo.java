package tu;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.*;

public class JMenuDemo extends JMenuBar {

    public JMenuDemo()
    {
        add(createAlgoMenu());    //添加“算法”菜单
        setVisible(true);
    }


    //定义“算法”菜单
    public JMenu createAlgoMenu()
    {
        JMenu menu=new JMenu("算法");
        menu.setMnemonic(KeyEvent.VK_F);    //设置快速访问符

        JMenuItem item=new JMenuItem("最小生成树(N)",KeyEvent.VK_N);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        menu.add(item);

        item=new JMenuItem("最短路径(O)",KeyEvent.VK_O);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        menu.add(item);

        item=new JMenuItem("最大匹配(S)",KeyEvent.VK_S);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        menu.add(item);
        menu.addSeparator();

        return menu;
    }
}
