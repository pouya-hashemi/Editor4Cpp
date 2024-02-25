package components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import common.LambdaFunc;

public class EditorFrame extends JFrame {

	private TextEditor editor;
	private LambdaFunc saveChanges;

	private static final long serialVersionUID = 1L;

	public EditorFrame(TextEditor editor,LambdaFunc saveChanges) {
		super("Editor4CPP");
		this.editor=editor;
		this.saveChanges=saveChanges;
		
		addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentShown(java.awt.event.ComponentEvent evt) {
				SwingUtilities.invokeLater(() -> setLocationRelativeTo(null));
			}
		});
		
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		initTextEditor();
		addToolbar();
	}
	
	private void initTextEditor() {
		JScrollPane scrollPane = new JScrollPane(editor);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane);
	}
	private void addToolbar() {
		JToolBar toolbar = new JToolBar();
		this.add(toolbar, "North");

//		ImageIcon saveIcon = new ImageIcon(MainView.class.getResource("/resources/save.png"));

		JButton saveButton = new JButton("S");

		Dimension buttonSize = new Dimension(24, 24);
		saveButton.setPreferredSize(buttonSize);
		saveButton.setMaximumSize(buttonSize);
		saveButton.setMinimumSize(buttonSize);

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveChanges.performOperation();
			}
		});

		toolbar.add(saveButton);
		
//		ImageIcon formatIcon = new ImageIcon(MainView.class.getResource("/resources/f.png"));

		JButton formatButton = new JButton("F");

		Dimension formatButtonSize = new Dimension(24, 24);
		formatButton.setPreferredSize(formatButtonSize);
		formatButton.setMaximumSize(formatButtonSize);
		formatButton.setMinimumSize(formatButtonSize);

		formatButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editor.formatText();
			}
		});
		
		toolbar.add(formatButton);
	}
	
}
