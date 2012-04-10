package sg.edu.nus.sudoku.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import sg.edu.nus.sudoku.controller.MainController;
import sg.edu.nus.sudoku.halloffame.Difficulty;
import sg.edu.nus.sudoku.halloffame.Record;
import sg.edu.nus.sudoku.util.Time;

public class HallOfFamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	final private static int SCROLLPANE_PADDING = 123;
	private JLabel lblHallOfFame;
	
	private MainController controller;
	private Hashtable<Difficulty,DefaultListModel>  listModels;
	private Hashtable<Difficulty,LinkedList<Record>> hallOfFameData;
	
	HallOfFamePanel() {
		listModels = new Hashtable<Difficulty,DefaultListModel>();
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;

		lblHallOfFame = new JLabel("Hall Of Fame");
		lblHallOfFame.setHorizontalAlignment(SwingConstants.CENTER);
		lblHallOfFame.setFont(Resources.CELL_FONT);
		lblHallOfFame.setIcon(Resources.HALLOFFAME_ICON);
		c.gridy = 0;
		c.weighty=0.5;
		c.anchor = GridBagConstraints.PAGE_START;
		
		this.add(lblHallOfFame,c);
				
		ScoreRenderer sr = new ScoreRenderer();

		Difficulty[] values = Difficulty.values();
		int gridycount =1;
		for(int i=0;i< values.length;i++){
			addLabel(values[i],c,gridycount);
			gridycount++;
			addTopScorerList(values[i], c, gridycount,sr);
			gridycount++;
		}

	}
	
	public void initializeHallOfFame(Hashtable<Difficulty, LinkedList<Record>> ht) {
		hallOfFameData = ht;
		Difficulty values[] = Difficulty.values();
		for(int j=0;j<values.length;j++){
			DefaultListModel dlm = listModels.get(values[j]);
			LinkedList<Record> records = ht.get(values[j]);
			for(Record r:records) {
				dlm.addElement(r);
			}
		}

	}
	
	private void addLabel(Difficulty d,GridBagConstraints c,int gridy){
		JLabel l = new JLabel(d.name());
		c.fill = GridBagConstraints.HORIZONTAL;
		l.setFont(Resources.STANDARD_FONT_UNDERLINE);
		c.ipady = 0;
		c.gridy = gridy;
		this.add(l,c);
	}

	private void addTopScorerList(Difficulty d, GridBagConstraints c, int gridy, ScoreRenderer r){
		JScrollPane p  = new JScrollPane(createTopScorerList(d, r));
		p.setBorder(BorderFactory.createEmptyBorder());
		c.gridy = gridy;
		c.ipady = SCROLLPANE_PADDING;
		this.add(p,c);
	}

	private JList createTopScorerList(Difficulty d,ScoreRenderer r){
		DefaultListModel dlm = new DefaultListModel();
		final JList list = new JList(dlm);
		list.setCellRenderer(r);
		listModels.put(d,dlm);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					Record r = (Record)list.getSelectedValue();
					if (!(r.getGameString()==null))
					controller.loadGameFromGameString(r.getGameString(),null);
				}
			}
		});
		return list;
	}

	public void updateHighScore(Difficulty d) {
		LinkedList<Record> records = hallOfFameData.get(d);
		DefaultListModel displayRecords = listModels.get(d);
		Iterator<Record> itr = records.iterator();
		int i = 0;
		Record dataRecord = null;
		while(itr.hasNext()) {
			dataRecord = itr.next();
			Record displayRecord = (Record)displayRecords.get(i);
			if(dataRecord!=displayRecord) break;
			i++;
		}
		do {   // copy over everything once difference spotted.
			displayRecords.set(i, dataRecord);
			dataRecord = itr.next();
			i++;
		} while(itr.hasNext());
	}

	private static class ScoreRenderer implements ListCellRenderer {
		
		private Hashtable<Record,JPanel> panelCache;
		private Hashtable<Record,JLabel> lblPosCache;
		private Hashtable<Record,JLabel> lblNameCache;
		private Hashtable<Record,JLabel> lblTimeCache;
		public ScoreRenderer() {
			panelCache = new Hashtable<Record, JPanel>();
			lblPosCache = new Hashtable<Record, JLabel>();
			lblNameCache = new Hashtable<Record, JLabel>();
			lblTimeCache = new Hashtable<Record, JLabel>();
		}
		
		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			Record r = (Record)value;
			JPanel p = panelCache.get(r);
			if(p==null) {
				p = createListItem(Integer.toString(index+1),isSelected,r);
				panelCache.put(r, p);
			}
			if(isSelected & cellHasFocus) lblNameCache.get(r).setForeground(Color.red);
			else lblNameCache.get(r).setForeground(Color.black);
			lblPosCache.get(r).setText(Integer.toString(index+1));
			return p;
		}
		private JPanel createListItem(String pos,boolean isSelected, Record r){
			JPanel p = new JPanel(new GridBagLayout());
			GridBagConstraints c= new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.ipady = 0;
			c.gridy = 0;
			c.weightx = 0.5;
		
			JLabel l = new JLabel(pos);
			l.setHorizontalAlignment(SwingConstants.LEFT);
			c.gridwidth = 1;
			p.add(l,c);
			lblPosCache.put(r, l);
			
			l = new JLabel(r.getPlayerName());
			c.gridwidth = 3;
			p.add(l,c );
			lblNameCache.put(r, l);

			l = new JLabel(Time.formatTime(r.getTime()));
			c.gridwidth = 1;
			p.add(l);
			lblTimeCache.put(r, l);
			
			return p;

		}
	}
	
	public void setController(MainController controller) {
		this.controller = controller;
	}
}



