package us.jfreedman.src.ns.frc.common.packets;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Joshua Freedman on 2/23/2016.
 * Project: NS-FRC-Impl
 */
public class Packet04 extends KeyedPacket<Packet04, byte[]> {

    int width, height;

    public Packet04(int width, int height, byte[] imageByteArray) {
        this(null, width, height, imageByteArray);
    }

    public Packet04(String key, int width, int height, BufferedImage image) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", bos);
        this.key = key;
        this.width = width;
        this.height = height;
        this.innerData = bos.toByteArray();
    }

    public Packet04(String key, int width, int height, byte[] imageByteArray) {
        this.key = key;
        this.width = width;
        this.height = height;
        this.innerData = imageByteArray;
    }

    public byte[] getImageByteArray() {
        return this.innerData;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BufferedImage getImage() {
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(new ByteArrayInputStream(getImageByteArray()));
            return bufferedImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
