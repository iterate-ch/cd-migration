/**
 *
 *  edtFTPj
 *
 *  Copyright (C) 2000-2003 Enterprise Distributed Technologies Ltd
 *
 *  www.enterprisedt.com
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *  Bug fixes, suggestions and comments should be sent to bruce@enterprisedt.com
 */

package com.enterprisedt.net.ftp;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.util.Vector;

/**
 *  Supports client-side FTP operations
 *
 *  @author             Bruce Blackshaw
 *      @version        $Revision$
 *
 */
 public class FTPControlSocket {

     /**
      *   Standard FTP end of line sequence
      */
     static final String EOL = "\r\n";

     /**
      *   The default and standard control port number for FTP
      */
     public static final int CONTROL_PORT = 21;
     
     /**
      *   Used to flag messages
      */
     private static final String DEBUG_ARROW = "---> ";
     
     /**
      *   Start of password message
      */
     private static final String PASSWORD_MESSAGE = DEBUG_ARROW + "PASS";
     
     /**
      * Use strict return codes if true
      */
     private boolean strictReturnCodes = true;

     /**
      *  The underlying socket.
      */
     protected Socket controlSock = null;

     /**
      *  The write that writes to the control socket
      */
	 protected Writer writer = null;

     /**
      *  The reader that reads control data from the
      *  control socket
      */
     protected BufferedReader reader = null;
     
     /**
      * Message listener
      */
     private FTPMessageListener messageListener = null;
     
     /**
      *   Constructor. Performs TCP connection and
      *   sets up reader/writer. Allows different control
      *   port to be used
      *
      *   @param   remoteAddr       Remote inet address
      *   @param   controlPort      port for control stream
      *   @param   millis           the length of the timeout, in milliseconds
      *   @param   encoding         character encoding used for data
      *   @param   messageListener  listens for messages
      */
     FTPControlSocket(InetAddress remoteAddr, int controlPort, int timeout, 
                      String encoding, FTPMessageListener messageListener)
         throws IOException, FTPException {
         
         this(new Socket(remoteAddr, controlPort), timeout, encoding, messageListener);
     }

    /**
     * Constructs a new <code>FTPControlSocket</code> using the given
     * <code>Socket</code> object.
     * 
	 * @param controlSock      Socket to be used. 
	 * @param timeout          Timeout to be used.
     * @param encoding         character encoding used for data
     * @param messageListener  listens for messages
     * 
	 * @throws IOException Thrown if no connection response could be read from the server.
	 * @throws FTPException Thrown if the incorrect connection response was sent by the server.
	 */
	protected FTPControlSocket(Socket controlSock, int timeout, 
                                String encoding, FTPMessageListener messageListener)
		throws IOException, FTPException {
         
		this.controlSock = controlSock;
        this.messageListener = messageListener;
         
		setTimeout(timeout);
		initStreams(encoding);
		validateConnection();
    }

     /**
      *   Checks that the standard 220 reply is returned
      *   following the initiated connection
      */
     private void validateConnection()
         throws IOException, FTPException {

         FTPReply reply = readReply();
         validateReply(reply, "220");
     }


     /**
      *  Obtain the reader/writer streams for this
      *  connection
      *
      * @param encoding    character encoding used for data
      */
     private void initStreams(String encoding)
         throws IOException {

         // input stream
         InputStream is = controlSock.getInputStream();
         reader = new BufferedReader(new InputStreamReader(is, encoding));

         // output stream
         OutputStream os = controlSock.getOutputStream();
         writer = new OutputStreamWriter(os, encoding);
     }

     /**
      *  Get the name of the remote host
      *
      *  @return  remote host name
      */
     String getRemoteHostName() {
         InetAddress addr = controlSock.getInetAddress();
         return addr.getHostName();
     }
     
     /**
      * Set strict checking of FTP return codes. If strict 
      * checking is on (the default) code must exactly match the expected 
      * code. If strict checking is off, only the first digit must match.
      * 
      * @param strict    true for strict checking, false for loose checking
      */
     void setStrictReturnCodes(boolean strict) {
         this.strictReturnCodes = strict;
     }
     

    /**
     *   Set the TCP timeout on the underlying control socket.
     *
     *   If a timeout is set, then any operation which
     *   takes longer than the timeout value will be
     *   killed with a java.io.InterruptedException.
     *
     *   @param millis The length of the timeout, in milliseconds
     */
    void setTimeout(int millis)
        throws IOException {

        if (controlSock == null)
            throw new IllegalStateException(
                        "Failed to set timeout - no control socket");

        controlSock.setSoTimeout(millis);
    }
    
    
    /**
     * Set a listener that handles all FTP messages
     * 
     * @param listener  message listener
     */
    void setMessageListener(FTPMessageListener listener) {
        this.messageListener = listener;
    }

    /**
     *  Quit this FTP session and clean up.
     */
    public void logout()
        throws IOException {

        IOException ex = null;
        try {
            writer.close();
        }
        catch (IOException e) {
            ex = e;
        }
        try {
            reader.close();
        }
        catch (IOException e) {
            ex = e;
        }
        try {
            controlSock.close();
        }
        catch (IOException e) {
            ex = e;
        }
        if (ex != null)
            throw ex;
     }
              
     /**
      *  Request a data socket be created on the
      *  server, connect to it and return our
      *  connected socket.
      *
      *  @param  active   if true, create in active mode, else
      *                   in passive mode
      *  @return  connected data socket
      */
     FTPDataSocket createDataSocket(FTPConnectMode connectMode)
         throws IOException, FTPException {

        if (connectMode == FTPConnectMode.ACTIVE) {
            return createDataSocketActive();
        }
        else { // PASV
            return createDataSocketPASV();
        }
     }        

     /**
      *  Request a data socket be created on the Client
      *  client on any free port, do not connect it to yet.
      *
      *  @return  not connected data socket
      */
	 FTPDataSocket createDataSocketActive()
         throws IOException, FTPException {

        // use any available port
		FTPDataSocket socket = newActiveDataSocket(0);

        // get the local address to which the control socket is bound.
        InetAddress localhost =  controlSock.getLocalAddress();

        // send the PORT command to the server
        setDataPort(localhost, (short)socket.getLocalPort());

        return socket;
     }         
     
    /**
     *  Helper method to convert a byte into an unsigned short value
     *
     *  @param  value   value to convert
     *  @return  the byte value as an unsigned short
     */
    private short toUnsignedShort(byte value) {
        return ( value < 0 )
            ? (short) (value + 256)
            : (short) value;
     }

    /**
     *  Convert a short into a byte array
     *
     *  @param  value   value to convert
     *  @return  a byte array
     */
    protected byte[] toByteArray (short value) {

        byte[] bytes = new byte[2];
        bytes[0] = (byte) (value >> 8);     // bits 1- 8
        bytes[1] = (byte) (value & 0x00FF); // bits 9-16
        return bytes;
    }


    /**
     *  Sets the data port on the server, i.e. sends a PORT
     *  command
     *
     *  @param  host    the local host the server will connect to
     *  @param  portNo  the port number to connect to
     */
    private void setDataPort(InetAddress host, short portNo)
        throws IOException, FTPException {

        byte[] hostBytes = host.getAddress();
        byte[] portBytes = toByteArray(portNo);

        // assemble the PORT command
        String cmd = new StringBuffer ("PORT ")
            .append (toUnsignedShort (hostBytes[0])) .append (",")
            .append (toUnsignedShort (hostBytes[1])) .append (",")
            .append (toUnsignedShort (hostBytes[2])) .append (",")
            .append (toUnsignedShort (hostBytes[3])) .append (",")
            .append (toUnsignedShort (portBytes[0])) .append (",")
            .append (toUnsignedShort (portBytes[1])) .toString ();

        // send command and check reply
        FTPReply reply = sendCommand(cmd);
        validateReply(reply, "200");
     }

     /**
      *  Request a data socket be created on the
      *  server, connect to it and return our
      *  connected socket.
      *
      *  @return  connected data socket
      */
     FTPDataSocket createDataSocketPASV()
         throws IOException, FTPException {

         // PASSIVE command - tells the server to listen for
         // a connection attempt rather than initiating it
         FTPReply replyObj = sendCommand("PASV");
         validateReply(replyObj, "227");
         String reply = replyObj.getReplyText();

         // The reply to PASV is in the form:
         // 227 Entering Passive Mode (h1,h2,h3,h4,p1,p2).
         // where h1..h4 are the IP address to connect and
         // p1,p2 the port number
         // Example:
         // 227 Entering Passive Mode (128,3,122,1,15,87).
         // NOTE: PASV command in IBM/Mainframe returns the string
         // 227 Entering Passive Mode 128,3,122,1,15,87	(missing 
         // brackets)

         // extract the IP data string from between the brackets
         int startIP = reply.indexOf('(');
         int endIP = reply.indexOf(')');

         // allow for IBM missing brackets around IP address
         if (startIP < 0 && endIP < 0) {
             startIP = reply.toUpperCase().lastIndexOf("MODE") + 4;
             endIP = reply.length();
         }
                  
         String ipData = reply.substring(startIP+1,endIP);
         int parts[] = new int[6];

         int len = ipData.length();
         int partCount = 0;
         StringBuffer buf = new StringBuffer();

         // loop thru and examine each char
         for (int i = 0; i < len && partCount <= 6; i++) {

             char ch = ipData.charAt(i);
             if (Character.isDigit(ch))
                 buf.append(ch);
             else if (ch != ',') {
                 throw new FTPException("Malformed PASV reply: " + reply);
             }

             // get the part
             if (ch == ',' || i+1 == len) { // at end or at separator
                 try {
                     parts[partCount++] = Integer.parseInt(buf.toString());
                     buf.setLength(0);
                 }
                 catch (NumberFormatException ex) {
                     throw new FTPException("Malformed PASV reply: " + reply);
                 }
             }
         }

         // assemble the IP address
         // we try connecting, so we don't bother checking digits etc
         String ipAddress = parts[0] + "."+ parts[1]+ "." +
             parts[2] + "." + parts[3];

         // assemble the port number
         int port = (parts[4] << 8) + parts[5];

         // create the socket
         return newPassiveDataSocket(ipAddress, port);
	}
	 
	 /*
	 private int[] parsePASVResponse(String response)  throws FTPException {
		 int startIP = 0;
		 for (int i = 4; i < response.length(); i++) {
			 if (Character.isDigit(response.charAt(i))) { startIP = i; break; }
		 }
		 
		 int i;
		 int j = startIP;
		 int parts[] = new int[6];        
		 for (i = 0; i < 6; i++) {
			 StringBuffer buf = new StringBuffer();
			 for (; j < response.length(); j++) {
				 char c = response.charAt(j);
				 if (Character.isDigit(c)) {
					 buf.append(c);
				 } else if (i < 5 && c != ',') {
					 throw new FTPException("Malformed PASV reply: " + response);
				 } else {
					 j += 1;
					 break;
				 }
			 }
			 if (buf.length() == 0) throw new FTPException("Malformed PASV reply: " + response);
			 parts[i] = new Integer(buf.toString()).intValue();
		 }
		 return parts;
	 }
	  */
	 
	 /**
		 * Constructs a new <code>FTPDataSocket</code> object (client mode) and connect
		 * to the given remote host and port number.
		 * 
		 * @param remoteHost Remote host to connect to.
		 * @param port Remote port to connect to.
		 * @return A new <code>FTPDataSocket</code> object (client mode) which is
		 * connected to the given server.
		 * @throws IOException Thrown if no TCP/IP connection could be made. 
		 */
	 protected FTPDataSocket newPassiveDataSocket(String remoteHost, int port) 
		 throws IOException {
	            
			 return new FTPPassiveDataSocket(new Socket(remoteHost, port));
		 }
	 
	 /**
		 * Constructs a new <code>FTPDataSocket</code> object (server mode) which will
		 * listen on the given port number.
		 * 
		 * @param port Remote port to listen on.
		 * @return A new <code>FTPDataSocket</code> object (server mode) which is
		 *         configured to listen on the given port.
		 * @throws IOException Thrown if an error occurred when creating the socket. 
		 */
     protected FTPDataSocket newActiveDataSocket(int port) 
		 throws IOException {
			 
			 return new FTPActiveDataSocket(new ServerSocket(port));
		 }
     
     /**
		 *  Send a command to the FTP server and
		 *  return the server's reply as a structured
		 *  reply object
		 * 
		 *  @param command   command to send
		 *
		 *  @return  reply to the supplied command
		 */
     public FTPReply sendCommand(String command)
         throws IOException {
			 
         writeCommand(command);
         
         // and read the result
         return readReply();
     }
     
     /**
      *  Send a command to the FTP server. Don't
      *  read the reply
      *
      *  @param command   command to send
      */     
     void writeCommand(String command)
         throws IOException {
         
         log(command, true);
         
         // send it
         writer.write(command + EOL);
         writer.flush();     
     }
     
     /**
      *  Read the FTP server's reply to a previously
      *  issued command. RFC 959 states that a reply
      *  consists of the 3 digit code followed by text.
      *  The 3 digit code is followed by a hyphen if it
      *  is a muliline response, and the last line starts
      *  with the same 3 digit code.
      *
      *  @return  structured reply object
      */
     FTPReply readReply()
         throws IOException {
         
         String line = reader.readLine();
         if (line == null || line.length() == 0)
             throw new IOException("Unexpected null reply received");
         
         log(line, false);
         
         String replyCode = line.substring(0, 3);
         StringBuffer reply = new StringBuffer("");
         if (line.length() > 3)
             reply.append(line.substring(4));
                  
         Vector dataLines = null;

         // check for multiline response and build up
         // the reply
         if (line.charAt(3) == '-') {
             dataLines = new Vector();
             boolean complete = false;
             while (!complete) {
                 line = reader.readLine();
                 if (line == null)
                     throw new IOException("Unexpected null reply received");
                 
                 log(line, false);
                 
                 if (line.length() > 3 &&
                         line.substring(0, 3).equals(replyCode) &&
                         line.charAt(3) == ' ') {
                     reply.append(line.substring(3));
                     complete = true;
                 }
                 else { // not the last line
                     reply.append(" ").append(line);
                     dataLines.addElement(line);
                 }
             } // end while
         } // end if
         
         if (dataLines != null) {
             String[] data = new String[dataLines.size()];
             dataLines.copyInto(data);
             return new FTPReply(replyCode, reply.toString(), data);
         }
         else {
             return new FTPReply(replyCode, reply.toString());
         }
     }
    
    
     /**
      *  Validate the response the host has supplied against the
      *  expected reply. If we get an unexpected reply we throw an
      *  exception, setting the message to that returned by the
      *  FTP server
      *
      *  @param   reply              the entire reply string we received
      *  @param   expectedReplyCode  the reply we expected to receive
      *
      */
     FTPReply validateReply(String reply, String expectedReplyCode)
         throws FTPException {
    
         FTPReply replyObj = new FTPReply(reply);
         
         if (validateReplyCode(replyObj, expectedReplyCode))
             return replyObj;
             
         // if unexpected reply, throw an exception
         throw new FTPException(replyObj);         
     }
    
     
     /**
      *  Validate the response the host has supplied against the
      *  expected reply. If we get an unexpected reply we throw an
      *  exception, setting the message to that returned by the
      *  FTP server
      *
      *  @param   reply               the entire reply string we received
      *  @param   expectedReplyCodes  array of expected replies
      *  @return  an object encapsulating the server's reply
      *
      */
     public FTPReply validateReply(String reply, String[] expectedReplyCodes)
         throws IOException, FTPException {
         
         FTPReply replyObj = new FTPReply(reply);        
         return validateReply(replyObj, expectedReplyCodes);
     }
     
     
     /**
      *  Validate the response the host has supplied against the
      *  expected reply. If we get an unexpected reply we throw an
      *  exception, setting the message to that returned by the
      *  FTP server
      *
      *  @param   reply               reply object
      *  @param   expectedReplyCodes  array of expected replies
      *  @return  reply object
      *
      */
     public FTPReply validateReply(FTPReply reply, String[] expectedReplyCodes)
         throws FTPException {
                  
         for (int i = 0; i < expectedReplyCodes.length; i++)
             if (validateReplyCode(reply, expectedReplyCodes[i]))
                 return reply;
             
             // got this far, not recognised
         throw new FTPException(reply);  
     }
     
     /**
      *  Validate the response the host has supplied against the
      *  expected reply. If we get an unexpected reply we throw an
      *  exception, setting the message to that returned by the
      *  FTP server
      *
      *  @param   reply               reply object
      *  @param   expectedReplyCode   expected reply
      *  @return  reply object
      *
      */
     public FTPReply validateReply(FTPReply reply, String expectedReplyCode)
         throws FTPException {
                  
         if (validateReplyCode(reply, expectedReplyCode))
                 return reply;
             
         // got this far, not recognised
         throw new FTPException(reply);  
     }
     
     /**
      * Validate reply object
      * 
      * @param reply                reference to reply object
      * @param expectedReplyCode    expect reply code
      * @return true if valid, false if invalid
      */
     private boolean validateReplyCode(FTPReply reply, String expectedReplyCode) {
         
         String replyCode = reply.getReplyCode();
         if (strictReturnCodes) {
             if (replyCode.equals(expectedReplyCode)) 
                 return true;
             else
                 return false;
         }
         else { // non-strict - match first char
             if (replyCode.charAt(0) == expectedReplyCode.charAt(0))
                 return true;
             else
                 return false;
         }         
     }
        
    
     /**
      *  Log a message, checking for passwords
      * 
      *  @param msg	message to log
      *  @param reply  true if a response, false otherwise
      */
     void log(String msg, boolean command) {
  	 	 if (msg.startsWith(PASSWORD_MESSAGE))
 	     	 msg = PASSWORD_MESSAGE+" ********";
         if (messageListener != null)
             if (command)
                 messageListener.logCommand(msg);
             else
                 messageListener.logReply(msg);
         
     }
}


