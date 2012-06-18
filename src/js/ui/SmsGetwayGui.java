package js.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class SmsGetwayGui {
	private static SmsGetwayGui gui;
	private Thread thread;
	
	private SmsGetwayGui() {
	}
	
	public static SmsGetwayGui getInstance() {
		if (gui == null) {
			gui = new SmsGetwayGui();
		}
		return gui;
	}
	
	public void createGui() {
		// create a simple console window
        JFrame frame = new JFrame("My Sandbox");
        frame.addWindowListener(new WindowEventHandler());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setBounds(25, 25, 450, 450);
        
        JTextArea area = new JTextArea();
        area.setFont(new Font(Font.SANS_SERIF, 0, 12));
        area.setBounds(frame.getBounds());
        area.setEditable(false);
        
        JMenuBar bar = new JMenuBar();
        JMenu mnuFile = new JMenu("File");
        
        MyActionListener actionListener = new MyActionListener();
        
        JMenuItem mnuExit = new JMenuItem("Exit");
        mnuExit.setActionCommand(mnuExit.getName());
        mnuExit.addActionListener(actionListener);
        
        mnuFile.add(mnuExit);
        
        bar.add(mnuFile);
        bar.setVisible(true);
        
        area.append("Initializing my sand box...\n");
        area.append("Sandbox initialized.");
        
        frame.setJMenuBar(bar);
        frame.add(area);
        frame.setVisible(true);

	}
	
    class WindowEventHandler extends WindowAdapter {
        public void windowOpened(WindowEvent paramWindowEvent) {
//            thread = new Thread(new SmsGatewayThread());
//            thread.start();
        }
        
        public void windowClosing(WindowEvent evt) {
//            thread.interrupt();
            System.exit(0);
        }
    }
    
    class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent paramActionEvent) {
            
            if (paramActionEvent.getActionCommand().equalsIgnoreCase("Exit")) {
//            	thread.interrupt();
                System.exit(0);
            }
            
        }
    }
    
    public static void main(String [] args) {
    	SmsGetwayGui.getInstance().createGui();
    }

}
