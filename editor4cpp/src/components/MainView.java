package components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import common.AppSelectionListener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import javax.swing.JOptionPane;

public class MainView extends ViewPart {
	private Table table;
	private JFrame frame;
	private TableItem selectedItem = null;
	private TextEditor editor;
	private EStringToStringMapEntryImpl entry;
	private boolean closing = false;
	private List<Button> buttons = new ArrayList<>();
	private List<TableEditor> tableEditors = new ArrayList<>();

	public MainView() {
		frame = new JFrame("Editor4CPP");

		frame.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentShown(java.awt.event.ComponentEvent evt) {
				// Center the JFrame on the screen after it is realized
				SwingUtilities.invokeLater(() -> frame.setLocationRelativeTo(null));
			}
		});

		frame.setSize(500, 500);

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		editor = new TextEditor();

		JScrollPane scrollPane = new JScrollPane(editor);
		frame.getContentPane().add(scrollPane);

		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {

				if (editor.getNeedSave() && !closing) {

					int result = JOptionPane.showOptionDialog(frame, "Do you want to save your changes?",
							"Save Changes", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
							new Object[] { "Yes", "No", "Cancel" }, "Cancel");

					// Handle the user's choice
					switch (result) {
					case JOptionPane.YES_OPTION:
						saveChanges();
						closing = true;
						frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
						frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

						break;
					case JOptionPane.NO_OPTION:
						editor.setNeedSave(false);
						closing = true;
						frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
						frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
						break;
					case JOptionPane.CANCEL_OPTION:
						break;

					}

				} else if (!editor.getNeedSave() && !closing) {
					closing = true;
					frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				}
				closing = false;

			}

		});

		addToolbar();
	}

	private void addToolbar() {
		JToolBar toolbar = new JToolBar();
		frame.add(toolbar, "North");

		ImageIcon saveIcon = new ImageIcon(MainView.class.getResource("/resources/save.png"));

		JButton saveButton = new JButton(saveIcon);

		Dimension buttonSize = new Dimension(16, 16);
		saveButton.setPreferredSize(buttonSize);
		saveButton.setMaximumSize(buttonSize);
		saveButton.setMinimumSize(buttonSize);

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveChanges();
			}
		});

		toolbar.add(saveButton);
	}

	private void saveChanges() {
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				selectedItem.setText(1, editor.getText());
				if (selectedItem.getText(0).equals("Value"))
					entry.setValue(editor.getText());
				if (selectedItem.getText(0).equals("Key"))
					entry.setKey(editor.getText());

				EObject container = entry.eContainer();

				Resource resource = container.eResource();
				resource.setModified(true);

				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

				IEditorPart editor = page.getActiveEditor();

				if (editor != null) {
					editor.doSave(new NullProgressMonitor());
				}
			}
		});
		editor.setNeedSave(false);
	}

	@Override
	public void createPartControl(Composite parent) {

		initTable(parent);
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService()
				.addSelectionListener(new AppSelectionListener(this));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	private void initTable(Composite parent) {
		table = new Table(parent, SWT.BORDER);
		TableColumn nameColumn = new TableColumn(table, SWT.LEFT);
		nameColumn.setText("Property");
		nameColumn.setWidth(100);

		TableColumn valueColumn = new TableColumn(table, SWT.LEFT);
		valueColumn.setText("Value");
		nameColumn.setWidth(200);

		TableColumn btnColumn = new TableColumn(table, SWT.CENTER);
		btnColumn.setText(" ");
		btnColumn.setWidth(50);
		btnColumn.setResizable(false);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}

	public void addProperty(String propertyName, String propertyValue) {
		TableItem item = new TableItem(table, SWT.BORDER);
		item.setText(0, propertyName);
		item.setText(1, propertyValue);
//		packColumns();
		TableEditor tableEditor = new TableEditor(table);
		Button button = new Button(table, SWT.BUTTON1);
		button.setText("...");
		button.setSize(10, 10);
		button.pack();
		button.setData("itemIndex", table.indexOf(item));
		button.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				selectedItem = table.getItem((int) event.widget.getData("itemIndex"));
				editor.setEditorText(selectedItem.getText(1));
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		});
		tableEditor.minimumWidth = button.getSize().x + 5;
		tableEditor.minimumHeight = button.getSize().y - 5;
		tableEditor.horizontalAlignment = SWT.CENTER;
		tableEditor.setEditor(button, item, 2);

		buttons.add(button);
		tableEditors.add(tableEditor);
		

	}

	public void clear() {
		for (Button button : buttons) {
			button.dispose();
		}
		buttons.clear();

		for (TableEditor editor : tableEditors) {
			editor.dispose();
		}
		tableEditors.clear();

		table.removeAll();
		packColumns();
		entry = null;
	}

	public void setEntry(EStringToStringMapEntryImpl entry) {
		this.entry = entry;
	}

	private void packColumns() {
		for (TableColumn column : table.getColumns()) {
			if (column.getWidth() == 50)
				continue;
			column.pack();
			column.setWidth(column.getWidth() + 5);
		}

	}

}
