package common;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class ModifyFeatureCommand extends AbstractCommand {

    private EObject targetObject;
    private EStructuralFeature feature;
    private Object newValue;
    private Object oldValue;

    public ModifyFeatureCommand(EObject targetObject, EStructuralFeature feature, Object newValue) {
        super("Modify Feature Command");
        this.targetObject = targetObject;
        this.feature = feature;
        this.newValue = newValue;
        this.oldValue = targetObject.eGet(feature);
    }

    @Override
    protected boolean prepare() {
        return true;
    }

    @Override
    public void execute() {
        redo();
    }

    @Override
    public void redo() {
        targetObject.eSet(feature, newValue);
    }

    @Override
    public void undo() {
        targetObject.eSet(feature, oldValue);
    }

    public static Command createModifyFeatureCommand(EObject targetObject, EStructuralFeature feature, Object newValue) {
        return new ModifyFeatureCommand(targetObject, feature, newValue);
    }
}
