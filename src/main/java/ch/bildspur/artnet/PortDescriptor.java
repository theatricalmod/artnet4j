/*
 * This file is part of artnet4j.
 * 
 * Copyright 2009 Karsten Schmidt (PostSpectacular Ltd.)
 * 
 * artnet4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * artnet4j is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with artnet4j. If not, see <http://www.gnu.org/licenses/>.
 */

package ch.bildspur.artnet;

public class PortDescriptor {

    protected boolean canOutput;
    protected boolean canInput;
    protected PortType type;
    protected byte inputStatus, outputStatus;
    protected int inputUniverse, outputUniverse;
    public PortDescriptor()
    {
    }

    public PortDescriptor(boolean canOutput, boolean canInput, PortType type, byte inputStatus, byte outputStatus, int inputUniverse, int outputUniverse) {
        this.canOutput = canOutput;
        this.canInput = canInput;
        this.type = type;
        this.inputStatus = inputStatus;
        this.outputStatus = outputStatus;
        this.inputUniverse = inputUniverse;
        this.outputUniverse = outputUniverse;
    }

    public PortDescriptor(int id) {
        canOutput = (id & 0x80) > 0;
        canInput = (id & 0x40) > 0;
        id &= 0x3f;
        for (PortType t : PortType.values()) {
            if (id == t.getPortID()) {
                type = t;
            }
        }
    }

    /**
     * @return the canInput
     */
    public boolean canInput() {
        return canInput;
    }

    /**
     * @return the canOutput
     */
    public boolean canOutput() {
        return canOutput;
    }

    /**
     * @return the type
     */
    public PortType getType() {
        return type;
    }
    public byte getInputStatus() {
        return inputStatus;
    }

    public byte getOutputStatus() {
        return outputStatus;
    }

    public int getInputUniverse() {
        return inputUniverse;
    }

    public int getOutputUniverse() {
        return outputUniverse;
    }

    public int getData()
    {
        int data = type.getPortID();
        data |= canInput ? 0x40 : 0;
        data |= canOutput ? 0x80 : 0;
        return data;
    }

    @Override
    public String toString() {
        return "PortDescriptor: " + type + " out: " + canOutput + " in: "
                + canInput;
    }

    public void setCanOutput(boolean canOutput) {
        this.canOutput = canOutput;
    }

    public void setCanInput(boolean canInput) {
        this.canInput = canInput;
    }

    public void setType(PortType type) {
        this.type = type;
    }

    public void setInputStatus(byte inputStatus) {
        this.inputStatus = inputStatus;
    }

    public void setOutputStatus(byte outputStatus) {
        this.outputStatus = outputStatus;
    }

    public void setInputUniverse(int inputUniverse) {
        this.inputUniverse = inputUniverse;
    }

    public void setOutputUniverse(int outputUniverse) {
        this.outputUniverse = outputUniverse;
    }
}
