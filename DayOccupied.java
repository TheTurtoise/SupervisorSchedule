public class DayOccupied {
	
	public enum DayOccupiedValue {A,B,C,D}
	
	private boolean blockA;
	private boolean blockB;
	private boolean blockL;
	private boolean blockC;
	private boolean blockD;
	
	public boolean isBlockA() {
		return blockA;
	}
	public void setBlockA(boolean blockA) {
		this.blockA = blockA;
	}
	public boolean isBlockB() {
		return blockB;
	}
	public void setBlockB(boolean blockB) {
		this.blockB = blockB;
	}
	public boolean isBlockC() {
		return blockC;
	}
	public void setBlockC(boolean blockC) {
		this.blockC = blockC;
	}
	public boolean isBlockD() {
		return blockD;
	}
	public void setBlockD(boolean blockD) {
		this.blockD = blockD;
	}
	
	public void  process (String value) {
		switch (value) {
		case "A":
			this.blockA = true;
			break;
		case "B":
			this.blockB = true;
			this.blockL = true;
			break;
		case "C":
			this.blockC = true;
			this.blockL = true;
			break;
		case "D":
			this.blockD = true;
			break;
		default:
			break;
		}
	}
	
	public boolean isBlockL() {
		return blockL;
	}
	public void setBlockL(boolean blockL) {
		this.blockL = blockL;
	}
	@Override
	public String toString() {
		return "DayOccupied [blockA=" + blockA + ", blockB=" + blockB + ", blockL=" + blockL + ", blockC=" + blockC
				+ ", blockD=" + blockD + "]";
	}
}
