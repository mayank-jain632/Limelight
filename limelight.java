import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
NetworkTableEntry tx = table.getEntry("tx");
NetworkTableEntry ty = table.getEntry("ty");
NetworkTableEntry ta = table.getEntry("ta");

//read values periodically
double x = tx.getDouble(0.0);
double y = ty.getDouble(0.0);
double area = ta.getDouble(0.0);

//post to smart dashboard periodically
SmartDashboard.putNumber("LimelightX", x);
SmartDashboard.putNumber("LimelightY", y);
SmartDashboard.putNumber("LimelightArea", area);


//aiming towards vision target
float Kp = -0.1;
float min_command = 0.05;

// Get the NetworkTable instance
NetworkTableInstance inst = NetworkTableInstance.getDefault();
// Get the "limelight" table
NetworkTable table = inst.getTable("limelight");

//horizontal diff between vision target and crosshair
float tx = table.getNumber("tx")

//if button is clicked
if(joystick.getRawButton(9)){
    //diff
    float heading_error = -tx;
    float steering_adjust = 0.0;

    
    if (tx > 1.0){
        steering_adjust = Kp * heading_error - min_command;
    } else if (tx < 1.0){
        steering_adjust = Kp * heading_error + min_command;
    }

    float left_command += steering_adjust;
    float right_command -= steering_adjust;

    System.out.println(left_command);
    System.out.println(right_command);
}



//moving a particular distance
float KpDistance = -0.1;

NetworkTableInstance inst = NetworkTableInstance.getDefault();
// Get the "limelight" table
NetworkTable table = inst.getTable("limelight");

float distance_error = table.getNumber("ty");

float driving_adjust = 0.0;

if(joystick.getRawButton(8)){
    driving_adjust = KpDistance * distance_error;

    float left_command += distance_adjust;
    float right_command += distance_adjust;

    System.out.println(left_command);
    System.out.println(right_command);
}

