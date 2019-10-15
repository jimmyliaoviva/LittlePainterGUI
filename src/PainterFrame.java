//���V�a
//105403517
//���3A
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

//���V�a
//105403517
//���3A

public class PainterFrame extends JFrame{
	private final JComboBox <String> paintingToolsBox;
	private final String toolsArray[] = {"����","���u","����","�x��","�ꨤ�x��"}; 
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
		super("�p�e�a");
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
							System.out.printf("���  %s\r\n",toolsArray[paintingToolsBox.getSelectedIndex()]);
					}
					
				});
		
		//�W���jPanel
		northPnl = new JPanel();
		GridLayout northLayout = new GridLayout(1,2);
		northPnl.setLayout(northLayout);
		//�k�Wpanel
		northRightPnl = new JPanel();
		GridLayout northRightLayout =  new GridLayout(2,3);
		northRightPnl.setLayout(northRightLayout);
		//���Wpanel
		northLeftPnl = new JPanel();
		GridLayout northLeftLayout = new GridLayout(1,3,10,10);
		northLeftPnl.setLayout(northLeftLayout);
		//radioButton panel
		radioButtonPnl = new JPanel();
		radioButtonPnl.setLayout(new GridLayout());
		//drawing Panwl
		canvasPnl = new JPanel();
		//labels
		label1 = new JLabel("ø�Ϥu��");
		label2 = new JLabel("����j�p");
		label3 = new JLabel("��");
		
		//radioButtons
		smallRadioButton = new JRadioButton("�p",false);
		mediumRadioButton = new JRadioButton("��",false);
		largeRadioButton = new JRadioButton("�j",false);
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
		button1 = new JButton("�e����");
		button2 = new JButton("�I����");
		button3 = new JButton("�M���e��");
		
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
				System.out.printf("���  %s ����\r\n", size);
		}//end itemStatechanged
	}//end radioButtonHandler
	
	private class CheckBoxHandler implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED)
				System.out.println("��ܶ�");
			else
				System.out.println("������");
		}//end itemStateChanged
		
	}//end CheckBoxHandler
	
	private class ButtonHandler implements ActionListener{
		public String text;
		public ButtonHandler(String text) {
			this.text = text;
		}//end constructor
		@Override 
		public void actionPerformed(ActionEvent e) {
			System.out.printf("�I��  %s\r\n",text);
		}
	}//end ButtonHandler
	
	private class MouseHandler extends MouseAdapter{
		@Override
		public void mouseMoved(MouseEvent e) {
			statusBarLabel.setText((String.format("��Ц�m [%d,%d]", e.getX(),e.getY())));
		}//end mouseMoved
	}//end mouseHandler
}//end class
