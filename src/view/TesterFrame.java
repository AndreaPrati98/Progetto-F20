package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.LinkedList;
//import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
//import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;

//import model.component.Component;

/**
 * 
 * @author Andrea
 * @author Irene R.
 * @author Stefano
 *
 */
@SuppressWarnings("serial")
public class TesterFrame extends JFrame {

	private Container content;
	private JPanel addingRemovingComponent;
	private JPanel terminal;
	private JPanel managingConfiguration;
	private JSplitPane choiceOfComponents;
	private JButton addComponentButton;
	private JButton checkButton;
	private JButton removeComponentButtont;
	private JLabel infoLabel;
	private JList<String> componentJList;
	private JList<String> chosenJList;
	private DefaultListModel<String> listModelCatalog;
	private DefaultListModel<String> listModelAdded;

	// Queste due liste servono solo ad aggiornare le JList (non conosco altri modi)
	private LinkedList<String> bufferComponent;
	private LinkedList<String> bufferNewComponent;

	public TesterFrame(String title) {
		super(title);
		this.bufferComponent = new LinkedList<String>();
		this.bufferNewComponent = new LinkedList<String>();
		this.initialPosition();
		
		content = this.getContentPane();
		content.setLayout(new GridLayout(2,1));

		addingRemovingComponent = new JPanel();
		terminal=new JPanel();
		managingConfiguration = new JPanel();
		listModelCatalog = new DefaultListModel<String>();
		listModelAdded = new DefaultListModel<String>();
		componentJList = new JList<String>(listModelCatalog);
		chosenJList = new JList<String>(listModelAdded);
		addComponentButton = new JButton("AGGIUNGI COMPONENTE");
		checkButton = new JButton("CHECK");
		removeComponentButtont = new JButton("RIMUOVI COMPONENTE");
		infoLabel = new JLabel("Qui visualizzerai le informazioni sulla configurazione che stai creando");
		
		
		choiceOfComponents = this.createJSplitPane(this.createScrollPanel(componentJList),this.createScrollPanel(chosenJList));
		
		graphicsfunction();
		
		addingRemovingComponent.setLayout(new GridLayout(3,1));
		addingRemovingComponent.add(addComponentButton);
		addingRemovingComponent.add(removeComponentButtont);
		addingRemovingComponent.add(checkButton);
		
		managingConfiguration.setLayout(new GridLayout(1,2));
		managingConfiguration.add(addingRemovingComponent);
		terminal.add(infoLabel);
		managingConfiguration.add(terminal);
		
		add(choiceOfComponents);
		add(managingConfiguration);

		
		

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}
	/**
	 *
	 */
	private void graphicsfunction() {
		// TODO Auto-generated method stub
		terminal.setBackground(Color.BLACK);
		infoLabel.setForeground(Color.green);
		infoLabel.setFont(new Font("Arial",22,16));
		
		addComponentButton.setBackground(Color.WHITE);
		addComponentButton.setForeground(Color.BLACK);
		addComponentButton.setFont(new Font("Arial",22,16));
		
		checkButton.setBackground(Color.WHITE);
		checkButton.setForeground(Color.BLACK);
		checkButton.setFont(new Font("Arial",22,16));
		
		removeComponentButtont.setBackground(Color.WHITE);
		removeComponentButtont.setForeground(Color.BLACK);
		removeComponentButtont.setFont(new Font("Arial",22,16));
		
		
	}

	/**
	 * Create a JSplitPanel using two JScrollPane
	 * 
	 * @param pannello1
	 * @param pannello2
	 * @return
	 * 
	 * @see JScrollPane
	 * @see JSplitPane
	 */
	private JSplitPane createJSplitPane(JScrollPane pannello1, JScrollPane pannello2) {

		// Create a split pane with the two scroll panes in it.
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pannello1, pannello2);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(screenSize.width/2);

//		// Provide minimum sizes for the two components in the split pane
//		Dimension minimumSize = new Dimension(80, 50);
//		pannello1.setMinimumSize(minimumSize);
//		pannello2.setMinimumSize(minimumSize);
		return splitPane;

	}

	/**
	 * Make a ScrollablePanel with a JList
	 * 
	 * @param listText
	 * @return the ScrollPanel
	 * 
	 * @see JSplitPane
	 * @see JList
	 */
	private JScrollPane createScrollPanel(JList<String> listText) {

		JScrollPane scrollPane = new JScrollPane(listText);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(250, 180));
		return scrollPane;

	}

	private void initialPosition() {

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		setMaximumSize(screenSize);
		setExtendedState(MAXIMIZED_BOTH);

	}

	/**
	 * Add an element to a JList and refresh the JSplitPanel in which there is the
	 * list
	 * 
	 * @param s
	 * @param typePanel - type of panel in which there is the list we wanto to
	 */
//	public void addElementJList(TesterFrame.TypeList typeList, String s) {
//
//		// devo aggiungere il vettore di stringe, lo dichiaro, lo riempio e poi lo metto
//		// dento alla JList
//
//		String[] listData = null;
//		if (typeList == TesterFrame.TypeList.NEW_COMPONENT_LIST) {
//			bufferNewComponent.add(s);
//			listData = new String[bufferNewComponent.size()];
//			int i = 0;
//			for (String elem : bufferNewComponent) {
//				listData[i] = elem;
//				i++;
//			}
//		} else if (typeList == TesterFrame.TypeList.ADDED_COMPONENT_LIST) {
//			bufferComponent.add(s);
//			listData = new String[bufferComponent.size()];
//			int i = 0;
//			for (String elem : bufferComponent) {
//				listData[i] = elem;
//				i++;
//			}
//		} else {
//			throw new IllegalArgumentException("typeList this method isn't able to handle");
//		}
//
//		// questa sezione decide quale JList aggiornare
//		if (typeList == TesterFrame.TypeList.NEW_COMPONENT_LIST) {
//			componentJList.setListData(listData);
//		} else if (typeList == TesterFrame.TypeList.ADDED_COMPONENT_LIST) {
//			chosenJList.setListData(listData);
//		} else {
//			throw new IllegalArgumentException("typeList this method isn't able to handle");
//		}
//
//		addingRemovingComponent.remove(choiceOfComponents);
//		choiceOfComponents = this.createJSplitPane(this.createScrollPanel(componentJList),
//				this.createScrollPanel(chosenJList));
//		addingRemovingComponent.add(choiceOfComponents);
//
//		addingRemovingComponent.revalidate();
//		addingRemovingComponent.repaint();
//
//	}

	public JPanel getAddingRemovingComponent() {
		return addingRemovingComponent;
	}

	public JPanel getManagingConfiguration() {
		return managingConfiguration;
	}

	public JSplitPane getChoiceOfComponents() {
		return choiceOfComponents;
	}

	public JButton getAddComponentButton() {
		return addComponentButton;
	}

	public JButton getRemoveComponentButtont() {
		return removeComponentButtont;
	}

	public JLabel getInfoLabel() {
		return infoLabel;
	}

	public JList<String> getComponentJList() {
		return componentJList;
	}
	
	public DefaultListModel<String> getListModelCatalog() {
		return listModelCatalog;
	}

	public JList<String> getChosenJList() {
		return chosenJList;
	}

	public DefaultListModel<String> getListModelAdded() {
		return listModelAdded;
	}
	public JButton getCheckButton() {
		return checkButton;
	}

	
}
