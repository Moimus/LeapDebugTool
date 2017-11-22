import java.io.IOException;

import com.leapmotion.leap.*;

public class Main {
	
	public static void main(String[] args)
	{
		Controller controller = new Controller();
		SampleListener sampleListener = new SampleListener();
		controller.addListener(sampleListener);
		System.out.println("Controller init done!");
		
        // Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove the sample listener when done
        controller.removeListener(sampleListener);
	}

}
