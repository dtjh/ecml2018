package cutpointdetection;

public interface CutPointDetector
{    
    public boolean setInput(double d);
    
    public void resetLearning();
    
    public String getDescription();
    
    public String getParams();
}
