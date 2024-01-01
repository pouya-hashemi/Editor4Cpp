package common;


import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import org.eclipse.ui.ISelectionListener;

import org.eclipse.ui.IWorkbenchPart;


import components.MainView;

public class AppSelectionListener implements ISelectionListener {

	private MainView mainView;
	public AppSelectionListener(MainView view) {
		mainView=view;
	}
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
            Object selectedObject = ((IStructuredSelection) selection).getFirstElement();

            if(selectedObject instanceof EStringToStringMapEntryImpl) {
            	EStringToStringMapEntryImpl entry=(EStringToStringMapEntryImpl)selectedObject;	
//            	 MessageDialog.openInformation(
//                         PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
//                         "Selection Information",
//                         entry.getValue());
            	
            	mainView.clear();
            	mainView.setEntry(entry);
            	mainView.addProperty("Key", entry.getKey());
            	mainView.addProperty("Value", entry.getValue());
            	
            }
            else {
            	mainView.clear();
            }

        }
	}

}
