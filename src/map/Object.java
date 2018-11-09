package map;

public class Object {
	private String icon;
	private boolean canInteract;
	private boolean isSolid;
	
	Object() {
		this.icon = "";
		this.canInteract = false;
		this.isSolid = false;
	}
	
	Object(String icon, boolean canInteract, boolean isSolid) {
		this.icon = icon;
		this.canInteract = canInteract;
		this.isSolid = isSolid;
	}
	
	
}
