import com.leapmotion.leap.*;

public class Debug {
	
	public static void GetHandData(Frame Frame, Boolean IsLeftHand)
	{
		String indent = "	";
		Hand hand = null;
		HandList handList = Frame.hands();
		String handOfInterest = null;
		if(IsLeftHand == true && handList.count() > 0)
		{
			if (Frame.hands().leftmost().isLeft() == true)
			{
				hand = Frame.hands().leftmost();
				handOfInterest = "left";
			}
			else
			{
				hand = null;
			}
		}
		else if(IsLeftHand == false && handList.count() > 0)
		{
			if(Frame.hands().rightmost().isRight() == true)
			{
				hand = Frame.hands().rightmost();
				handOfInterest = "right";
			}
			else
			{
				hand = null;
			}
		}
		
		if(hand != null)
		{
			//both hands
			System.out.println("HandData: " + handOfInterest);
			System.out.println(indent + "Hand recognized");
			System.out.println(indent + "HandOfInterest: " + handOfInterest);
			System.out.println(indent + "Hands count: " + handList.count());
			System.out.println(indent + "HandPitch: " + hand.direction().pitch());
			System.out.println(indent + "HandYaw: " + hand.direction().yaw());
			System.out.println(indent + "HandPalmXYZPos: " + hand.palmPosition());
			System.out.println(indent + "HandPalmVelocity: " + hand.palmVelocity());

		}
		else
		{
			if(IsLeftHand == true)
			{
				System.out.println("No left hand recognized!");
			}
			else
			{
				System.out.println("No right hand recognized!");
			}
			
		}
	}
	

	
	public static void GetFingerData(Frame Frame)
	{
		Hand leftHand = Frame.hands().leftmost();
		Hand rightHand = Frame.hands().rightmost();
		
		FingerList leftHandFingers = leftHand.fingers();
		FingerList rightHandFingers = rightHand.fingers();
		String indent = ("	");
		
		if (leftHand.isLeft())
		{
			//left hand
			System.out.println("FingerData: LeftHand");
			for(int n = 0; n < leftHandFingers.count(); n++)
			{
				Finger finger = leftHandFingers.get(n);
				System.out.println(indent + finger.type().toString());
				System.out.println(indent + indent +"isExtended: " + finger.isExtended());
				System.out.println(indent + indent + "tipPosition" + finger.tipPosition());
				System.out.println(indent + indent + "tipVelocity" + finger.tipVelocity());
				System.out.println(indent + indent + "tipDirection" + finger.direction());
				System.out.println(indent + indent + "tipStabilizedVelocity: " + finger.stabilizedTipPosition());
				System.out.println(indent + indent +"Bone_DistalDirection: " + finger.bone(Bone.Type.TYPE_DISTAL).direction());
				System.out.println(indent + indent +"Bone_DistalLength: " + finger.bone(Bone.Type.TYPE_DISTAL).length());
				System.out.println(indent + indent +"Bone_DistalWidth: " + finger.bone(Bone.Type.TYPE_DISTAL).width());
				System.out.println(indent + indent +"Bone_DistalPitch: " + finger.bone(Bone.Type.TYPE_DISTAL).direction().pitch());
			}
		}
		
		if (rightHand.isRight())
		{
			//right hand
			System.out.println("FingerData: RightHand");
			for(int n = 0; n < rightHandFingers.count(); n++)
			{
				Finger finger = rightHandFingers.get(n);
				System.out.println(indent + finger.type().toString());
				System.out.println(indent + indent +"isExtended: " + finger.isExtended());
				System.out.println(indent + indent + "tipPosition" + finger.tipPosition());
				System.out.println(indent + indent + "tipVelocity" + finger.tipVelocity());
				System.out.println(indent + indent + "tipStabilizedVelocity: " + finger.stabilizedTipPosition());
				System.out.println(indent + indent +"Bone_DistalDirection: " + finger.bone(Bone.Type.TYPE_DISTAL).direction());
				System.out.println(indent + indent +"Bone_DistalLength: " + finger.bone(Bone.Type.TYPE_DISTAL).length());
				System.out.println(indent + indent +"Bone_DistalWidth: " + finger.bone(Bone.Type.TYPE_DISTAL).width());
				System.out.println(indent + indent +"Bone_DistalPitch: " + finger.bone(Bone.Type.TYPE_DISTAL).direction().pitch());
			}
		}
	}
	
	public static Boolean doesPointGesture(Frame Frame, String HandOfInterest, Float VelocityThreshold)
	{
		Boolean gestureDetected = false;
		
		if(HandOfInterest.equals("left"))
		{
			if (Frame.hands().leftmost().isLeft() == true)
			{
				Hand hand = Frame.hands().leftmost();
				FingerList fingers = hand.fingers();
				if(fingers.get(1).isExtended() && hand.direction().pitch() > 0.4 && hand.direction().pitch() < 0.5 && fingers.get(1).tipVelocity().getZ() < VelocityThreshold)
				{
					if(!fingers.get(0).isExtended() && !fingers.get(2).isExtended() && !fingers.get(3).isExtended() && !fingers.get(4).isExtended())
					{
						System.out.println("Point PositionXY ( " + fingers.get(1).tipPosition().getX() + " | " + fingers.get(1).tipPosition().getY() + " )");
						gestureDetected = true;
					}
				}
			}
		}
		else if (HandOfInterest.equals("right"))
		{
			if (Frame.hands().rightmost().isRight() == true)
			{
				Hand hand = Frame.hands().rightmost();
				FingerList fingers = hand.fingers();
				if(fingers.get(1).isExtended() && hand.direction().pitch() > 0.4 && hand.direction().pitch() < 0.5 && fingers.get(1).tipVelocity().getZ() < VelocityThreshold)
				{
					if(!fingers.get(0).isExtended() && !fingers.get(2).isExtended() && !fingers.get(3).isExtended() && !fingers.get(4).isExtended())
					{
						System.out.println("Point PositionXY ( " + fingers.get(1).tipPosition().getX() + " | " + fingers.get(1).tipPosition().getY() + " )");
						gestureDetected = true;
					}
				}
			}
		}
		
		return gestureDetected;
	}
	
	public static Boolean doesPeaceSign(Frame Frame, String HandOfInterest)
	{
		Boolean gestureDetected = false;
		if(HandOfInterest.equals("left"))
		{
			if (Frame.hands().leftmost().isLeft() == true)
			{
				Hand hand = Frame.hands().leftmost();
				FingerList fingers = hand.fingers();
				if(fingers.get(1).isExtended() && fingers.get(2).isExtended() && hand.direction().pitch() > 0.8)
				{
					if(!fingers.get(0).isExtended() && !fingers.get(3).isExtended() && !fingers.get(4).isExtended())
					{
						gestureDetected = true;
					}
				}
			}
			else
			{
				
			}
		}
		else if (HandOfInterest.equals("right"))
		{
			if (Frame.hands().rightmost().isRight() == true)
			{
				Hand hand = Frame.hands().rightmost();
				FingerList fingers = hand.fingers();
				if(fingers.get(1).isExtended() && fingers.get(2).isExtended() && hand.direction().pitch() > 0.8)
				{
					if(!fingers.get(0).isExtended() && !fingers.get(3).isExtended() && !fingers.get(4).isExtended())
					{
						gestureDetected = true;
					}
				}
			}
			else
			{
				
			}
		}
		
		return gestureDetected;
	}

}
