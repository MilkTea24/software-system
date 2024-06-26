
public class HyundaiFloorDisplayImplementor implements IFloorDisplayImplementor {
	private boolean activated = true;
	public void activateDisplay() {
		activated = true;
	}
	public void deactivateDisplay() {
		activated = false;
	}
	public void showCurrentPosition(int floor) {
		if ( ! activated ) return;
		System.out.println("Hyundai Display: Current Postion " + floor);
	}
	public void expressCurrentPositionByVoice(int floor) {
		if ( ! activated ) return;
		System.out.println("Hyundai Voice: Current Postion " + floor);
	}
	public void showDirection(int direction) {
		if ( ! activated ) return;
		System.out.println("Hyundai Display: Current Direction " + direction);
	}
	public void expressDirectionByVoice(int direction) {
		if ( ! activated ) return;
		System.out.println("Hyundai Voice: Current Direction " + direction);
	}
}
