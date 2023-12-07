package Serializable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;


public class MessageExternalizable implements Externalizable {
    private String sender, message;

    public MessageExternalizable(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public static void usage() {
        System.out.println("Il manque des arguments. Il en faut 3 : sender message fileName");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        sender = in.readUTF();
        message = in.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(sender);
        out.writeUTF(message);
    }

    public static void main(String[] args) {
        if(args.length == 3) {
            String sender = args[0];
            String message = args[1];
            String fileName = args[2];
            Message msg = new Message(sender, message);
            msg.printMessage();
            MessageExternalizable messageExternalizable = new MessageExternalizable(sender, message);

            try(FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                FileInputStream fileInputStream = new FileInputStream(fileName);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
                objectOutputStream.writeObject(msg);
                msg.printMessage();

                messageExternalizable.readExternal(objectInputStream);
                messageExternalizable.writeExternal(objectOutputStream);
            } catch(IOException e) {
                e.printStackTrace();
            } catch(ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            usage();
        }
    }
}