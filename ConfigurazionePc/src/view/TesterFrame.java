package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

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
	
	private Container content;
	private JPanel addingRemovingComponent;
	private JPanel managingConfigurations;
	private JSplitPane choiceOfComponents;
	private JButton addComponentButton;
	private JButton removeComponenButtont;
	private JLabel infoLabel;
	private JList<String> componentJList;
	private JList<String> chosenJList;
	
	public TesterFrame(String title) {
		super(title);
		this.initialPosition();
		content = this.getContentPane();
		
		content.setLayout(new BorderLayout());
		
		addingRemovingComponent = new JPanel();
		managingConfigurations = new JPanel();
		
		componentJList = new JList<String>();
		chosenJList = new JList<String>();
		
		choiceOfComponents = this.createJSplitPane(this.createScrollPanel(componentJList), this.createScrollPanel(chosenJList));
		addingRemovingComponent.add(choiceOfComponents);
		
		addComponentButton = new JButton("AGGIUNGI COMPONENTE");
		removeComponenButtont = new JButton("RIMUOVI COMPONENTE");
		addingRemovingComponent.add(addComponentButton);
		addingRemovingComponent.add(removeComponenButtont);
		
		infoLabel = new JLabel("Qui visualizzerai le informazioni sulla configurazione che stai creando");
		managingConfigurations.add(infoLabel);
		
		content.add(addingRemovingComponent, BorderLayout.NORTH);
		content.add(managingConfigurations, BorderLayout.SOUTH);
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
	 */
	/*
	public void addElementJList(String s) {
		//TODO  
		lm.add(s);
		//devo aggiungere il vettore di stringe, lo dichiaro, lo riempio e poi lo metto dento alla JList 
		String[] listData = new String[lm.size()];
		
		int i = 0;
		for(String elem : lm) {
			
			listData[i] = elem;
			i++;
			
		}
		
		
		ibanList.setListData(listData);
		
		p0.remove(splitPane);
		
		scrollPaneIban = createScrollPanel(ibanList);
		
		//creo spazio per le info
		infoConto = createJTextArea();
		scrollPaneInfo = createScrollPanel(infoConto);
		
		//assemblo il JSplitPanel e lo aggiungo al pannello
		splitPane = createJSplitPane(scrollPaneIban, scrollPaneInfo);
		p0.add(splitPane);
		
		p0.revalidate();
		p0.repaint();
		
	}
	*/
	
	
	
}
