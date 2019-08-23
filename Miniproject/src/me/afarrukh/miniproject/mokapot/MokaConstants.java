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

    final static boolean isLocal = DistributedCommunicator.getCommunicator()==null;

    private static DistributedCommunicator comm = null;
    private static CommunicationAddress remoteAddress = null;
    private static CommunicationAddress localAddr = null;

    public static DistributedCommunicator getCommunicator() {
        if (DistributedCommunicator.getCommunicator()==null){
            if (comm==null){
                try {
                    comm = new DistributedCommunicator("client.p12", "testpassword1".toCharArray());
                    comm.startCommunication();
                } catch (KeyStoreException e) {
                    e.printStackTrace();
                } catch (KeyManagementException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return comm;
            }
            return comm;
        }
        return DistributedCommunicator.getCommunicator();
    }


    public static CommunicationAddress getRemoteAddress() throws IOException {
        if (isLocal){
            if(remoteAddress==null){
                remoteAddress = getCommunicator().lookupAddress(InetAddress.getLoopbackAddress(), 15238);
            }
            return remoteAddress;
        }
        if (localAddr ==null){
            localAddr =  getCommunicator().getMyAddress();
        }
        return localAddr;
    }

    public static CommunicationAddress getLocalAddr() throws IOException {
        if (!isLocal){
            if(remoteAddress==null){
                remoteAddress =getCommunicator().lookupAddress(InetAddress.getLoopbackAddress(), 15238);
            }
            return remoteAddress;
        }
        if (localAddr ==null){
            localAddr =  getCommunicator().getMyAddress();
        }
        return localAddr;
    }

}
