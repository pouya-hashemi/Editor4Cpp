package common;

import java.util.logging.Logger;

import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import org.eclipse.ui.ISelectionListener;

import org.eclipse.ui.IWorkbenchPart;

import components.MainView;

public class AppSelectionListener implements ISelectionListener {
	private static final Logger logger = Logger.getLogger(AppSelectionListener.class.getName());
	private MainView mainView;

	public AppSelectionListener(MainView view) {
		mainView = view;
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		try {
			if (selection instanceof IStructuredSelection) {
				Object selectedObject = ((IStructuredSelection) selection).getFirstElement();

				if (selectedObject instanceof EStringToStringMapEntryImpl) {
					EStringToStringMapEntryImpl entry = (EStringToStringMapEntryImpl) selectedObject;

					mainView.setEntry(entry);

				} else {
					mainView.clear();
				}

			}
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}
	}

}
