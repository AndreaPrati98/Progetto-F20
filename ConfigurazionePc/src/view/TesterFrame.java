package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;

import model.component.Component;

/**
 * 
 * @author Andrea
 * @author Irene R.
 * @author Stefano
 *
 */
@SuppressWarnings("serial")
public class TesterFrame extends JFrame {

	// si parte dal presupposto che sia unica la configurazione che stiamo creando

	public static enum TypeList {
		NEW_COMPONENT_LIST, ADDED_COMPONENT_LIST;
	}

	private Container content;
	private JPanel addingRemovingComponent;
	private JPanel managingConfiguration;
	private JSplitPane choiceOfComponents;
	private JButton addComponentButton;
	private JButton removeComponenButtont;
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

		content.setLayout(new BorderLayout());

		addingRemovingComponent = new JPanel();
		managingConfiguration = new JPanel();
		listModelCatalog = new DefaultListModel<String>();
		listModelAdded = new DefaultListModel<String>();
		componentJList = new JList(listModelCatalog);
		chosenJList = new JList<String>(listModelAdded);

		choiceOfComponents = this.createJSplitPane(this.createScrollPanel(componentJList),
				this.createScrollPanel(chosenJList));
		addingRemovingComponent.add(choiceOfComponents);

		addComponentButton = new JButton("AGGIUNGI COMPONENTE");
		removeComponenButtont = new JButton("RIMUOVI COMPONENTE");
		addingRemovingComponent.add(addComponentButton);
		addingRemovingComponent.add(removeComponenButtont);

		infoLabel = new JLabel("Qui visualizzerai le informazioni sulla configurazione che stai creando");
		managingConfiguration.add(infoLabel);

		content.add(addingRemovingComponent, BorderLayout.NORTH);
		content.add(managingConfiguration, BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

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
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pannello1, pannello2);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(150);

		// Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(80, 50);
		pannello1.setMinimumSize(minimumSize);
		pannello2.setMinimumSize(minimumSize);
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
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth / 2, screenHeight / 2);
		setLocation(screenWidth / 4, screenHeight / 4);

	}

	/**
	 * Add an element to a JList and refresh the JSplitPanel in which there is the
	 * list
	 * 
	 * @param s
	 * @param typePanel - type of panel in which there is the list we wanto to
	 */
	public void addElementJList(TesterFrame.TypeList typeList, String s) {

		// devo aggiungere il vettore di stringe, lo dichiaro, lo riempio e poi lo metto
		// dento alla JList

		String[] listData = null;
		if (typeList == TesterFrame.TypeList.NEW_COMPONENT_LIST) {
			bufferNewComponent.add(s);
			listData = new String[bufferNewComponent.size()];
			int i = 0;
			for (String elem : bufferNewComponent) {
				listData[i] = elem;
				i++;
			}
		} else if (typeList == TesterFrame.TypeList.ADDED_COMPONENT_LIST) {
			bufferComponent.add(s);
			listData = new String[bufferComponent.size()];
			int i = 0;
			for (String elem : bufferComponent) {
				listData[i] = elem;
				i++;
			}
		} else {
			throw new IllegalArgumentException("typeList this method isn't able to handle");
		}

		// questa sezione decide quale JList aggiornare
		if (typeList == TesterFrame.TypeList.NEW_COMPONENT_LIST) {
			componentJList.setListData(listData);
		} else if (typeList == TesterFrame.TypeList.ADDED_COMPONENT_LIST) {
			chosenJList.setListData(listData);
		} else {
			throw new IllegalArgumentException("typeList this method isn't able to handle");
		}

		addingRemovingComponent.remove(choiceOfComponents);
		choiceOfComponents = this.createJSplitPane(this.createScrollPanel(componentJList),
				this.createScrollPanel(chosenJList));
		addingRemovingComponent.add(choiceOfComponents);

		addingRemovingComponent.revalidate();
		addingRemovingComponent.repaint();

	}

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

	public JButton getRemoveComponenButtont() {
		return removeComponenButtont;
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

	
}
