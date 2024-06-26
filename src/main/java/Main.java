
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		
		int floorCount = 5;
		
		List<ElevatorController> elevatorControllers = createElevatorControllers(floorCount);
		
		SimpleElevatorManager em = new SimpleElevatorManager(elevatorControllers);
		
		List<ElevatorRequest> requestButtons = new ArrayList<>();
		for ( int i = 0; i < floorCount; i ++ ) {
			int floor = i+1;
			ElevatorRequest requestButton = new ElevatorRequest(floor,em);
			requestButtons.add(requestButton);
		}
		requestButtons.get(0).down();
	}

	private static List<ElevatorController> createElevatorControllers(int floorCount) {
		List<ElevatorController> elevatorControllers = new ArrayList<>();
		
		// Devices for Elevator 1
		ElevatorManager elevatorMotor1 = new ElevatorManager(DeviceVendor.Samsung);
		JavaDoorTimer doorTimer1 = new JavaDoorTimer();
			
		ElevatorDoor elevatorDoor1 = new ElevatorDoor(DeviceVendor.Samsung);
		List<FloorDoor> floorDoors1 = createFloorDoors(floorCount, DeviceVendor.Samsung);
		
		// every floor stop
		ElevatorController elevatorController1 = new ElevatorController(0,
				elevatorMotor1, elevatorDoor1, floorDoors1, doorTimer1);
		doorTimer1.setDoorTimeout(elevatorController1);
		elevatorMotor1.setElevatorController(elevatorController1);

		ControlRoomDisplay controlRoomDisplay = new ControlRoomDisplay(elevatorController1);
		elevatorController1.setControlRoomDisplay(controlRoomDisplay);
		ElevatorInsideDisplay elevatorInsideDisplay = new ElevatorInsideDisplay(elevatorController1);
		elevatorController1.setElevatorInsideDisplay(elevatorInsideDisplay);

		elevatorControllers.add(elevatorController1);
		
		// Devices for Elevator 2
		ElevatorManager elevatorMotor2 = new ElevatorManager(DeviceVendor.Hyundai);
		ElevatorDoor elevatorDoor2 = new ElevatorDoor(DeviceVendor.Hyundai);
		List<FloorDoor> floorDoors2 = createFloorDoors(floorCount, DeviceVendor.Hyundai);
		
		// demand only stop
		ElevatorController elevatorController2 = new ElevatorController(1,
					elevatorMotor2, elevatorDoor2, floorDoors2, null);
		elevatorMotor2.setElevatorController(elevatorController2);

		IFloorDisplayImplementor imp = new SamsungFloorDisplayImplementor();
		AdvancedFloorDisplay advancedFloorDisplay = new AdvancedFloorDisplay(elevatorController2, imp);
		elevatorController2.setAbstractFloorDisplay(advancedFloorDisplay);
		
		elevatorControllers.add(elevatorController2);
		return elevatorControllers;
	}

	private static List<FloorDoor> createFloorDoors(int floorCount, DeviceVendor vendor) {
		List<FloorDoor> floorDoors = new ArrayList<>();
		
		for ( int i = 0 ; i < floorCount; i ++ )
			floorDoors.add(new FloorDoor(vendor, i));
		return floorDoors;
	}
}
