package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GrammarNode implements Cloneable {
	public UUID Id;
	protected List<UUID> childIds;
	public boolean canBeEnd;

	public GrammarNode() {
		Id = UUID.randomUUID();
	}

	public GrammarNode(UUID id, List<UUID> childIds, boolean canBeEnd) {
		this.Id = id;
		this.childIds = childIds;
		this.canBeEnd = canBeEnd;
	}

	public void addChild(UUID id) {
		if (childIds == null)
			childIds = new ArrayList<UUID>();

		childIds.add(id);
	}

	public void addChild(List<UUID> ids) {
		if (childIds == null)
			childIds = new ArrayList<UUID>();

		childIds.addAll(ids);
	}

	public List<UUID> getChildIds() {
		return childIds == null ? new ArrayList<UUID>() : childIds;
	}

	public void removeChild(UUID childId) {
		var child=this.childIds.stream().filter(f->f.equals(childId)).findFirst();
		if(child.isPresent())
			this.childIds.remove(childId);
	}

	@Override
	public GrammarNode clone() {
		var newIds = new ArrayList<UUID>();
		if (this.childIds != null)
			for (UUID id : this.childIds)
				newIds.add( UUID.fromString(id.toString()));
		return new GrammarNode(this.Id, newIds, this.canBeEnd);
	}

}
