package Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class ConsoleWriter {
    public static String consoleID = "";
    private String name = "";
    URLConnection connection;
    Semaphore stateChange = new Semaphore(1, true);// binary semaphore, fair
    private ConsoleWriterThread consoleWriterThread;

    // The backing data structure will be blocking queue
    private final Queue<String> messageQueue;

    public ConsoleWriter(String name) {
	this.name = name;
	messageQueue = new LinkedBlockingQueue<String>();
    }

    /**
     * This should be called whenever state has changed that might cause the console writer to do something.
     */
    protected void stateChanged() {
	stateChange.release();
    }

    protected void print(String msg) {
	print(msg, null);
    }

    /** Print message with exception stack trace */
    protected void print(String msg, Throwable e) {
	StringBuffer sb = new StringBuffer();
	sb.append("[ConsoleWriter]");
	sb.append(": ");
	sb.append(msg);
	sb.append("\n");
	if (e != null) {
	    sb.append(StringUtil.stackTraceString(e));
	}

	System.out.print(sb.toString());
    }

    /**
     * Console writer scheduler thread, calls sendingMessage() whenever a state change has been signaled.
     */
    private class ConsoleWriterThread extends Thread {
	private volatile boolean goOn = false;

	private ConsoleWriterThread(String name) {
	    super(name);
	}

	@Override
	public void run() {
	    goOn = true;

	    while (goOn) {
		try {
		    stateChange.acquire();
		    while (sendingMessage()) {
			;
		    }
		} catch (InterruptedException e) {
		    // no action - expected when stopping or when deadline
		    // changed
		} catch (Exception e) {
		    print("Unexpected exception caught in ConsoleWriter thread: ", e);
		}
	    }
	}

	private void stopConsoleWriter() {
	    goOn = false;
	    this.interrupt();
	}
    }

    /**
     * Start consolewriter scheduler thread. Should be called once at init time.
     */
    public synchronized void startThread() {
	print("Thread started.");
	if (consoleWriterThread == null) {
	    consoleWriterThread = new ConsoleWriterThread("ConsoleWriter");
	} else {
	    consoleWriterThread.interrupt();
	}
    }

    /** Stop consolewriter scheduler thread. */
    public void stopThread() {
	print("Thread stopped");
	if (consoleWriterThread != null) {
	    consoleWriterThread.stopConsoleWriter();
	    consoleWriterThread = null;
	}
    }

    public void startConsole() {
	if (consoleID.equals("")) {
	    consoleID = sendData("start=y");
	}
	System.out.println("Console started: " + consoleID);
    }

    /*
     * Messages
     */

    /**
     * Called by agents in their print methods
     * 
     * @param sourceName agent's name
     * @param messag the message to be sent
     */
    public void sendMessage(String sourceName, String message) {
	message = "[" + sourceName + "] " + message;
	if (consoleID != "") {
	    message += "&consoleID=" + consoleID;
	}
	System.out.println("Sending " + message);
	messageQueue.add("message=" + message);
	stateChanged();
    }

    /*
     * Scheduler
     */
    public boolean sendingMessage() {
	if (messageQueue.size() > 0) {
	    sendData(messageQueue.remove());
	    return true;
	} else {
	    return false;
	}
    }

    /*
     * Actions
     */
    public String sendData(String data) {
	try {
	    connection = new URL("http://ptz-debug.appspot.com/listen/").openConnection();
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	connection.setDoOutput(true);
	connection.setRequestProperty("Accept-Charset", "UTF-8");
	connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
	try {
	    OutputStream output = connection.getOutputStream();
	    output.write(new String(data).getBytes("UTF-8"));

	    InputStream response = connection.getInputStream();
	    return read(response);
	} catch (Exception e) {
	    e.printStackTrace();
	    return "";
	}

    }

    public String read(InputStream response) {
	BufferedReader reader = null;
	try {
	    reader = new BufferedReader(new InputStreamReader(response, "UTF-8"));
	    return reader.readLine();
	} catch (Exception e) {
	    return "";
	}
    }
}