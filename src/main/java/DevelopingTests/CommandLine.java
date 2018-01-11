package DevelopingTests;

import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;

public class CommandLine {


        void StartAppium () throws Exception {

            /* appium start */
            org.apache.commons.exec.CommandLine command = new org.apache.commons.exec.CommandLine("cmd");
            command.addArgument("/c");
            command.addArgument("C://Users//....//Appium//node.exe");
            command.addArgument("C://Users//...//Appium//node_modules//appium//bin//appium.js");
            command.addArgument("--address");
            command.addArgument("127.0.0.1");
            command.addArgument("--port");
            command.addArgument("4723");
            command.addArgument("--no-reset");
            command.addArgument("--log");
            command.addArgument("C://Users//...//log//appiumLogs.txt");
            DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
            DefaultExecutor executor = new DefaultExecutor();
            executor.setExitValue(1);
            executor.execute(command, resultHandler);

        }

}
