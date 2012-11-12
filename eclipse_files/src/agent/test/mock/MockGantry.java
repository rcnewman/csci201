package agent.test.mock;

import agent.FeederAgent;
import agent.data.Bin;
import factory.PartType;
import agent.interfaces.Gantry;

/**
 * Mock Gantry
 * @author Daniel Paje
 */
public class MockGantry extends MockAgent implements Gantry {

	public EventLog log;

	public MockGantry(String name) {
		super(name, new EventLog());
		this.log = super.getLog();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void msgHereIsBin(Bin bin) {
		// TODO Auto-generated method stub
		log.add(new LoggedEvent("Recieved msgHereIsBinConfig"));
	}

	@Override
	public void msgINeedParts(PartType type, FeederAgent feeder) {
		// TODO Auto-generated method stub

	}

	@Override
	public void msgReceiveBinDone(Bin bin) {
		// TODO Auto-generated method stub

	}

	@Override
	public void msgDropBinDone(Bin bin) {
		// TODO Auto-generated method stub

	}

	@Override
	public void msgRemoveBinDone(Bin bin) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean pickAndExecuteAnAction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void moveToFeeder(Bin bin, FeederAgent feeder) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fillFeeder(Bin bin, FeederAgent feeder) {
		// TODO Auto-generated method stub

	}

	@Override
	public void discardBin(Bin bin) {
		// TODO Auto-generated method stub

	}

}
