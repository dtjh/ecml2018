import java.awt.Point;
import java.util.ArrayList;
import cutpointdetection.CutPointDetector;

public class ConceptDriftEnsemble implements CutPointDetector
{
	private int size;
	private CutPointDetector[] ensemble;
	private ArrayList<Point> resultSet;
	private int t;

	private double param_k = 2;
	private double param_windowSize = 500;

	public ConceptDriftEnsemble(CutPointDetector[] ensemble, double k, double windowSize)
	{
		this.ensemble = ensemble;
		this.size = ensemble.length;
		this.param_k = k;
		this.param_windowSize = windowSize;

		this.resultSet = new ArrayList<Point>();
		this.t = 0;
	}

	public boolean setInput(double input)
	{
		t++;
		boolean isChange = false;
		for (int i = 0; i < size; i++)
		{
			if(ensemble[i].setInput(input) == true)
			{
				Point point = new Point(i,t);
				resultSet.add(point);
				isChange = aggregationFunction(this.resultSet);
			}
		}	

		return isChange;
	}

	public void resetLearning()
	{
		this.resultSet = new ArrayList<Point>();

		for(CutPointDetector det : ensemble)
		{
			det.resetLearning();
		}
	}

	public boolean aggregationFunction(ArrayList<Point> resultSet)
	{
		int size = resultSet.size();
		Point[] normalizedDistance = new Point[size];

		Point anchor = resultSet.get(size-1);

		for (int k = 0; k < size; k++)
		{
			Point currentPt = new Point();
			currentPt.x = resultSet.get(k).x;
			currentPt.y = (Math.abs(anchor.y - resultSet.get(k).y)) < param_windowSize ? 1 : 0;
			normalizedDistance[k] = currentPt;
		}

		boolean isChange = false;

		double sum = 0.0;

		for (Point pt : normalizedDistance)
		{
			if(pt.y > 0.5)
			{
				sum += pt.y;
			}
		}
		if (sum - 1 >= param_k)
		{
			isChange = true;
		}
		return isChange;
	}

	public ArrayList<Point> getResultSet()
	{
		return this.resultSet;
	}

	@Override
	public String getDescription()
	{
		return null;
	}

	@Override
	public String getParams()
	{
		return null;
	}


}
