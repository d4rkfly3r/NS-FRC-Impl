package club.pantherbotics.josh;

import us.jfreedman.src.ns.frc.common.Listener;
import us.jfreedman.src.ns.frc.common.Plugin;
import us.jfreedman.src.ns.frc.common.packets.Packet01;
import us.jfreedman.src.ns.frc.common.packets.Packet02;
import us.jfreedman.src.ns.frc.common.packets.Packet03;
import us.jfreedman.src.ns.frc.server.MainGUI;

/**
 * Created by Joshua Freedman on 2/23/2016.
 * Project: NS-FRC-Impl
 */
@Plugin(name = "Test Me")
public class Test2Class {

    @Listener
    public void defPacket(Packet01 packet01) {
        System.out.println(packet01.getExtra());
        MainGUI.getInstance().logTextArea.append(packet01.getExtra() + "\n");
    }

    @Listener
    public void numberPacket(Packet02 packet02) {
        MainGUI.getInstance().logTextArea.append(packet02.getKey() + " | " + packet02.getNumber() + "\n");
    }
    @Listener
    public void stringPacket(Packet03 packet03) {
        MainGUI.getInstance().logTextArea.append(packet03.getKey() + " | " + packet03.getString() + "\n");
    }
}
