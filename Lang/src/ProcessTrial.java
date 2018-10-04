import java.io.IOException;

public class ProcessTrial {

    public static void main(String[] args) {
        ProcessTrial processTrial = new ProcessTrial();



//        processTrial.subProcess();
        processTrial.localProcess();
    }

    private void localProcess() {
//        Process process = Runtime.getRuntime();


    }



    private void subProcess() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        System.out.println(processBuilder);
//        try {
//            Process process = processBuilder.start();
//            System.out.println(process);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



    }
}


class OO {
    private synchronized void add1() {

        while (true) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void add2() {

        while (true) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}