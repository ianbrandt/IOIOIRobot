package org.wintrisstech.irobot.ioio;

/**
 * A concrete class that provides a default implementation of the
 * IRobotCreateInterface. It is a convenience class intended to be extended in
 * order to define customized implementations of the IRobotCreateInterface.
 */
public class IRobotCreateAdapter implements IRobotCreateInterface {

    /**
     * The decorated instance. All calls on the methods of the
     * IRobotCreateInterface are forwarded to this instance.
     */
    protected final IRobotCreateInterface delegate;

    /**
     * Makes a new instance from an IRobotCreateInterface implementation
     * instance.
     *
     * @param delegate a non-null instance of IRobotCreateInterface to which
     * method calls are forwarded.
     */
    public IRobotCreateAdapter(IRobotCreateInterface delegate) {
        if (delegate == null) {
            throw new IllegalArgumentException("Argument must be non-null");
        }
        this.delegate = delegate;
    }

    public void demo(int demoType) {
        delegate.demo(demoType);
    }

    public void setDigitalOutputs(boolean pin0High, boolean pin1High, boolean pin2High) {
        delegate.setDigitalOutputs(pin0High, pin1High, pin2High);
    }

    public void drive(int velocity, int radius) {
        delegate.drive(velocity, radius);
    }

    public void driveDirect(int rightVelocity, int leftVelocity) {
        delegate.driveDirect(rightVelocity, leftVelocity);
    }

    public void full() {
        delegate.full();
    }

    public int getAngle() {
        return delegate.getAngle();
    }

    public int getBatteryCapacity() {
        return delegate.getBatteryCapacity();
    }

    public int getBatteryCharge() {
        return delegate.getBatteryCharge();
    }

    public int getBatteryTemperature() {
        return delegate.getBatteryTemperature();
    }

    public int getCargoBayAnalogSignal() {
        return delegate.getCargoBayAnalogSignal();
    }

    public int getChargingState() {
        return delegate.getChargingState();
    }

    public int getCliffFrontLeftSignal() {
        return delegate.getCliffFrontLeftSignal();
    }

    public int getCliffFrontRightSignal() {
        return delegate.getCliffFrontRightSignal();
    }

    public int getCliffLeftSignal() {
        return delegate.getCliffLeftSignal();
    }

    public int getCliffRightSignal() {
        return delegate.getCliffRightSignal();
    }

    public int getCurrent() {
        return delegate.getCurrent();
    }

    public int getDistance() {
        return delegate.getDistance();
    }

    public int getInfraredByte() {
        return delegate.getInfraredByte();
    }

    public int getOiMode() {
        return delegate.getOiMode();
    }

    public int getRequestedLeftVelocity() {
        return delegate.getRequestedLeftVelocity();
    }

    public int getRequestedRadius() {
        return delegate.getRequestedRadius();
    }

    public int getRequestedRightVelocity() {
        return delegate.getRequestedRightVelocity();
    }

    public int getRequestedVelocity() {
        return delegate.getRequestedVelocity();
    }

    public int getSongNumber() {
        return delegate.getSongNumber();
    }

    public int getVoltage() {
        return delegate.getVoltage();
    }

    public int getWallSignal() {
        return delegate.getWallSignal();
    }

    public boolean isAdvanceButtonDown() {
        return delegate.isAdvanceButtonDown();
    }

    public boolean isBumpLeft() {
        return delegate.isBumpLeft();
    }

    public boolean isBumpRight() {
        return delegate.isBumpRight();
    }

    public boolean isCargoBayDeviceDetectBaudRateChangeHigh() {
        return delegate.isCargoBayDeviceDetectBaudRateChangeHigh();
    }

    public boolean isCargoBayDigitalInput0High() {
        return delegate.isCargoBayDigitalInput0High();
    }

    public boolean isCargoBayDigitalInput1High() {
        return delegate.isCargoBayDigitalInput1High();
    }

    public boolean isCargoBayDigitalInput2High() {
        return delegate.isCargoBayDigitalInput2High();
    }

    public boolean isCargoBayDigitalInput3High() {
        return delegate.isCargoBayDigitalInput3High();
    }

    public boolean isCliffFrontLeft() {
        return delegate.isCliffFrontLeft();
    }

    public boolean isCliffFrontRight() {
        return delegate.isCliffFrontRight();
    }

    public boolean isCliffLeft() {
        return delegate.isCliffLeft();
    }

    public boolean isCliffRight() {
        return delegate.isCliffRight();
    }

    public boolean isHomeBaseChargerAvailable() {
        return delegate.isHomeBaseChargerAvailable();
    }

    public boolean isInternalChargerAvailable() {
        return delegate.isInternalChargerAvailable();
    }

    public boolean isLeftWheelOvercurrent() {
        return delegate.isLeftWheelOvercurrent();
    }

    public boolean isLowSideDriver0Overcurrent() {
        return delegate.isLowSideDriver0Overcurrent();
    }

    public boolean isLowSideDriver1Overcurrent() {
        return delegate.isLowSideDriver1Overcurrent();
    }

    public boolean isLowSideDriver2Overcurrent() {
        return delegate.isLowSideDriver2Overcurrent();
    }

    public boolean isPlayButtonDown() {
        return delegate.isPlayButtonDown();
    }

    public boolean isRightWheelOvercurrent() {
        return isRightWheelOvercurrent();
    }

    public boolean isSongPlaying() {
        return delegate.isSongPlaying();
    }

    public boolean isVirtualWall() {
        return delegate.isVirtualWall();
    }

    public boolean isWall() {
        return delegate.isWall();
    }

    public boolean isWheelDropCaster() {
        return delegate.isWheelDropCaster();
    }

    public boolean isWheelDropLeft() {
        return delegate.isWheelDropLeft();
    }

    public boolean isWheelDropRight() {
        return delegate.isWheelDropRight();
    }

    public void leds(boolean powerLedOn, boolean playLedOn, boolean advanceLedOn) {
        delegate.leds(powerLedOn, playLedOn, advanceLedOn);
    }

    public void leds(int powerColor, int powerIntensity, boolean playLedOn, boolean advanceLedOn) {
        delegate.leds(powerColor, powerIntensity, playLedOn, advanceLedOn);
    }

    public void ledsToggle(boolean togglePower, boolean togglePlay, boolean toggleAdvance) {
        delegate.ledsToggle(togglePower, togglePlay, toggleAdvance);
    }

    public void lowSideDrivers(boolean lowSideDriver0On, boolean lowSideDriver1On, boolean lowSideDriver2On) {
        delegate.lowSideDrivers(lowSideDriver0On, lowSideDriver1On, lowSideDriver2On);
    }

    public void playScript(byte[] script) {
        delegate.playScript(script);
    }

    public void playSong(int songNumber) {
        delegate.playSong(songNumber);
    }

    public void pwmLowSideDrivers(int lowSideDriver0DutyCycle, int lowSideDriver1DutyCycle, int lowSideDriver2DutyCycle) {
        delegate.pwmLowSideDrivers(lowSideDriver0DutyCycle, lowSideDriver1DutyCycle, lowSideDriver2DutyCycle);
    }

    public void readSensors(int sensorId) {
        delegate.readSensors(sensorId);
    }

    public void readSensors(int[] sensorIds) {
        delegate.readSensors(sensorIds);
    }

    public void safe() {
        delegate.safe();
    }

    public void sendIr(int byteValue) {
        delegate.sendIr(byteValue);
    }

    public int[] showScript() {
        return delegate.showScript();
    }

    public void song(int songNumber, int[] notesAndDurations) {
        delegate.song(songNumber, notesAndDurations);
    }

    public void song(int songNumber, int[] notesAndDurations, int startIndex, int length) {
        delegate.song(songNumber, notesAndDurations, startIndex, length);
    }

    public void waitButtonPressed(boolean playButton, boolean beep) {
        delegate.waitButtonPressed(playButton, beep);
    }

    public void closeConnection() {
        delegate.closeConnection();
    }
}
