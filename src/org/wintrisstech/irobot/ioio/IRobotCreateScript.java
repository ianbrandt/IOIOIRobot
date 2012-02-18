/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wintrisstech.irobot.ioio;

/**
 * This class is used to create scripts that can then be played using the
 * IRobotCreateInterface playScript() method.
 *
 * @see IRobotCreateInterface#playScript(byte[]) 
 * @author Erik
 */
public class IRobotCreateScript {

    /**
     * This command controls Create's drive wheels. It takes four data bytes,
     * interpreted as two 16-bit signed values using two's complement. The first
     * two bytes specify the average velocity of the drive wheels in millimeters
     * per second (mm/s), with the high byte being sent first. The next two
     * bytes specify the radius in millimeters at which Create will turn. The
     * longer radii make Create drive straighter, while the shorter radii make
     * Create turn more. The radius is measured from the center of the turning
     * circle to the center of Create. A Drive command with a positive velocity
     * and a positive radius makes Create drive forward while turning toward the
     * left. A negative radius makes Create turn toward the right. Special cases
     * for the radius make Create turn in place or drive straight, as specified
     * below. A negative velocity makes Create drive backward. <br>
     * NOTE: Internal and environmental restrictions may prevent Create from
     * accurately carrying out some drive commands. For example, it may not be
     * possible for Create to drive at full speed in an arc with a large radius
     * of curvature. <br>
     * + Available in modes: Safe or Full <br>
     * + Changes mode to: No Change <br>
     * + Velocity (-500 - 500 mm/s) <br>
     * + Radius (-2000 - 2000 mm) <br>
     * Special cases: <br>
     * Straight = 32768 or 32767 = hex 8000 or 7FFF <br>
     * Turn in place clockwise = hex FFFF <br>
     * Turn in place counter-clockwise = hex 0001
     * @see #drive(int, int)
     */
    private static final byte COMMAND_DRIVE = (byte) 137;
    /**
     * This command lets you control the three low side drivers. The state of
     * each driver is specified by one bit in the data byte. Low side drivers 0
     * and 1 can provide up to 0.5A of current. Low side driver 2 can provide up
     * to 1.5 A of current. If too much current is requested, the current is
     * limited and the overcurrent flag is set (sensor packet 14).
     * <p>
     * The bits in the data byte are mapped to the drivers as follows
     * (0 = off, 1 = on at 100% PWM duty cycle):
     * <p>
     * bit 0 - Low Side Driver 0 (pin 23) <br>
     * bit 1 - Low Side Driver 1 (pin 22) <br>
     * bit 2 - Side Driver (pin 24)
     * @see #lowSideDrivers(boolean, boolean, boolean)
     */
    private static final byte COMMAND_DRIVE_DIRECT = (byte) 145;
    /**
     * This command causes iRobot Create to wait until it has
     * traveled the specified distance in mm. When Create travels
     * forward, the distance is incremented. When Create travels
     * backward, the distance is decremented. If the wheels
     * are passively rotated in either direction, the distance is
     * incremented. Until Create travels the specified distance,
     * its state does not change, nor does it react to any inputs,
     * serial or otherwise.
     * <p>
     * NOTE: This command resets the distance variable that is
     * returned in Sensors packets 19, 2 and 6.
     * <ul>
     * <li> Serial sequence: [156] [Distance high byte][Distance low byte]
     * <li> Available in modes: Passive, Safe, or Full
     * <li> Changes mode to: No Change
     * <li> Wait Distance data bytes 1-2: 16-bit signed distance in mm, high 
     * byte first (-32767 -32768)
     * </ul>
     */
    private static final byte COMMAND_WAIT_DISTANCE = (byte) 156;
    /**
     * This command causes Create to wait until it has rotated
     * through specified angle in degrees. When Create turns
     * counterclockwise, the angle is incremented. When Create
     * turns clockwise, the angle is decremented. Until Create
     * turns through the specified angle, its state does not change,
     * nor does it react to any inputs, serial or otherwise.
     * <p>
     * NOTE: This command resets the angle variable that is
     * returned in Sensors packets 20, 2 and 6.
     * <ul>
     * <li> Serial sequence: [157] [Angle high byte] [Angle low byte]
     * <li> Available in modes: Passive, Safe, or Full
     * <li> Changes mode to: No Change
     * <li> Wait Angle data bytes 1-2: 16-bit signed angle in degrees,
     * high byte first (-32767 - 32768)
     * </ul>
     */
    private static final byte COMMAND_WAIT_ANGLE = (byte) 157;
    private int maxScriptLength = 100; // According to Create Open Interface spec.
    private byte[] script = new byte[maxScriptLength];
    /**
     * The script is included in the array script starting at index 0 and ending
     * at index last - 1.
     */
    private int last = 0;

    /**
     * Once the script is ready, this method may be called to retrieve the 
     * script as a byte array.
     * @return a byte array containing the script, which may be used as an 
     * argument to {@link IRobotCreateInterface#playScript(byte[])}
     */
    public byte[] getBytes() {
        byte[] result = new byte[last];
        System.arraycopy(script, 0, result, 0, last);
        return result;
    }

    /**
     * Adds a byte to the script.
     * @param b the byte added
     * @throws IndexOutOfBoundsException if the script has reached the maximum
     * size (= 100 bytes).
     */
    private void addByte(byte b) throws IndexOutOfBoundsException {
        if (last < maxScriptLength) {
            script[last++] = b;
        } else {
            throw new IndexOutOfBoundsException("Max script length exceeded");
        }
    }

    /**
     * This methods adds a command to the script that controls the Create's
     * drive wheels. Larger values for the radius makes
     * the Create drive straighter, while the smaller values make the Create
     * turn more. The radius is measured from the center of the turning
     * circle to the center of Create. A positive velocity
     * and a positive radius make the Create drive forward while turning toward the
     * left. A negative radius makes the Create turn toward the right. A negative
     * velocity makes Create drive backward. Available in modes: Safe or Full
     * <p>
     * NOTE: Internal and environmental restrictions may prevent Create from
     * accurately carrying out some drive commands. For example, it may not be
     * possible for Create to drive at full speed in an arc with a large radius
     * of curvature.
     * <p>
     * @param velocity the average velocity of the wheels in mm/s. Range is
     * -500 to 500. If velocity is out of range it is truncated.
     * @param radius the turn radius in mm. Range is -2000 to 2000. If radius is
     * out of range it is truncated.
     */
    public void drive(int velocity, int radius) {
        if (velocity < -500) {
            velocity = -500;
        } else if (500 < velocity) {
            velocity = 500;
        }
        if (radius < -2000) {
            radius = -2000;
        } else if (2000 < radius) {
            radius = 2000;
        }
        addByte(COMMAND_DRIVE);
        addByte((byte) (velocity >> 8));
        addByte((byte) velocity);
        addByte((byte) (radius >> 8));
        addByte((byte) radius);
    }

    /**
     * Makes the Create drive straight. Negative values of velocity make the
     * Create drive backward.
     *
     * @param velocity the velocity in mm/s. Must be between -500 and 500. If
     * velocity is out of range, it is truncated.
     */
    public void driveStraight(int velocity) {
        if (velocity < -500) {
            velocity = -500;
        } else if (500 < velocity) {
            velocity = 500;
        }
        addByte(COMMAND_DRIVE);
        addByte((byte) (velocity >> 8));
        addByte((byte) velocity);
        addByte((byte) 0x7F);
        addByte((byte) 0xFF);
    }

    /**
     * Makes the Create turn in place.
     * @param velocity - the velocity in mm/s. Must be positive and less than or equal to 500 mm/s.
     * @param clockwise - if true, the Create turns clockwise.
     */
    public void turnInPlace(int velocity, boolean clockwise) {
        if (velocity < 0) {
            velocity = 1;
        } else if (500 < velocity) {
            velocity = 500;
        }
        addByte(COMMAND_DRIVE);
        addByte((byte) (velocity >> 8)); // velocity high byte
        addByte((byte) velocity); // velocity low byte
        if (clockwise) {
            addByte((byte) 0xFF);
            addByte((byte) 0xFF);
        } else {
            addByte((byte) 0x00);
            addByte((byte) 0x01);

        }
    }

    /**
     * Sets the speed of each wheel of the Create. Negative values make the wheel
     * turn backward.
     * @param rightSpeed the velocity of the right wheel in mm/s. Must be
     * between -500 and 500. If rightSpeed is out of range it is truncated.
     * @param leftSpeed the velocity of the left wheel in mm/s. Must be
     * between -500 and 500.  If leftSpeed is out of range it is truncated.
     */
    public void driveDirect(int rightSpeed, int leftSpeed) {
        if (rightSpeed < -500) {
            rightSpeed = -500;
        } else if (500 < rightSpeed) {
            rightSpeed = 500;
        }
        if (leftSpeed < -500) {
            leftSpeed = -500;
        } else if (500 < leftSpeed) {
            leftSpeed = 500;
        }
        addByte(COMMAND_DRIVE_DIRECT);
        addByte((byte) (rightSpeed >> 8));
        addByte((byte) rightSpeed);
        addByte((byte) (leftSpeed >> 8));
        addByte((byte) leftSpeed);
    }

    /**
     * Makes the Create stop.
     */
    public void stop() {
        addByte(COMMAND_DRIVE_DIRECT);
        addByte((byte) 0);
        addByte((byte) 0);
        addByte((byte) 0);
        addByte((byte) 0);
    }

    /**
     * Waits until the Create has rotated a specified angle.
     * @param angle the angle in degrees. Must be between -32767 and 32767. If
     * angle is out of range it is truncated.
     */
    public void waitAngle(int angle) {
        if(angle < -32767) {
            angle = -32767;
        }
        if(32767 < angle) {
            angle = 32767;
        }
        addByte(COMMAND_WAIT_ANGLE);
        addByte((byte) (angle >> 8));
        addByte((byte) angle);
    }


    /**
     * Waits until the Create has driven a specified distance.
     * @param distance the distance in mm. Must be between 0 and 32767. If
     * distance is out of range it is truncated.
     */
    public void waitDistance(int distance) {
        if(distance < 0) {
            distance = 0;
        }
        if(32767 < distance) {
            distance = 32767;
        }
        addByte(COMMAND_WAIT_DISTANCE);
        addByte((byte) (distance >> 8));
        addByte((byte) distance);
    }
}
