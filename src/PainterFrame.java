//廖顥軒
//105403517
//資管3A
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

//廖顥軒
//105403517
//資管3A

public class PainterFrame extends JFrame{
	private final JComboBox <String> paintingToolsBox;
	private final String toolsArray[] = {"筆刷","直線","橢圓形","矩形","圓角矩形"}; 
	private final JPanel northPnl;
	private final JPanel radioButtonPnl;
	private final JPanel northRightPnl;
	private final JPanel northLeftPnl;
	private final JPanel canvasPnl;
	private final JRadioButton smallRadioButton;
	private final JRadioButton mediumRadioButton;
	private final JRadioButton largeRadioButton;
	private final ButtonGroup radioGroup;
	private final JLabel label1;
	private final JLabel label2;
	private final JLabel label3;
	private final JLabel statusBarLabel;
	private final JCheckBox fillCheckBox;
	private final JButton button1;
	private final JButton button2;
	private final JButton button3;
	
	
	public PainterFrame() {
		super("小畫家");
		BorderLayout layOut = new BorderLayout();
		setLayout(layOut);
		//welcome message
		
		//JCombobox
		paintingToolsBox = new JComboBox<String>(toolsArray);
		paintingToolsBox.addItemListener(
				new ItemListener()
				{
					@Override
					public void itemStateChanged(ItemEvent e)
					{
						if(e.getStateChange() == ItemEvent.SELECTED)
							System.out.printf("選擇  %s\r\n",toolsArray[paintingToolsBox.getSelectedIndex()]);
					}
					
				});
		
		//上面大Panel
		northPnl = new JPanel();
		GridLayout northLayout = new GridLayout(1,2);
		northPnl.setLayout(northLayout);
		//右上panel
		northRightPnl = new JPanel();
		GridLayout northRightLayout =  new GridLayout(2,3);
		northRightPnl.setLayout(northRightLayout);
		//左上panel
		northLeftPnl = new JPanel();
		GridLayout northLeftLayout = new GridLayout(1,3,10,10);
		northLeftPnl.setLayout(northLeftLayout);
		//radioButton panel
		radioButtonPnl = new JPanel();
		radioButtonPnl.setLayout(new GridLayout());
		//drawing Panwl
		canvasPnl = new JPanel();
		//labels
		label1 = new JLabel("繪圖工具");
		label2 = new JLabel("筆刷大小");
		label3 = new JLabel("填滿");
		
		//radioButtons
		smallRadioButton = new JRadioButton("小",false);
		mediumRadioButton = new JRadioButton("中",false);
		largeRadioButton = new JRadioButton("大",false);
		radioButtonPnl.add(smallRadioButton);
		radioButtonPnl.add(mediumRadioButton);
		radioButtonPnl.add(largeRadioButton);
		smallRadioButton.addItemListener(new RadioButtonHandler(smallRadioButton.getText()));
		mediumRadioButton.addItemListener(new RadioButtonHandler(mediumRadioButton.getText()));
		largeRadioButton.addItemListener(new RadioButtonHandler(largeRadioButton.getText()));
		//checkBox
		fillCheckBox = new JCheckBox();
		CheckBoxHandler checkBoxHandler= new CheckBoxHandler();
		fillCheckBox.addItemListener(checkBoxHandler);
		//buttons
		button1 = new JButton("前景色");
		button2 = new JButton("背景色");
		button3 = new JButton("清除畫面");
		
		button1.addActionListener(new ButtonHandler(button1.getText()));
		button2.addActionListener(new ButtonHandler(button2.getText()));
		button3.addActionListener(new ButtonHandler(button3.getText()));
		
		
		//create logical relationship between JRadioButtons
		radioGroup = new ButtonGroup();
		radioGroup.add(smallRadioButton);
		radioGroup.add(mediumRadioButton);
		radioGroup.add(largeRadioButton);
		
		//status bar,mouse handler
		statusBarLabel = new JLabel();
		MouseHandler mouseHandler = new MouseHandler();
		canvasPnl.addMouseMotionListener(mouseHandler);
		
		northRightPnl.add(label1);//1
		northRightPnl.add(label2);//2
		northRightPnl.add(label3);//3
		northRightPnl.add(paintingToolsBox);//4
		northRightPnl.add(radioButtonPnl);//5
		northRightPnl.add(fillCheckBox);//6
		northLeftPnl.add(button1);
		northLeftPnl.add(button2);
		northLeftPnl.add(button3);
		northPnl.add(northRightPnl);
		northPnl.add(northLeftPnl);
		add(northPnl,layOut.NORTH);
		add(canvasPnl,BorderLayout.CENTER);
		add(statusBarLabel,BorderLayout.SOUTH);
	}//end constructor
		
	private class RadioButtonHandler implements ItemListener {
		public String size;
		public RadioButtonHandler(String size) {
			this.size = size;
		}//end constructor
		@Override 
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED)
				System.out.printf("選擇  %s 筆刷\r\n", size);
		}//end itemStatechanged
	}//end radioButtonHandler
	
	private class CheckBoxHandler implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED)
				System.out.println("選擇填滿");
			else
				System.out.println("取消填滿");
		}//end itemStateChanged
		
	}//end CheckBoxHandler
	
	private class ButtonHandler implements ActionListener{
		public String text;
		public ButtonHandler(String text) {
			this.text = text;
		}//end constructor
		@Override 
		public void actionPerformed(ActionEvent e) {
			System.out.printf("點選  %s\r\n",text);
		}
	}//end ButtonHandler
	
	private class MouseHandler extends MouseAdapter{
		@Override
		public void mouseMoved(MouseEvent e) {
			statusBarLabel.setText((String.format("游標位置 [%d,%d]", e.getX(),e.getY())));
		}//end mouseMoved
	}//end mouseHandler
}//end class
