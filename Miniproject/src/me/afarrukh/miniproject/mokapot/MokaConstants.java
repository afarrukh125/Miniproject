package me.afarrukh.miniproject.mokapot;

import xyz.acygn.mokapot.CommunicationAddress;
import xyz.acygn.mokapot.DistributedCommunicator;

import java.io.IOException;
import java.net.InetAddress;
import java.security.KeyManagementException;
import java.security.KeyStoreException;

/**
 * @author Abdullah Farrukh
 */
public class MokaConstants {

    private static DistributedCommunicator comm = null;
    private static CommunicationAddress remoteAddress = null;

    public static void init() throws KeyManagementException, KeyStoreException, IOException {
        comm = new DistributedCommunicator("client.p12", "testpassword1".toCharArray());
        comm.startCommunication();

        remoteAddress = comm.lookupAddress(InetAddress.getLoopbackAddress(), 15238);

    }

    public static DistributedCommunicator getCommunicator() {
        if(comm == null)
            System.out.println("Communicator not found");
        return comm;
    }

    public static CommunicationAddress getRemoteAddress() {
        return remoteAddress;
    }
}
