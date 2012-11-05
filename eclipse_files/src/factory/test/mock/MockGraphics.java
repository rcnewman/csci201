package factory.test.mock;

import java.util.Timer;
import java.util.TimerTask;

import DeviceGraphics.BinGraphics;
import DeviceGraphics.KitGraphics;
import DeviceGraphics.PartGraphics;
import GraphicsInterfaces.CameraGraphics;
import GraphicsInterfaces.ConveyorGraphics;
import GraphicsInterfaces.FeederGraphics;
import GraphicsInterfaces.GantryGraphics;
import GraphicsInterfaces.KitRobotGraphics;
import GraphicsInterfaces.LaneGraphics;
import GraphicsInterfaces.NestGraphics;
import GraphicsInterfaces.PartsRobotGraphics;
import agent.Agent;
import factory.CameraAgent;
import factory.ConveyorAgent;
import factory.FeederAgent;
import factory.GantryAgent;
import factory.KitRobotAgent;
import factory.LaneAgent;
import factory.NestAgent;
import factory.PartsRobotAgent;
import factory.data.Part;
import factory.interfaces.Feeder;
import factory.interfaces.Gantry;
import factory.interfaces.Lane;
import factory.interfaces.Nest;

;

public class MockGraphics extends Agent implements CameraGraphics,
		ConveyorGraphics, FeederGraphics, GantryGraphics, KitRobotGraphics,
		LaneGraphics, NestGraphics, PartsRobotGraphics {

	Timer timer;
	String name;

	// Agents
	CameraAgent camera;
	ConveyorAgent conveyor;
	FeederAgent feeder;
	GantryAgent gantry;
	KitRobotAgent kitrobot;
	LaneAgent lane;
	NestAgent nest;
	PartsRobotAgent partsrobot;

	// Graphics
	KitRobotGraphics kitrobotgraphics;
	ConveyorGraphics conveyorgraphics;

	public MockGraphics(String name) {
		super();

		timer = new Timer();
		this.name = name;
		// Set server to null
		conveyorgraphics = new DeviceGraphics.ConveyorGraphics(null);

		camera = new CameraAgent();
		conveyor = new ConveyorAgent("conveyor");
		feeder = new FeederAgent("feeder");
		gantry = new GantryAgent("gantry");
		kitrobot = new KitRobotAgent("kitrobot");
		lane = new LaneAgent("lane");
		nest = new NestAgent("nest");
		// partsrobot = new PartsRobotAgent();

		camera.startThread();
		conveyor.startThread();
		feeder.startThread();
		gantry.startThread();
		kitrobot.startThread();
		lane.startThread();
		nest.startThread();
		// partsrobot.startThread();
		// kitrobotgraphics = new DeviceGraphics.KitRobotGraphics(null);

	}

	@Override
	public void pickUpPart(PartGraphics part) {
		// TODO Auto-generated method stub

	}

	@Override
	public void givePartToKit(KitGraphics kit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void givePartToPartsRobot(PartGraphics part) {
		// TODO Auto-generated method stub

	}

	@Override
	public void receivePart(PartGraphics part) {
		// TODO Auto-generated method stub

	}

	@Override
	public void purge() {
		// TODO Auto-generated method stub

	}

	@Override
	public void msgPlaceKitOnStand(KitGraphics kit, int location) {
		print("KitRobotGraphics received message msgPlaceKitOnStand");
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				print("KitRobotGraphics sending message msgPlaceKitOnStandDone() to KitRobot after 100ms");
				kitrobot.msgPlaceKitOnStandDone();
			}
		}, 100);

	}

	@Override
	public void msgPlaceKitInInspectionArea(KitGraphics kit) {
		print("KitRobotGraphics received message placeKitInInspectionArea");
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				print("KitRobotGraphics sending message placeKitInInspectionAreaDone() to KitRobot after 100ms");
				kitrobot.msgPlaceKitInInspectionAreaDone();
			}
		}, 100);
	}

	@Override
	public void msgPlaceKitOnConveyor() {
		print("KitRobotGraphics received message msgPlaceKitOnConveyor");

		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				print("KitRobotGraphics sending message placeKitOnConveyorDone() to KitRobot after 100ms");
				kitrobot.msgPlaceKitOnConveyorDone();
			}
		}, 100);
	}

	@Override
	public void dropBin(BinGraphics bin, FeederGraphics feeder) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeBin(BinGraphics bin) {
		// TODO Auto-generated method stub

	}

	@Override
	public void receiveBin(BinGraphics bin) {
		// TODO Auto-generated method stub

	}

	@Override
	public void purgeBin(BinGraphics bin) {
		// TODO Auto-generated method stub

	}

	@Override
	public void movePartToDiverter(PartGraphics part) {
		// TODO Auto-generated method stub

	}

	@Override
	public void flipDiverter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void movePartToLane(PartGraphics part) {
		// TODO Auto-generated method stub

	}

	@Override
	public void msgBringEmptyKit(KitGraphics kit) {
		print("ConveyorGraphics received message msgBringEmptyKit");

		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				print("ConveyorGraphics sending message msgBringEmptyKitDone() to conveyor after 100ms");
				conveyor.msgBringEmptyKitDone();
			}
		}, 100);

	}

	@Override
	public void msgGiveKitToKitRobot(KitGraphics kit) {
		print("ConveyorGraphics received message msgGiveKitToKitRobot");

		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				print("ConveyorGraphics sending message msgGiveKitToKitRobotDone() to conveyor after 100ms");
				conveyor.msgGiveKitToKitRobotDone();
			}
		}, 100);

	}

	@Override
	public void msgReceiveKit(KitGraphics kit) {
		print("ConveyorGraphics received message msgReceiveKit");

		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				print("ConveyorGraphics sending message msgReceiveKitDone() to conveyor after 100ms");
				conveyor.msgReceiveKitDone();
			}
		}, 100);

	}

	@Override
	public void takeKitPhoto(KitGraphics kit) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean pickAndExecuteAnAction() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * Accessors and mutators
	 */

	public CameraAgent getCamera() {
		return camera;
	}

	public void setCamera(CameraAgent camera) {
		this.camera = camera;
	}

	public ConveyorAgent getConveyor() {
		return conveyor;
	}

	public void setConveyor(ConveyorAgent conveyor) {
		this.conveyor = conveyor;
	}

	public Feeder getFeeder() {
		return feeder;
	}

	public void setFeeder(FeederAgent feeder) {
		this.feeder = feeder;
	}

	public Gantry getGantry() {
		return gantry;
	}

	public void setGantry(GantryAgent gantry) {
		this.gantry = gantry;
	}

	public KitRobotAgent getKitrobot() {
		return kitrobot;
	}

	public void setKitrobot(KitRobotAgent kitrobot) {
		this.kitrobot = kitrobot;
	}

	public Lane getLane() {
		return lane;
	}

	public void setLane(LaneAgent lane) {
		this.lane = lane;
	}

	public Nest getNest() {
		return nest;
	}

	public void setNest(NestAgent nest) {
		this.nest = nest;
	}

	public PartsRobotAgent getPartsrobot() {
		return partsrobot;
	}

	public void setPartsrobot(PartsRobotAgent partsrobot) {
		this.partsrobot = partsrobot;
	}

	public KitRobotGraphics getKitrobotgraphics() {
		return kitrobotgraphics;
	}

	public void setKitrobotgraphics(KitRobotGraphics kitrobotgraphics) {
		this.kitrobotgraphics = kitrobotgraphics;
	}

	public ConveyorGraphics getConveyorgraphics() {
		return conveyorgraphics;
	}

	public void setConveyorgraphics(ConveyorGraphics conveyorgraphics) {
		this.conveyorgraphics = conveyorgraphics;
	}

	@Override
	public void receivePart(Part part) {
		// TODO Auto-generated method stub

	}

	@Override
	public void givePartToNest(Part part) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void takeNestPhoto(NestGraphics nest1) {
		// TODO Auto-generated method stub

	}

}
