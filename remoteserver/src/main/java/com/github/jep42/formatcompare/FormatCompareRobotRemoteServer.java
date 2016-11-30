package com.github.jep42.formatcompare;


import org.robotframework.remoteserver.RemoteServer;

import com.github.jep42.robotformatcompare.RobotFormatCompare;

public final class FormatCompareRobotRemoteServer {

	private static final String PATH = "/RobotFormatCompare";

	private static int port = 8270;

    private FormatCompareRobotRemoteServer() {
		super();
	}

	public static void main(String[] args) throws Exception {
    	parseArguments(args);

        org.robotframework.remoteserver.RemoteServer.configureLogging();
        RemoteServer server = new RemoteServer();
        server.setPort(port);
        server.putLibrary(PATH, new RobotFormatCompare());
        server.start();
    }

	private static void parseArguments(String[] args) {
		for (int i = 0; i < args.length; i++) {
			if ("--port".equalsIgnoreCase(args[i])) {
				port = Integer.parseInt(args[i + 1]);
			}
		}
	}

}
