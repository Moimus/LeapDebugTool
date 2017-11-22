import com.leapmotion.leap.*;
import com.leapmotion.leap.Bone.Type;

public class SampleListener extends Listener 
{

    public void onConnect(Controller controller) 
    {
        System.out.println("Connected");
    }

    public void onFrame(Controller controller) 
    {
    	
        System.out.println("Frame available");
        Frame frame = controller.frame();
        System.out.println("----------Frame: " + frame.id() + "----------");
        if(Debug.doesPeaceSign(frame, "right") == true)
        {
        	System.out.println("PEACE! Right");
        	System.exit(0);
        }
        else if(Debug.doesPeaceSign(frame, "left") == true)
        {
        	System.out.println("PEACE! Left");
        	System.exit(0);
        }
        
        else if(Debug.doesPointGesture(frame, "right", -150f) == true)
        {
			System.out.println("POINT! Right");
			System.exit(0);
        }
        else if(Debug.doesPointGesture(frame, "left", -150f) == true)
        {
			System.out.println("POINT! Left");
			System.exit(0);
        }
        Debug.GetHandData(frame,true);
        Debug.GetHandData(frame,false);
        //Debug.GetFingerData(frame);
        
        //V2 stuff, won't work in Orion. For demonstration only
        /*
        Frame frameV2 = controller.frame();
        GestureList gestures = frameV2.gestures();
        for(int n = 0; n < gestures.count(); n++)
        {
        	if(gestures.get(n).type().equals(Gesture.Type.TYPE_SCREEN_TAP) == true)
        	{
        		System.out.println("Gesture recognized: ScreenTap");
        		ScreenTapGesture tap = new ScreenTapGesture(gestures.get(n));
        		System.out.println("Tap direction" + tap.direction());
        	}
        	else if(gestures.get(n).type().equals(Gesture.Type.TYPE_CIRCLE) == true)
        	{
        		System.out.println("Gesture recognized: ScreenTap");
        		CircleGesture circle = new CircleGesture(gestures.get(n));
        		System.out.println("Circles drawn: " + circle.progress());
        	}
        }
        */
    }
    
}
