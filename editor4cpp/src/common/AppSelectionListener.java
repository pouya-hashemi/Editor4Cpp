package common;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

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
