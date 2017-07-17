package com.telco;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author junior dev
 */
public class BaseStation implements Runnable, Serializable {

    public static final long serialVersionUID = 42L;

    /**
     * The amount of free connections in pool
     */
    public int connectionsPool = 100;
    public int id = 1;

    public BaseStation() {
        // This will guarantee that it reports of low amounts of free connections in the pool
        new Thread(this).run();
    }

    /**
     * Warn if number of available connections in the pool is low every five minutes
     */
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (connectionsPool < 10) {
                NotificationService.getInstance().sendEmail("operator@telco.com", "BaseStation " + id + " is really busy", "We're running out of free connection on base station!");
            } else {
                System.out.println("connection pool ok");
            }
        }
    }

    public int getConnectionsPool() {
        return connectionsPool;
    }

    public void extendConnectionPool(int amount) {
        this.connectionsPool += amount;
    }

    public Call makeCall() throws CallException {
        if (this.connectionsPool < 5) {
            throw new CallException("Not enough connections in the pool");
        }


        Call call = new Call();

        call.startsAt = new Date();
        call.callType = CallType.GSM;

        return call;
    }

    public void endCall(Call call) throws CallException {
        this.connectionsPool++;
    }

    //public void printConnectionPool() {
    //    System.out.println("Connection pool AMOUNT: " + this.connectionsPool);
    //}

    public static class Call {
        public CallType callType;
        public Date startsAt;
        public Date endsAt;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Call)) return false;

            Call call = (Call) o;

            if (startsAt != call.startsAt) return false;
            if (endsAt != call.endsAt) return false;
            if (callType != call.callType) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return (int)startsAt.getTime();
        }
    }

    /**
     * WE ONLY SUPPORT GSM, BUT WE MAY HAVE WCDMA AND LTE IN THE FUTURE.
     */
    public static enum CallType {
        GSM,
        WCDMA,
        LTE
    }

    /**
     * FOR CALL RELATED EXCEPTIONS!
     */
    public static class CallException extends Exception {
        public CallException(String msg) {
            super(msg);
        }
    }
}