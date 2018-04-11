package cutpointdetection;

public class ADWINChangeDetector implements CutPointDetector {

    public ADWINChangeDetector(double delta)
    {
	this.deltaAdwinOption = delta;
    }
    
    protected ADWIN adwin;

    public double deltaAdwinOption = 0.05;

    @Override
    public boolean setInput(double d) {
        if (this.adwin == null) {
            resetLearning();
        }
        return adwin.setInput(d);
    }

    @Override
    public void resetLearning() {
        adwin = new ADWIN((double) this.deltaAdwinOption);
    }

    @Override
    public String getDescription()
    {
	// TODO Auto-generated method stub
	return "ADWIN";
    }

    @Override
    public String getParams()
    {
	// TODO Auto-generated method stub
	return null;
    }
}
