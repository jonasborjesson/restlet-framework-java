/**
 * Copyright 2005-2009 Noelios Technologies.
 * 
 * The contents of this file are subject to the terms of one of the following
 * open source licenses: LGPL 3.0 or LGPL 2.1 or CDDL 1.0 or EPL 1.0 (the
 * "Licenses"). You can select the license that you prefer but you may not use
 * this file except in compliance with one of these Licenses.
 * 
 * You can obtain a copy of the LGPL 3.0 license at
 * http://www.opensource.org/licenses/lgpl-3.0.html
 * 
 * You can obtain a copy of the LGPL 2.1 license at
 * http://www.opensource.org/licenses/lgpl-2.1.php
 * 
 * You can obtain a copy of the CDDL 1.0 license at
 * http://www.opensource.org/licenses/cddl1.php
 * 
 * You can obtain a copy of the EPL 1.0 license at
 * http://www.opensource.org/licenses/eclipse-1.0.php
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * http://www.noelios.com/products/restlet-engine
 * 
 * Restlet is a registered trademark of Noelios Technologies.
 */

package org.restlet.engine.http.connector;

import java.io.IOException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;

import org.restlet.Server;

/**
 * Listens on the given socket channel for incoming connections and dispatches
 * them to the given handler pool
 * 
 * @author Jerome Louvel
 */
public class AcceptorTask implements Runnable {

    /** The parent server helper. */
    private final BaseServerHelper helper;

    /** The server socket channel to listen on. */
    private final ServerSocketChannel serverSocket;

    /**
     * The latch to countdown when the socket is ready to accept connections.
     */
    private final CountDownLatch latch;

    /**
     * Constructor.
     * 
     * @param helper
     *            The target server helper.
     * @param serverSocket
     *            The server socket channel to listen on.
     * @param latch
     *            The latch to countdown when the socket is ready to accept
     *            connections.
     * @param handlerService
     *            The handler service.
     */
    public AcceptorTask(BaseServerHelper helper,
            ServerSocketChannel serverSocket, CountDownLatch latch) {
        this.helper = helper;
        this.serverSocket = serverSocket;
        this.latch = latch;
    }

    /**
     * Returns the parent server helper.
     * 
     * @return The parent server helper.
     */
    protected BaseServerHelper getHelper() {
        return helper;
    }

    /**
     * Listens on the given server socket for incoming connections.
     */
    public void run() {
        this.latch.countDown();

        while (true) {
            try {
                SocketChannel client = this.serverSocket.accept();
                int connectionsCount = getHelper().getConnections().size();

                if ((getHelper().getMaxTotalConnections() == -1)
                        || (connectionsCount <= getHelper()
                                .getMaxTotalConnections())) {
                    Connection<Server> connection = getHelper()
                            .createConnection(getHelper(), client.socket());
                    connection.open();
                    getHelper().getConnections().add(connection);
                } else {
                    // Rejection connection
                    client.close();
                    getHelper()
                            .getLogger()
                            .info(
                                    "Maximum number of concurrent connections reached. New connection rejected.");
                }
            } catch (ClosedByInterruptException ex) {
                this.helper.getLogger().log(Level.FINE,
                        "ServerSocket channel was closed by interrupt", ex);
                break;
            } catch (IOException ex) {
                this.helper.getLogger().log(Level.WARNING,
                        "Unexpected error while accepting new connection", ex);
            }
        }
    }
}