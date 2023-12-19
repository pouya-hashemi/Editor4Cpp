package components;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import common.AppSelectionListener;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class MainView extends ViewPart {
	private Table table;
	private JFrame frame;
	private TableItem selectedItem=null;
	private TextEditor editor;

	public MainView() {
		frame = new JFrame("Editor4CPP");
		frame.setLocationRelativeTo(null);

		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		editor = new TextEditor();

		JScrollPane scrollPane = new JScrollPane(editor);
		frame.getContentPane().add(scrollPane);
		
		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				Display.getDefault().syncExec(new Runnable() {
				    public void run() {
				    	selectedItem.setText(1, editor.getText());
				    }
				});
		        
			}

		});
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
		valueColumn.pack();

		TableColumn btnColumn = new TableColumn(table, SWT.CENTER);
		btnColumn.setText(" ");
		btnColumn.setWidth(35);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}

	public void addProperty(String propertyName, String propertyValue) {
		TableItem item = new TableItem(table, SWT.BORDER);
		item.setText(0, propertyName);
		item.setText(1, propertyValue);

		TableEditor tableEditor = new TableEditor(table);
		Button button = new Button(table, SWT.BUTTON1);
		button.setText("...");
		button.pack();
		button.setData("itemIndex", table.indexOf(item));
		button.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				selectedItem=table.getItem((int)event.widget.getData("itemIndex"));
				editor.setEditorText(selectedItem.getText(1));
				frame.setVisible(true);
			}
		});
		tableEditor.minimumWidth = button.getSize().x + 5;
		tableEditor.horizontalAlignment = SWT.CENTER;
		tableEditor.setEditor(button, item, 2);
		packColumns();

	}

	public void clear() {
		table.removeAll();
		packColumns();
	}

	private void packColumns() {
		for (TableColumn column : table.getColumns()) {
			column.pack();
			column.setWidth(column.getWidth() + 5);
		}

	}

}
