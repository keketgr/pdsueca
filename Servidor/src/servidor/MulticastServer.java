/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class MulticastServer extends Thread
{
    MulticastSocket s;
    InetAddress serverAddr;
    int port=0;
    
    public MulticastServer(InetAddress servaddr,int port) throws SocketException, UnknownHostException, IOException
    {
        
        s= new MulticastSocket(6001);
        InetAddress group=InetAddress.getByName("225.0.0.1");
        s.joinGroup(group);
        serverAddr=InetAddress.getByName("127.0.0.1");
        this.port=port;
        		        
        
    }
    
    @Override
    public void run()
    {
        DatagramPacket pkt=null;
        String msg = "127.0.0.1 "+port+" \n";
        try {
            msg= " "+ InetAddress.getLocalHost().getHostAddress()+ " "+port+" \n";
        } catch (UnknownHostException ex) {
            Logger.getLogger(MulticastServer.class.getName()).log(Level.SEVERE, null, ex);
        }
//        try {
//            //s.setSoTimeout(100000);
//        } catch (SocketException ex) {
//            Logger.getLogger(MulticastServer.class.getName()).log(Level.SEVERE, null, ex);
//        }
        try {
            
            pkt=new DatagramPacket(msg.getBytes(),msg.length(),InetAddress.getByName("225.0.0.1"),6001);
        } catch (UnknownHostException ex) {
            Logger.getLogger(MulticastServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            byte[] buf = new byte[256];
            DatagramPacket pp= new DatagramPacket(buf,buf.length);
            while(true){
               s.receive(pp);
               String rec=new String(pp.getData());
               System.out.println("<MULTI>recebeu de "+pp.getAddress()+" :"+pp.getPort()+" msg: "+rec);
               
               if(rec.contains("IP")){
                  pkt.setPort(pp.getPort());
                s.send(pkt);
               }
            }
            
        }catch(Exception e ){
            System.err.println("<Cli> Erro no acesso ao socket UDP!");
            System.err.println(e);            
        }finally{
            s.close();
        }
    }
}
