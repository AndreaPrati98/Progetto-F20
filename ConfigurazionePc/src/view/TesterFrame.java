package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
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
	
	public static enum typeList {
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

	// Queste due liste servono solo ad aggiornare le JList (non conosco altri modi)
	private LinkedList<String> bufferComponent;
	private LinkedList<String> bufferNewComponent;
	
	public TesterFrame(String title) {
		super(title);
		this.bufferComponent = new LinkedList<String>();
		
		this.initialPosition();
		content = this.getContentPane();
		
		content.setLayout(new BorderLayout());
		
		addingRemovingComponent = new JPanel();
		managingConfiguration = new JPanel();
		
		componentJList = new JList<String>();
		chosenJList = new JList<String>();
		
		choiceOfComponents = this.createJSplitPane(this.createScrollPanel(componentJList), this.createScrollPanel(chosenJList));
		addingRemovingComponent.add(choiceOfComponents);
		
		addComponentButton = new JButton("AGGIUNGI COMPONENTE");
		removeComponenButtont = new JButton("RIMUOVI COMPONENTE");
		addingRemovingComponent.add(addComponentButton);
		addingRemovingComponent.add(removeComponenButtont);
		
		infoLabel = new JLabel("Qui visualizzerai le informazioni sulla configurazione che stai creando");
		managingConfiguration.add(infoLabel);
		
		content.add(addingRemovingComponent, BorderLayout.NORTH);
		content.add(managingConfiguration, BorderLayout.SOUTH);
		
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
		
		//Create a split pane with the two scroll panes in it.
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pannello1, pannello2);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(150);

		//Provide minimum sizes for the two components in the split pane
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
		setSize(screenWidth/2,screenHeight/2);
		setLocation(screenWidth/4,screenHeight/4);
		
	}
	
	/**
	 * 
	 * @param s
	 * @param typePanel - type of panel in witch there is the list we wanto to 
	 */
	
	
	public void addElementJList(TesterFrame.typeList typeList, String s) {
		//TODO  
		bufferComponent.add(s);
		//devo aggiungere il vettore di stringe, lo dichiaro, lo riempio e poi lo metto dento alla JList 
		String[] listData = new String[bufferComponent.size()];
		
		int i = 0;
		for(String elem : bufferComponent) {	
			listData[i] = elem;
			i++;
			
		}
		
		if(typeList == TesterFrame.typeList.NEW_COMPONENT_LIST) {
			componentJList.setListData(listData);
		}else if(typeList == TesterFrame.typeList.ADDED_COMPONENT_LIST) {
			componentJList.setListData(listData);
		} else {
			throw new IllegalArgumentException("typeList this method isn't able to handle");
		}
		
		addingRemovingComponent.remove(choiceOfComponents);
		choiceOfComponents = this.createJSplitPane(this.createScrollPanel(componentJList), this.createScrollPanel(chosenJList));
		
		addingRemovingComponent.revalidate();
		addingRemovingComponent.repaint();
/*		
		scrollPaneIban = createScrollPanel(ibanList);
		
		//creo spazio per le info
		infoConto = createJTextArea();
		scrollPaneInfo = createScrollPanel(infoConto);
		
		//assemblo il JSplitPanel e lo aggiungo al pannello
		splitPane = createJSplitPane(scrollPaneIban, scrollPaneInfo);
		p0.add(splitPane);
		
		p0.revalidate();
		p0.repaint();
*/		
	}
	
}
